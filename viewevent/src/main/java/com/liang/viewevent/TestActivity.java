package com.liang.viewevent;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nineoldandroids.animation.ValueAnimator;

public class TestActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener{

    private static final String TAG = "TestActivity";

    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAY_TIME = 33;

    private Button mButton1;
    private View mButton2;

    private int mCount = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SCROLL_TO:
                    mCount++;
                    if (mCount <= FRAME_COUNT) {
                        float fraction = mCount / (float) FRAME_COUNT;
                        int scrollX = (int) (fraction * 100) * (-1);
                        mButton1.scrollTo(scrollX, 0);
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAY_TIME);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = findViewById(R.id.button2);
        mButton2.setOnLongClickListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            Log.d(TAG, "button1.left　=　" + mButton1.getLeft());
            Log.d(TAG, "button1.x = " + mButton1.getX());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (v == mButton1) {
            mButton1.setTranslationX(100);
//            Log.d(TAG, "button1.left=" + mButton1.getLeft());
//            Log.d(TAG, "button1.x=" + mButton1.getX());
//            ObjectAnimator.ofFloat(mButton1, "translationX", 0, 100).setDuration(1000).start();
//            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mButton1.getLayoutParams();
//            params.width += 100;
//            params.leftMargin += 100;
//            mButton1.requestLayout();
//            mButton1.setLayoutParams(params);

             final int startX = 0;
             final int deltaX = 100;
             ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(1000);
             animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                 @Override
                 public void onAnimationUpdate(ValueAnimator animator) {
                     float fraction = animator.getAnimatedFraction();
                     mButton1.scrollTo(startX + (int) (deltaX * fraction), 0);
                 }
             });
             animator.start();

//            mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAY_TIME);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(this, "long click", Toast.LENGTH_SHORT).show();
        return true;
    }
}
