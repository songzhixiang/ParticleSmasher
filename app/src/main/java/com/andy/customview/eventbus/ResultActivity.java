package com.andy.customview.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.andy.customview.R;

/**
 * @author andysong
 * @data 2019-06-24
 * @discription xxx
 */
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusActivity.start(ResultActivity.this);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResult(User user){
        Log.e("szx","user info :"+user.toString());
        Log.e("szx","ResultActivity:"+Thread.currentThread().getName());
    }



}
