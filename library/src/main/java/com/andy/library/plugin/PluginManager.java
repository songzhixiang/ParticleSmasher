package com.andy.library.plugin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @author andysong
 * @data 2019-06-13
 * @discription xxx
 * 创建DexClassLoader加载插件代码
 * 创建Resource加载资源文件
 * 管理插件Activity的生命周期
 */
public class PluginManager {

    private static class LazyLoad{
        private static PluginManager instance = new PluginManager();
    }

    public static PluginManager getInstance(){
        return LazyLoad.instance;
    }

    private Context mContext;

    private PluginApk mPluginApk;

    private PluginManager(){

    }

    public void init(Context context){
        mContext = context;
    }

    public void loadApk(String apkPath){
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES|PackageManager.GET_SERVICES);


        if (packageInfo != null){
            DexClassLoader classLoader =  createDexClassLoader(apkPath);
            AssetManager assetManager = createAssetManger(apkPath);
            Resources resources = createResources(assetManager);
            mPluginApk = new PluginApk(packageInfo,resources,assetManager,classLoader);

        }
    }

    //创建访问插件apk的DexClassLoader
    private DexClassLoader createDexClassLoader(String apkPath) {
        File file  =mContext.getDir("dex",Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath,file.getAbsolutePath(),null,mContext.getClassLoader());
    }

    //创建AssetManger
    private AssetManager createAssetManger(String apkPath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath",String.class);
            method.invoke(am,apkPath);
            return am;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    //创建Resource对象

    private Resources createResources(AssetManager assetManager) {
        Resources resources = mContext.getResources();

        return new Resources(assetManager,resources.getDisplayMetrics(),resources.getConfiguration());
    }

    public PluginApk getPluginApk(){
        return mPluginApk;
    }
}
