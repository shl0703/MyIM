package com.example.shl.mylib.utils;

import android.text.TextUtils;
import android.util.Log;

public class LogUtils {
    private static String TAG = "jc";

    public static void v(String msg) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (Log.isLoggable(TAG, Log.VERBOSE)) {
            Log.v(TAG, generalMessage(stackTraceElements, msg));
        }
    }

    public static void d(String msg) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, generalMessage(stackTraceElements, msg));
        }
    }

    public static void i(String msg) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (Log.isLoggable(TAG, Log.INFO)) {
            Log.i(TAG, generalMessage(stackTraceElements, msg));
        }
    }

    public static void w(String msg) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (Log.isLoggable(TAG, Log.WARN)) {
            Log.w(TAG, generalMessage(stackTraceElements, msg));
        }
    }

    public static void e(String msg) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (Log.isLoggable(TAG, Log.ERROR)) {
            Log.e(TAG, generalMessage(stackTraceElements, msg));
        }
    }

    public static String generalMessage(StackTraceElement[] stackTraceElements, String msg) {
        StackTraceElement element = null;
        if (stackTraceElements == null || stackTraceElements.length == 0) {
            element = null;
        } else if (stackTraceElements.length > 3) {
            element = stackTraceElements[3];
        } else if (stackTraceElements.length > 2) {
            element = stackTraceElements[2];
        } else if (stackTraceElements.length > 1) {
            element = stackTraceElements[1];
        } else if (stackTraceElements.length > 0) {
            element = stackTraceElements[1];
        }
        if (element == null) {
            return msg;
        }
        String className = element.getClassName();
        try {
            Class c = Class.forName(className);
            if (c != null) {
                className = c.getSimpleName();
            }
        } catch (ClassNotFoundException e) {

        }
        if (TextUtils.isEmpty(className)) {
            className = element.getClassName();
        }
        String methodName = element.getMethodName();
        int lineNumber = element.getLineNumber();
        String caller = className + ":" + methodName + " at line:" + lineNumber + " msg:==>" + msg;
        return caller;
    }
}
