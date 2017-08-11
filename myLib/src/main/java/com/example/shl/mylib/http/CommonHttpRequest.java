package com.example.shl.mylib.http;

import com.example.shl.mylib.bean.BaseJson;
import com.example.shl.mylib.bean.BaseReply;
import com.example.shl.mylib.utils.Consts;
import com.example.shl.mylib.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class CommonHttpRequest {

    private static CommonHttpRequest commonHttpRequest;

    private CommonHttpRequest() {
    }

    public static CommonHttpRequest getInstance() {
        synchronized (CommonHttpRequest.class) {

            if (commonHttpRequest == null) {
                commonHttpRequest = new CommonHttpRequest();
            }
            return commonHttpRequest;
        }
    }

    public void get(final GetRequestCallBack callBack, final int requestCode, final Class cls, RequestParams params) {
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                LogUtils.d("success:" + result);
                Gson gson = new Gson();
                BaseJson baseJson = gson.fromJson(result, BaseJson.class);
                JsonObject jObj = new JsonParser().parse(result).getAsJsonObject();
                if (jObj.get("Data") != null && !jObj.get("Data").isJsonNull()) {
                    if (jObj.get("Data").isJsonArray()) {
                        JsonArray data = jObj.get("Data").getAsJsonArray();
                        if (data != null && data.size() > 0) {
                            ArrayList list = new ArrayList();
                            for (int i = 0; i < data.size(); i++) {
                                list.add(new Gson().fromJson(data.get(i), cls));
                            }
                            baseJson.setData(list);
                        }
                    } else {
                        if (jObj.get("Data").isJsonObject()) {
                            JsonObject data = jObj.get("Data").getAsJsonObject();
                            baseJson.setData(new Gson().fromJson(data, cls));
                        } else {
                            String data = jObj.get("Data").getAsString();
                            baseJson.setData(data);
                        }
                    }
                } else {
                    LogUtils.d("return data null");
                }
                callBack.onLoadData(requestCode, Consts.HTTP_REQUEST_OK, baseJson);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                LogUtils.e("error:" + ex.getMessage());
                callBack.onLoadData(requestCode, Consts.HTTP_REQUEST_ERROR, null);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
                LogUtils.e("cancel:" + cex.getMessage());
                callBack.onLoadData(requestCode, Consts.HTTP_REQUEST_CANCEL, null);
            }

            @Override
            public void onFinished() {
                LogUtils.e("finish");
            }
        });
    }

    public void post(final PostRequestCallBack callBack, final int requestCode, String url, final Class cls, Map params) {
        org.xutils.http.RequestParams requestParams = new org.xutils.http.RequestParams(url);
        if (params != null) {
            Gson g = new Gson();
            String json = g.toJson(params);
            requestParams.setAsJsonContent(true);
            requestParams.setBodyContent(json);
            LogUtils.d("postBody:" + json);
        }
        LogUtils.d("url:" + url);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.d("success:" + result);
                Gson gson = new Gson();
                BaseReply reply = gson.fromJson(result, BaseReply.class);
                JsonObject jObj = new JsonParser().parse(result).getAsJsonObject();
                if (jObj.get("data") != null && !jObj.get("data").isJsonNull()) {
                    if (jObj.get("data").isJsonArray()) {
                        JsonArray data = jObj.get("data").getAsJsonArray();
                        if (data != null && data.size() > 0) {
                            ArrayList list = new ArrayList();
                            for (int i = 0; i < data.size(); i++) {
                                list.add(new Gson().fromJson(data.get(i), cls));
                            }
                            reply.setData(list);
                        }
                    } else {
                        if (jObj.get("data").isJsonObject()) {
                            JsonObject data = jObj.get("data").getAsJsonObject();
                            reply.setData(new Gson().fromJson(data, cls));
                        } else {
                            String data = jObj.get("data").getAsString();
                            reply.setData(data);
                        }
                    }
                } else {
                    LogUtils.d("return data null");
                }
                callBack.onLoadData(requestCode, Consts.HTTP_REQUEST_OK, reply);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                LogUtils.e("error:" + ex.getMessage());
                callBack.onLoadData(requestCode, Consts.HTTP_REQUEST_ERROR, null);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
                LogUtils.e("cancel:" + cex.getMessage());
                callBack.onLoadData(requestCode, Consts.HTTP_REQUEST_CANCEL, null);
            }

            @Override
            public void onFinished() {
                LogUtils.e("finish");
            }
        });
    }

    public void getDataInHeader(final PostRequestCallBack callBack, final int requestCode, String url, final Class cls, Map params) {
        org.xutils.http.RequestParams requestParams = new org.xutils.http.RequestParams(url);
        if (params != null) {
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> param : entries) {
                requestParams.addHeader(param.getKey(), param.getValue());
            }
        }
        LogUtils.d("url:" + url);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtils.d("success:" + result);
                Gson gson = new Gson();
                BaseReply reply = gson.fromJson(result, BaseReply.class);
                JsonObject jObj = new JsonParser().parse(result).getAsJsonObject();
                if (jObj.get("data") != null && !jObj.get("data").isJsonNull()) {
                    if (jObj.get("data").isJsonArray()) {
                        JsonArray data = jObj.get("data").getAsJsonArray();
                        if (data != null && data.size() > 0) {
                            ArrayList list = new ArrayList();
                            for (int i = 0; i < data.size(); i++) {
                                list.add(new Gson().fromJson(data.get(i), cls));
                            }
                            reply.setData(list);
                        }
                    } else {
                        if (jObj.get("data").isJsonObject()) {
                            JsonObject data = jObj.get("data").getAsJsonObject();
                            reply.setData(new Gson().fromJson(data, cls));
                        } else {
                            String data = jObj.get("data").getAsString();
                            reply.setData(data);
                        }
                    }
                } else {
                    LogUtils.d("return data null");
                }
                callBack.onLoadData(requestCode, Consts.HTTP_REQUEST_OK, reply);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                LogUtils.e("error:" + ex.getMessage());
                callBack.onLoadData(requestCode, Consts.HTTP_REQUEST_ERROR, null);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
                LogUtils.e("cancel:" + cex.getMessage());
                callBack.onLoadData(requestCode, Consts.HTTP_REQUEST_CANCEL, null);
            }

            @Override
            public void onFinished() {
                LogUtils.e("finish");
            }
        });
    }
}
