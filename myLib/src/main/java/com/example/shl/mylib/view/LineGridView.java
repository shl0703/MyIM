package com.example.shl.mylib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.example.shl.mylib.R;

/**
 * Created by zcw on 2015-11-30.
 */
public class LineGridView extends GridView {

    public LineGridView(Context context) {
        super(context);
    }

    public LineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LineGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        int childCount = getChildCount();//子view的总数
        if (childCount > 0) {
            View localView1 = getChildAt(0);
            int column = getWidth() / localView1.getWidth();//计算出一共有多少列，假设有3列
            if (childCount > column) {
                Paint localPaint;//画笔
                localPaint = new Paint();
                localPaint.setStyle(Paint.Style.STROKE);
                localPaint.setColor(getContext().getResources().getColor(R.color.divder_color));//设置画笔的颜色
                for (int i = 0; i < childCount; i++) { //遍历子view
                    View cellView = getChildAt(i);//获取子view
                    if (i < column) {//第一行
                        canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), localPaint);
                    }
                    if (i % column == 0) {//第一列
                        canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getLeft(), cellView.getBottom(), localPaint);
                    }
                    if ((i + 1) % column == 0) {//第三列
                        //画子view底部横线
                        canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
                        canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                        canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), localPaint);

                    } else if ((i + 1) > (childCount - (childCount % column))) {//如果view是最后一行
                        //画子view的右边竖线
                        canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                        //画子view的底部横线
                        canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);

                    } else {//如果view不是最后一行
                        //画子view的右边竖线
                        canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                        //画子view的底部横线
                        canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
                    }
                }
                //不满一行的补空格
                if (childCount % column != 0) {
                    int emptyCount = column - childCount % column;
                    View cellView = getChildAt(childCount - 1);//获取子view
                    int measuredWidth = cellView.getMeasuredWidth();
                    for (int i = 1; i < emptyCount; i++) {
                        int right = cellView.getRight() + measuredWidth * i;
                        canvas.drawLine(right, cellView.getTop(), right, cellView.getBottom(), localPaint);
                    }
                    //画子view的底部横线
                    canvas.drawLine(cellView.getLeft(), cellView.getBottom(), getRight(), cellView.getBottom(), localPaint);
                }
            } else {
                Paint localPaint;//画笔
                localPaint = new Paint();
                localPaint.setStyle(Paint.Style.STROKE);
                localPaint.setColor(getContext().getResources().getColor(R.color.divder_color));//设置画笔的颜色
                for (int i = 0; i < childCount; i++) {
                    View cellView = getChildAt(i);
                    if (i == 0) {
                        canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getLeft(), cellView.getBottom(), localPaint);
                        canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
                        canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                        canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), localPaint);
                    } else {
                        canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
                        canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                        canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), localPaint);
                    }
                }
                //不满一行的补空格
                if (childCount < column) {
                    int emptyCount = column - childCount % column;
                    View cellView = getChildAt(childCount - 1);//获取子view
                    int measuredWidth = cellView.getMeasuredWidth();
                    for (int i = 1; i < emptyCount; i++) {
                        int right = cellView.getRight() + measuredWidth * i;
                        canvas.drawLine(right, cellView.getTop(), right, cellView.getBottom(), localPaint);
                    }
                    //画子view的顶部横线
                    canvas.drawLine(cellView.getLeft(), cellView.getTop(), getRight(), cellView.getTop(), localPaint);
                    //画子view的底部横线
                    canvas.drawLine(cellView.getLeft(), cellView.getBottom(), getRight(), cellView.getBottom(), localPaint);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 解决页面显示不全,和嵌套在ScrollView中只显示一行的问题
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
