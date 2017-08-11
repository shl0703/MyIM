package com.example.shl.mylib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

public class MyWebView extends WebView {

    long preTouchTime = 0;

    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 解决双击放大的问题
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            long currentTouchTime = System.currentTimeMillis();
            if (currentTouchTime - preTouchTime <= 300) {
                preTouchTime = currentTouchTime;
                return true;
            }
            preTouchTime = currentTouchTime;
        }
        return super.dispatchTouchEvent(ev);
    }
}