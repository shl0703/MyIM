package com.example.shl.mylib.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.view.WindowManager;

import com.example.shl.mylib.utils.LogUtils;

/**
 * TODO: document your custom view class.
 */
public class BaseDialog extends Dialog {
    private Bitmap mBackground;

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void show() {
        super.show();
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        lp.height = (int) (display.getHeight());
        getWindow().setAttributes(lp);
    }

    public void setBackGround(Bitmap bitmap) {
        try {
            if (bitmap != null && !bitmap.isRecycled()) {
                mBackground = bitmap;
                getWindow().getDecorView().setBackgroundDrawable(
                        new BitmapDrawable(getContext().getResources(), bitmap));
            } else {
                LogUtils.d("setBackGround,the background bitmap is null or recycled");
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
