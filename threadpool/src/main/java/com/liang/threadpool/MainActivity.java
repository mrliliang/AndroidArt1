package com.liang.threadpool;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scheduleThreads();
    }

    private void scheduleThreads() {
        runAsyncTask();
        runIntentService();
        runThreadPool();
    }

    private void runThreadPool() {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                Log.d(TAG, "ThreadPool " + Thread.currentThread().getName() + " " +
                        System.currentTimeMillis());
            }
        };

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        fixedThreadPool.execute(command);

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(command);

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
        scheduledThreadPool.schedule(command, 2000, TimeUnit.MICROSECONDS);
        scheduledThreadPool.scheduleAtFixedRate(command, 10, 1000, TimeUnit.MILLISECONDS);

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(command);
    }

    private void runIntentService() {
        Intent intent = new Intent(this, LocalIntentService.class);
        intent.putExtra("task_action", "com.liang.action.TASK1");
        startService(intent);
        intent.putExtra("task_action", "com.liang.action.TASK2");
        startService(intent);
        intent.putExtra("task_action", "com.liang.action.TASK3");
        startService(intent);
    }

    private void runAsyncTask() {
        try {
            new DownLoadFileTask().execute(new URL("https://www.baidu.com"), new URL("http://www" +
                    ".renyugang.cn"));
        } catch (MalformedURLException e) {
            Log.e(TAG, null, e);
        }
    }

    private class DownLoadFileTask extends AsyncTask<URL, Integer, Long> {
        @Override
        protected Long doInBackground(URL... params) {
            int count = params.length;
            long totalSize = 0;
            for (int i = 1; i <= count; i++) {
                publishProgress((int) ((i / (float)count) * 100));
                if (isCancelled()) {
                    break;
                }
            }
            return totalSize;
        }

        protected void onProgressUpdate(Integer... progress) {
            setProgress(progress[0]);
            Log.d(TAG, "Download progress " + progress[0] + "%");
        }

        protected void onPostExecute(Long result) {

        }

    }
}
