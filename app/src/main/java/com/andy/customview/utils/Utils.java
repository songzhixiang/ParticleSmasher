package com.andy.customview.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import java.util.Random;

/**
 * @author andysong
 * @data 2019-06-10
 * @discription xxx
 */
public class Utils {

    ///////////////////////////////////////////////////////////////////////////
    // 粒子动画(1.生成一张和原来View一样的图片  2.添加全屏显示动画的场地  3.执行动画（1.震动  2.缩小透明--爆炸  3.爆炸结束,View恢复）)
    ///////////////////////////////////////////////////////////////////////////


    private static final Canvas  CANVAS = new Canvas();

    public static final Random RANDOM = new Random();


    public static Bitmap createBitmapFromView(View view){
        view.clearFocus();//使View恢复原样
        Bitmap bitmap  = createBitmapSafely(view.getWidth(),view.getHeight(),Bitmap.Config.ARGB_8888,1);
        if (bitmap != null){
            synchronized (CANVAS){
                CANVAS.setBitmap(bitmap);
                view.draw(CANVAS);
                CANVAS.setBitmap(null);
            }
        }
        return bitmap;

    }

    private static Bitmap createBitmapSafely(int width, int height, Bitmap.Config config, int retryCount) {
        try {

            return Bitmap.createBitmap(width,height,config);
        }catch (OutOfMemoryError error){
            error.printStackTrace();
            if (retryCount > 0){
                System.gc();
                return createBitmapSafely(width,height,config,retryCount);
            }
        }
        return null;


    }
}
