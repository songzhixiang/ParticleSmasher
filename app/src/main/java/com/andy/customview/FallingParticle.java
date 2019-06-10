package com.andy.customview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.andy.customview.utils.Utils;

/**
 * @author andysong
 * @data 2019-06-10
 * @discription xxx
 */
public class FallingParticle extends Particle {

    float radius = FallingParticleFactory.PART_WH;
    float alpha = 1.0f;//透明度
    Rect mRect;

    public FallingParticle(float cx, float cy, int color, Rect rect) {
        super(cx, cy, color);
        mRect = rect;
    }

    @Override
    protected void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        paint.setAlpha((int)(Color.alpha(color)*alpha));
        canvas.drawCircle(cx,cy,radius,paint);
    }

    @Override
    protected void calculate(float factor) {
        cx+=factor* Utils.RANDOM.nextInt(mRect.width())*(Utils.RANDOM.nextFloat()-0.5f);
        cy+=factor* Utils.RANDOM.nextInt(mRect.height()/2);
        radius-=factor*Utils.RANDOM.nextInt(2);
        alpha = (1f-factor)*(Utils.RANDOM.nextFloat()+1);
    }
}
