package com.liang.ipc;

import android.app.Application;
import android.os.Process;
import android.util.Log;

import com.liang.ipc.utils.MyUtils;

/**
 * Created by liliang on 2017/3/23.
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = MyUtils.getProcessName(getApplicationContext(), Process.myPid());
        Log.d(TAG, "application start, process name:" + processName);
    }
}
