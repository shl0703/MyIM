package com.example.shl.mylib.bean;

import java.util.List;

/**
 * Created by suhon on 2017/3/27.
 */

public class BaseJson {

    private int code;
    private String message;
    private Object Data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
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
