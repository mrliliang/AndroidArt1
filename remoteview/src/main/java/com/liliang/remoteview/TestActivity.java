package com.liliang.remoteview;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1;
    private Button mButton2;

    private static int sId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mButton1) {
            sId++;
            Intent intent = new Intent(this, DemoActivity2.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setTicker("hello world")
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager manager = (NotificationManager)getSystemService(Context
                    .NOTIFICATION_SERVICE);
            manager.notify(sId, notification);
        } else if (view == mButton2) {
            sId++;
            Intent intent = new Intent(this, DemoActivity1.class);
            intent.putExtra("sid", "" + sId);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            RemoteViews remoteViews = new RemoteViews(getPackageName(),
                    R.layout.layout_notification);
            remoteViews.setTextViewText(R.id.msg, "chapter_5: " + sId);
            remoteViews.setImageViewResource(R.id.icon, R.drawable.icon1);

            PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(this,
                    0, new Intent(this, DemoActivity2.class), PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.open_activity2, openActivity2PendingIntent);

            Notification notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setTicker("hello world")
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setCustomContentView(remoteViews)
                    .build();
            NotificationManager manager = (NotificationManager)getSystemService(Context
                    .NOTIFICATION_SERVICE);
            manager.notify(sId, notification);
        }
    }
}
