package com.liang.viewevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.button1) {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button2) {
            Intent intent = new Intent(this, DemoActivity1.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button3) {
            Intent intent = new Intent(this, DemoActivity2.class);
            startActivity(intent);
        }
    }
}
