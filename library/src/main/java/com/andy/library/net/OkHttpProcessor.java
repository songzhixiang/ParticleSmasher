package com.andy.library.net;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author andysong
 * @data 2019-06-25
 * @discription xxx
 */
public class OkHttpProcessor  implements IHttpProcessor {

    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public OkHttpProcessor() {
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler();
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {
        RequestBody requestBody = appendBody(params);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String str = response.body().string();
                    Log.e("okhttp",str);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(str);
                        }
                    });
                }
            }
        });
    }

    private RequestBody appendBody(Map<String, Object> params) {
        return null;
    }
}
