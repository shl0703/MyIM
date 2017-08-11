package com.example.shl.mylib.utils;


import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * 类描述: 初始化statusbar
 */
public class StatusBar {
    private Activity mActivity;
    public StatusBar(Activity activity) {
        this.mActivity = activity;
    }

    public void initStatusBar(int res,boolean translucentStatus,boolean statusBarTintEnabled){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            setTranslucentStatus(translucentStatus);
            SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
            tintManager.setStatusBarTintEnabled(statusBarTintEnabled);
            //保证和状态栏同色
            tintManager.setStatusBarTintResource(res);
        }
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = mActivity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        final int bit = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        final int bit1 = WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
        if (on) {
            winParams.flags |= bit;
        } else {
            winParams.flags &= ~bit;
        }
        win.setAttributes(winParams);
    }
}