package com.andy.customview;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * @author andysong
 * @data 2019-06-10
 * @discription xxx
 */
public class FallingParticleFactory extends ParticleFactory {

    public static final int  PART_WH = 8;


    @Override
    Particle[][] generatePaticles(Bitmap bitmap, Rect rect) {
        int w = rect.width();
        int h = rect.height();

        int partW_Count = w/PART_WH;//横向个数
        int partH_Count = h/PART_WH;//竖向个数

        partW_Count = partW_Count>0?partW_Count:1;
        partH_Count = partH_Count>0?partH_Count:1;

        int bitmap_part_w = bitmap.getWidth()/partW_Count;
        int bitmap_part_h = bitmap.getHeight()/partH_Count;

        Particle[][] particles = new Particle[partH_Count][partW_Count];
        for (int row = 0; row < partH_Count; row++) {
            //行
            for (int cloumn = 0; cloumn < partW_Count; cloumn++) {
                //列
                //获取当前粒子所在的位置的颜色
                int color = bitmap.getPixel(cloumn*bitmap_part_w,row*bitmap_part_h);
                float x = rect.left+PART_WH*cloumn;
                float y = rect.top+PART_WH*row;
                particles[row][cloumn] = new FallingParticle(x,y,color,rect);
            }
        }


        return particles;
    }
}
