package com.example.shl.mylib.bean;

import java.util.List;

/**
 * JSON 基础模型
 */
public class JsonModel<T> {

    private int code;
    private String message;
    private T Data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 是否操作成功
     *
     * @return
     */
    public boolean isSuccess() {
        if (code == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断有没有数据
     *
     * @return
     */
    public boolean hasData() {
        if (Data == null) {
            return false;
        }
        if (Data instanceof List && ((List) Data).size() <= 0) {
            return false;
        }
        return true;
    }
}
