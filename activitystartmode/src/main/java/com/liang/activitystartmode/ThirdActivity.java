package com.liang.activitystartmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
//            intent.setClass(ThirdActivity.this, MainActivity.class);
                intent.putExtra("time", System.currentTimeMillis());
                ThirdActivity.this.startActivity(intent);
            }
        });
    }
}
