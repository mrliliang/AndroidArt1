package com.liang.ipc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liang.ipc.manager.UserManager;
import com.liang.ipc.model.User;
import com.liang.ipc.utils.MyConstants;
import com.liang.ipc.utils.MyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserManager.sUserId = 2;
        Log.d(TAG, "UserManager.sUserId = " + UserManager.sUserId);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        persistToFile();
    }

    private void persistToFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                User user = new User(1, "Hello world", false);
//                File dir = new File(MyConstants.CHAPTER_2_PATH);
//                if (!dir.exists()) {
//                    dir.mkdir();
//                }
//                File cachedFile = new File(dir, "usercache");
//                ObjectOutputStream objectOutputStream = null;
//                try {
//                    cachedFile.createNewFile();
//                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(cachedFile));
//                    objectOutputStream.writeObject(user);
//                    Log.d(TAG, "persist user:" + user);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    MyUtils.close(objectOutputStream);
//                }
                User user = new User(1, "hello world", false);
                File dir = new File(MyConstants.CHAPTER_2_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File cachedFile = new File(MyConstants.CACHE_FILE_PATH);
                ObjectOutputStream objectOutputStream = null;
                try {
                    objectOutputStream = new ObjectOutputStream(
                            new FileOutputStream(cachedFile));
                    objectOutputStream.writeObject(user);
                    Log.d(TAG, "persist user:" + user);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG, "persist fail");
                } finally {
                    MyUtils.close(objectOutputStream);
                }
            }
        }).start();
    }
}
