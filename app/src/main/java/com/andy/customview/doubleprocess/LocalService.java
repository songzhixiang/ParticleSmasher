package com.andy.customview.doubleprocess;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.andy.customview.ForegroundService;

/**
 * @author andysong
 * @data 2019-06-14
 * @discription xxx
 */
public class LocalService extends ForegroundService {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(LocalService.this,RemoteService.class),mServiceConnection, Service.BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //1.
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //2.当服务断开以后才进行拉活
            startService(new Intent(LocalService.this,RemoteService.class));
            bindService(new Intent(LocalService.this,RemoteService.class),mServiceConnection, Service.BIND_IMPORTANT);
        }
    };
}
