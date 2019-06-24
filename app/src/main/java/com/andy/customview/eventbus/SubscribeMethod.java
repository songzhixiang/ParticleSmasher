package com.andy.customview.eventbus;

import java.lang.reflect.Method;

/**
 * @author andysong
 * @data 2019-06-24
 * @discription xxx
 */
public class SubscribeMethod {


    //回调方法
    private Method mMethod;


    //线程模式
    private ThreadMode mThreadMode;


    //回调方法中参数类型
    private Class<?> type;


    public SubscribeMethod(Method method, ThreadMode threadMode, Class<?> type) {
        mMethod = method;
        mThreadMode = threadMode;
        this.type = type;
    }


    public Method getMethod() {
        return mMethod;
    }

    public void setMethod(Method method) {
        mMethod = method;
    }

    public ThreadMode getThreadMode() {
        return mThreadMode;
    }

    public void setThreadMode(ThreadMode threadMode) {
        mThreadMode = threadMode;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
