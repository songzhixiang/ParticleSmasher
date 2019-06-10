package com.andy.customview;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * @author andysong
 * @data 2019-06-10
 * @discription 粒子类
 */
public abstract class Particle {

    float cx;
    float cy;
    int color;

    public Particle(float cx, float cy, int color) {
        this.cx = cx;
        this.cy = cy;
        this.color = color;
    }

    protected abstract void draw(Canvas canvas,Paint paint);


    protected abstract void calculate(float factor);

    public void advance(Canvas canvas, Paint paint,float factor){
        calculate(factor);
        draw(canvas,paint);
    }
}
