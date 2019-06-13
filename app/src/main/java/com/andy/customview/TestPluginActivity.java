package com.andy.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
    }
}
