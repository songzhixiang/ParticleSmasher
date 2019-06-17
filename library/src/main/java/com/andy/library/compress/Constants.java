package com.andy.library.compress;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;

/**
 * @author andysong
 * @data 2019-06-17
 * @discription xxx
 */
public class Constants {

    //缓存根目录
    public static final String BASE_CACHE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/data/";
    //压缩后缓存路径
    public static final String COMPRESS_CACHE = "compress_cache";

}
