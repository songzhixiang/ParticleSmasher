package com.andy.customview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * @author andysong
 * @data 2019-06-14
 * @discription xxx
 */
public class KeepReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //如果是关屏
        if (TextUtils.equals(action,Intent.ACTION_SCREEN_OFF)){
                KeepManager.getInstance().startKeep(context);
        }else if (TextUtils.equals(action,Intent.ACTION_SCREEN_ON)){
            //开屏幕
            KeepManager.getInstance().finishKeep();

        }
    }
}
