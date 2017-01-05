package com.example.vedioviewcompat;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.xuehu365.videoviewmediacontroller.MediaController;
import com.xuehu365.videoviewmediacontroller.MediaController.onClickIsFullScreenListener;
import com.xuehu365.videoviewmediacontroller.VideoView;


public class MainActivity extends Activity implements onClickIsFullScreenListener, MediaController.OnKeyBackDownListener, View.OnClickListener {

    private MediaController mController;
    private VideoView viv;
    private RelativeLayout rlDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        viv = (VideoView) findViewById(R.id.videoView);
        rlDD = (RelativeLayout) findViewById(R.id.rl_dd);
        mController = new MediaController(this);
        mController.setClickIsFullScreenListener(this);
        mController.setOnKeyBackDownListener(this);
        mController.setBackClickListener(this);
        viv.setMediaController(mController);
        viv.setVideoURI(Uri.parse("android.resource://" + getPackageName()
                + "/" + R.raw.apple));
        viv.requestFocus();
        viv.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void setOnClickIsFullScreen() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {//设置RelativeLayout的全屏模式
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e("info", "横屏");
            rlDD.setVisibility(View.GONE);
        } else {
            Log.e("info", "竖屏");
            rlDD.setVisibility(View.VISIBLE);
        }
        super.onConfigurationChanged(newConfig);
        viv.refreshDrawableState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mController.dispatchKeyEvent(event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyBackDown() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {//横屏模式
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {//横屏模式
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            finish();
        }
    }
}
