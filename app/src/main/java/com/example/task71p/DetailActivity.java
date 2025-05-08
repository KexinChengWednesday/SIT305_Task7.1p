package com.example.task71p;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    int itemId;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dbHelper = new DBHelper(this);
        itemId = getIntent().getIntExtra("itemId", -1);

        TextView details = findViewById(R.id.details);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM items WHERE id = ?", new String[]{String.valueOf(itemId)});
        if (cursor.moveToFirst()) {
            String text = "Type: " + cursor.getString(1) + "\n" +
                          "Name: " + cursor.getString(2) + "\n" +
                          "Phone: " + cursor.getString(3) + "\n" +
                          "Description: " + cursor.getString(4) + "\n" +
                          "Date: " + cursor.getString(5) + "\n" +
                          "Location: " + cursor.getString(6);
            details.setText(text);
        }
        cursor.close();
    }

    public void removeAdvert(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("items", "id = ?", new String[]{String.valueOf(itemId)});
        db.close();
        startActivity(new Intent(this, ItemListActivity.class));
        finish();
    }
}