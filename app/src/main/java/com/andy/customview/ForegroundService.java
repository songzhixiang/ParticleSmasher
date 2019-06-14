package com.andy.customview;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

/**
 * @author andysong
 * @data 2019-06-14
 * @discription xxx
 */
public class ForegroundService extends Service {

    private static final int SERVICE_ID = 1;

    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    private class LocalBinder extends Binder{

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT <18){
            //4.3
            startForeground(SERVICE_ID,new Notification());
        }else if (Build.VERSION.SDK_INT <26){
            //7.0  会在通知栏上面出现
            startForeground(SERVICE_ID,new Notification());
            //删除通知栏消息（原理：根据两个前台服务的启动ID是一样的，关闭以后系统就以为服务关闭了）
            startService(new Intent(this,InnnerService.class));
        }else {
            //设置Channel
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            //设置级别,通知重要性越低，但是最新版的SDK已经取消了对MIN级别的隐藏功能
            NotificationChannel notificationChannel = new NotificationChannel("channel","xxxx",NotificationManager.IMPORTANCE_MIN);
            if (null != manager){
                manager.createNotificationChannel(notificationChannel);
                Notification notification = new NotificationCompat.Builder(this,"channel").build();
                startForeground(SERVICE_ID,notification);
            }

        }

        return super.onStartCommand(intent, flags, startId);
    }

    public static class InnnerService extends Service{

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {

            startForeground(SERVICE_ID,new Notification( ));
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }


}
