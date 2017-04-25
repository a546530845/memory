package com.zxgzero.remotememory.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zxgzero.remotememory.R;
import com.zxgzero.remotememory.Util.about.About_keyboard;
import com.zxgzero.remotememory.Util.atymanager.ActivityManager;
import com.zxgzero.remotememory.Util.toolutil.LogUtil;
import com.zxgzero.remotememory.systemutil.SystemBarTintManager;


/**
 * 基础Activity
 *
 * @author zxg
 */
public abstract class BaseActivity extends AppCompatActivity {
    private final static String TAG = "BaseActivity";
    //上下文
    public Activity mContext;
    //共享参数
    protected SystemBarTintManager mTintManager;

    public TextView title_left;
    public TextView title_name;
    public TextView title_right;

    private boolean cancelDownload;



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityManager.getInstance().pushActivity(this);
        mContext = this;
		setStatusBarColor(R.color.colorAccent);
        setStatusbar();
        init();

    }

    public void setStausBarColorOrDrawable(int colorID, int drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            if (colorID != -1) {
                tintManager.setStatusBarTintColor(getResources().getColor(colorID));
            } else {
                tintManager.setStatusBarTintDrawable(getResources().getDrawable(drawable));
            }

        }
    }

    public void setStatusBarColor(int colorID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBarColorLOLLIPOP(colorID);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getResources().getColor(colorID));
        }
    }

    @TargetApi(21)
    private void setStatusBarColorLOLLIPOP(int colorID) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//		window.setStatusBarColor(getResources().getColor(colorID));
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    /**
     * 初始化操作
     */
    private void init() {
        this.beforeInitView();
        this.setLayoutView();
        this.initTitleView();
        this.initView();
        this.initListener();
        this.initData();
    }

    public void setStatusbar() {
        setStausBarColorOrDrawable(-1, R.mipmap.ic_launcher);
    }

    /**
     * 监听控件<br>
     * 页面跳转时，最先获取数据
     */
    protected abstract void beforeInitView();

    /**
     * 监听控件<br>
     */
    protected abstract void setLayoutView();

    /**
     * 监听控件<br>
     * 调用完setContentView之后马上调用 用于实例化TitleLayout
     */
    protected abstract void initTitleView();

    /**
     * 监听控件<br>
     * 调用完initTitleView之后马上调用 实例化Activity用到的控件
     */
    protected abstract void initView();

    /**
     * 初始化设定<br>
     * 调用完跟随initView之后调用 给控件设置设置事件
     */
    protected abstract void initListener();

    /**
     * 初始化设定<br>
     * 调用完跟随initView之后调用 给控件设置上信息
     */
    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("获取onResume类名=",this.getClass().getCanonicalName());

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d("获取onPause类名=",this.getClass().getCanonicalName());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void finish() {
        ActivityManager.getInstance().removeActivity(this);

        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 返回到上一页面（退出）
     */
    protected void turnToBack() {
        About_keyboard.hideSoftInput(this);
        finish();
        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    /*
     * 跳转到下一个页面的动作
     */
    protected void GotoAnim() {
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK://返回
                turnToBack();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}