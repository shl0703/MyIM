package com.example.shl.mylib.view;

import android.content.Context;
import android.os.Bundle;

import com.example.shl.mylib.R;

public class LoadingDialog extends BaseDialog {
    public LoadingDialog(Context context, int themeResId) {
        super(context, R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.common_loading_dialog);
    }
}
