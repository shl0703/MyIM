package com.example.shl.mylib.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义RecyclerView的基类, 定义selection相关的方法
 */
public class BaseRecyclerView extends RecyclerView {
    private View selectedView;
    private int selectedPosition;

    public BaseRecyclerView(Context context) {
        super(context);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setChildrenDrawingOrderEnabled(true);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setChildrenDrawingOrderEnabled(true);
    }

    public View getSelectedView() {
        return selectedView;
    }

    public void setSelectedView(View selectedView) {
        this.selectedView = selectedView;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    @Override
    public void setChildrenDrawingOrderEnabled(boolean enabled) {
        super.setChildrenDrawingOrderEnabled(enabled);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        // 此方法用于重定义布局顺序, 当选中放大某一项后可以在最上层
        int result = -1;
//        if (!isInTouchMode()) {
        View view = getChildAt(i);
        if (view != null) {
            if (view.hasFocus()) {
                result = childCount - 1;
            } else if (i == childCount - 1) {
                result = indexOfChild(getFocusedChild());
            }
        }
//        }
        return result >= 0 ? result : super.getChildDrawingOrder(childCount, i);
    }

    public static interface OnItemClickListener {
        public void onItemClick(RecyclerView recyclerView, View itemView, int position);
    }

    public static interface OnItemSelectedListener {
        public void onItemSelected(RecyclerView recyclerView, View itemView, int position);
    }
}
