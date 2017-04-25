package com.zxgzero.remotememory.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.zxgzero.remotememory.R;
import com.zxgzero.remotememory.base.BaseActivity;


public class WelcomeActivity extends BaseActivity {
    private String TAG = "WelcomeActivity";
    private int interval = 4000;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                        startActivity(new Intent(WelcomeActivity.this,
                                MainActivity.class));
                        finish();
                    break;
            }
        }

        ;
    };

    public void setStatusbar() {
        //重写方法，不设置bars
    }

    @Override
    protected void beforeInitView() {
    }

    @Override
    protected void setLayoutView() {
        setContentView(R.layout.welcome_aty);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void initTitleView() {

    }

    @Override
    protected void initView() {
        mHandler.sendEmptyMessageDelayed(0, interval);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
