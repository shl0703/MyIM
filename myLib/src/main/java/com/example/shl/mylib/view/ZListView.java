package com.example.shl.mylib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by zcw on 2016-02-29.
 */
public class ZListView extends ListView implements AbsListView.OnScrollListener {
    public ZListView(Context context) {
        super(context);
        init();
    }

    public ZListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ZListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        super.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // 判断滚动到底部
        if (getLastVisiblePosition() == (getCount() - 1)) {
            Log.i("shl", "buttom");
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}