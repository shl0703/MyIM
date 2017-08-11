package com.example.shl.mylib.http;

import com.example.shl.mylib.bean.BaseJson;

public interface GetRequestCallBack {
    void onLoadData(int requestCode, int resultCode, BaseJson baseJson);
}
