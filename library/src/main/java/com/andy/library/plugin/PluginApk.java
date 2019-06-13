package com.andy.library.plugin;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * @author andysong
 * @data 2019-06-13
 * @discription xxx
 */
public class PluginApk {

    //插件APk的实例对象
    public PackageInfo mPackageInfo;
    public Resources mResources;
    public AssetManager mAssetManager;
    public DexClassLoader mClassLoader;

    public PluginApk(PackageInfo packageInfo, Resources resources, AssetManager assetManager, DexClassLoader classLoader) {
        mPackageInfo = packageInfo;
        mResources = resources;
        mAssetManager = assetManager;
        mClassLoader = classLoader;
    }
}
