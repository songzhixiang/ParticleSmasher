package com.andy.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.andy.customview.doubleprocess.LocalService;
import com.andy.customview.doubleprocess.RemoteService;
import com.andy.library.plugin.PluginManager;
import com.andy.library.plugin.ProxyActivity;

/**
 * @author andysong
 * @data 2019-06-13
 * @discription xxx
 */
public class TestPluginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testplugin);
        PluginManager.getInstance().init(this);
        findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apkPath = Utils.copyAssetAndWrite(TestPluginActivity.this, "sdadsada.apk");
                PluginManager.getInstance().loadApk(apkPath);
            }
        });
        findViewById(R.id.btn_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TestPluginActivity.this, ProxyActivity.class);
                intent.putExtra("className","com.andy.pluginapp.NePluginActivity");
                startActivity(intent);
            }
        });


        //通过 1px Activity 进行提权
//        KeepManager.getInstance().registerKeep(this);

        //第二种方法，前台服务
//        startService(new Intent(this,ForegroundService.class));

        //双进程守护
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        KeepManager.getInstance().unregisterKeep(this);
    }
}
