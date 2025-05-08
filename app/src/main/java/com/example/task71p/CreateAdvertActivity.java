package com.example.task71p;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CreateAdvertActivity extends AppCompatActivity {

    EditText name, phone, desc, date, location;
    RadioGroup typeGroup;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert); // ✅ 必须设置布局
        dbHelper = new DBHelper(this);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        desc = findViewById(R.id.description);
        date = findViewById(R.id.date);
        location = findViewById(R.id.location);
        typeGroup = findViewById(R.id.typeGroup);
    }

    public void saveAdvert(View view) {
        if (typeGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select Lost or Found", Toast.LENGTH_SHORT).show();
            return;
        }

        String itemType = ((RadioButton) findViewById(typeGroup.getCheckedRadioButtonId())).getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", itemType);
        values.put("name", name.getText().toString());
        values.put("phone", phone.getText().toString());
        values.put("description", desc.getText().toString());
        values.put("date", date.getText().toString());
        values.put("location", location.getText().toString());

        long result = db.insert("items", null, values);
        db.close();

        if (result != -1) {
            Toast.makeText(this, "Advert saved", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error saving advert", Toast.LENGTH_SHORT).show();
        }
    }
}
