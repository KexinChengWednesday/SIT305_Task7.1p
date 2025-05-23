package com.example.task71p;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createAdvert(View view) {
        startActivity(new Intent(this, CreateAdvertActivity.class));
    }

    public void showItems(View view) {
        startActivity(new Intent(this, ItemListActivity.class));
    }
}