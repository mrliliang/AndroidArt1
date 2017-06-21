package com.liliang.remoteview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class DemoActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        initView();
    }

    private void initView() {
        Toast.makeText(this, getIntent().getStringExtra("sid"),
                Toast.LENGTH_SHORT).show();
    }
}
