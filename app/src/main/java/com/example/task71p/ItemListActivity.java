package com.example.task71p;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ItemListActivity extends AppCompatActivity {

    ListView listView; // 成员变量，必须正确引用
    ArrayAdapter<String> adapter;
    ArrayList<String> items = new ArrayList<>();
    ArrayList<Integer> ids = new ArrayList<>();
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        listView = findViewById(R.id.itemListView); // ✅ 修复：不能重新声明
        dbHelper = new DBHelper(this);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("itemId", ids.get(position));
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItems();
    }

    private void loadItems() {
        items.clear();
        ids.clear();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, description FROM items", null);

        while (cursor.moveToNext()) {
            ids.add(cursor.getInt(0));
            items.add(cursor.getString(1)); // 这里只展示描述字段
        }

        cursor.close();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter); // ✅ 此处不再为空指针
    }
}
