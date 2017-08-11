package com.example.shl.mylib.http;

import com.example.shl.mylib.bean.BaseReply;

public interface PostRequestCallBack {
    void onLoadData(int requestCode, int resultCode, BaseReply reply);
}
