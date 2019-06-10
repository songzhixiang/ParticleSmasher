package com.andy.customview;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * @author andysong
 * @data 2019-06-10
 * @discription xxx
 */
public abstract class ParticleFactory {
    abstract Particle[][] generatePaticles(Bitmap bitmap, Rect rect);
}
