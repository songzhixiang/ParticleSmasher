package com.andy.customview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

/**
 * @author andysong
 * @data 2019-06-14
 * @discription xxx
 */
public class KeepManager {

    private KeepReceiver mKeepReceiver;

    private WeakReference<Activity> mKeepAct;

    private static class LazyLoad{
        private static KeepManager instance = new KeepManager();
    }

    public static KeepManager getInstance(){
        return LazyLoad.instance;
    }

    private KeepManager(){

    }


    /**
     * 注册屏幕的开关
     */
    public void registerKeep(Context context){
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        mKeepReceiver = new KeepReceiver();
        context.registerReceiver(mKeepReceiver,filter);

    }

    public void unregisterKeep(Context context){
        if (null!=mKeepReceiver){
            context.unregisterReceiver(mKeepReceiver);
        }
    }


    /**
     * 开启一像素的Act
     *
     */
    public void startKeep(Context context){
        Intent intent = new Intent(context,KeepActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public void finishKeep(){
        if (null!= mKeepAct){
            Activity activity = mKeepAct.get();
            if (null!=activity){
                activity.finish();
            }
            mKeepAct = null;


        }
    }

    public void setKeep(KeepActivity keep){
        mKeepAct = new WeakReference<>(keep);
    }
}
