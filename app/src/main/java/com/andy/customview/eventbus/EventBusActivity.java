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
public class EventBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new User("andy",23));
                        Log.e("EventBusActivity",Thread.currentThread().getName());
                    }
                }).start();


            }
        });
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, EventBusActivity.class);

        context.startActivity(starter);
    }

}
