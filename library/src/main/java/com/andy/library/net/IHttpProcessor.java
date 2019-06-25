package com.andy.library.net;

import java.util.Map;

/**
 * @author andysong
 * @data 2019-06-25
 * @discription xxx
 */
public interface IHttpProcessor {

    void post(String url, Map<String,Object> params,ICallBack callBack);
}
