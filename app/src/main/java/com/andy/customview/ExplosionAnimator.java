package com.andy.customview;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * @author andysong
 * @data 2019-06-10
 * @discription 执行粒子动画
 */
public class ExplosionAnimator extends ValueAnimator {

    private static final int DEFAULT_DURATION = 1500;
    private Particle[][] mParticles;
    private Paint mPaint;
    private View mContainer;
    private ParticleFactory mParticleFactory;

    /**
     * 爆炸效果场地
     * @param field
     * @param bitmap
     * @param rect
     * @param particleFactory
     */
    public ExplosionAnimator(View field, Bitmap bitmap, Rect rect,ParticleFactory particleFactory) {
        mParticleFactory = particleFactory;
        mPaint  = new Paint();
        mContainer = field;
        setFloatValues(0f,1.0f);
        setDuration(DEFAULT_DURATION);
        mParticles = mParticleFactory.generatePaticles(bitmap,rect);

    }

    public void draw(Canvas canvas){
        if (!isStarted()){
            return;
        }
        //所有粒子开始运动
        for (Particle[] mParticle:mParticles){
            for (Particle particle:mParticle){
                particle.advance(canvas,mPaint, (Float) getAnimatedValue());
            }
        }
        mContainer.invalidate();

    }

    @Override
    public void start() {
        super.start();
        mContainer.invalidate();
    }
}
