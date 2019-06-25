package com.andy.library.net;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author andysong
 * @data 2019-06-25
 * @discription xxx
 */
public class HttpHelper implements IHttpProcessor {



    private static class LazyLoad{
       private static HttpHelper instance = new HttpHelper();
    }

    private HttpHelper() {

    }

    public static HttpHelper getInstance(){
        return LazyLoad.instance;
    }

    private static IHttpProcessor mHttpProcessor;


    public static void init(IHttpProcessor iHttpProcessor){
        mHttpProcessor = iHttpProcessor;
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        String finalUrl = appendParams(url,params);
        mHttpProcessor.post(finalUrl,params,callBack);
    }

    private String appendParams(String url, Map<String, Object> params) {
        if (params == null|| params.isEmpty()){
            return url;
        }
        StringBuilder urlBuilder = new StringBuilder(url);
        if (urlBuilder.indexOf("?") <= 0){
            urlBuilder.append("?");
        }else {
            if (!urlBuilder.toString().endsWith("?")){
                urlBuilder.append("&");
            }
        }

        for (Map.Entry<String,Object> entry:params.entrySet()){
            urlBuilder.append("&"+entry.getKey()).append("=").append(encode(entry.getValue().toString()));

        }

        Log.e("szx","访问地址:"+urlBuilder.toString());

        return urlBuilder.toString();
    }

    private static String encode(String str) {
        try {
            return URLEncoder.encode(str,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}

