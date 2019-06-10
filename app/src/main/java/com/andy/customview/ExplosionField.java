package com.andy.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.andy.customview.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author andysong
 * @data 2019-06-10
 * @discription xxx
 */
public class ExplosionField extends View {

    private OnClickListener mOnClickListener;

    private ArrayList<ExplosionAnimator> mExplosionAnimators;//动画集合
    private HashMap<View,ExplosionAnimator> mViewExplosionAnimatorHashMap;//用View和当前动画进行绑定，判断这个View有没有动画在执行
    private ParticleFactory mParticleFactory;








    public ExplosionField(Context context,ParticleFactory factory) {
        super(context);
        attach2Activity((Activity) context);
        mExplosionAnimators = new ArrayList<>();
        mViewExplosionAnimatorHashMap = new HashMap<>();
        mParticleFactory =factory;
    }

    /***
     * 重写onDraw方法
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //粒子动画绘制
        for (ExplosionAnimator explosionAnimator:mExplosionAnimators){
            explosionAnimator.draw(canvas);
        }


    }



    /**
     * 添加全屏显示动画的场地
     */
    private void attach2Activity(Activity activity){
        ViewGroup rootView = activity.findViewById(Window.ID_ANDROID_CONTENT);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(this,params);
    }

    /**
     * 希望谁有破碎效果的监听
     * @param view
     */
    public void addListener(View view){
        if (view instanceof ViewGroup){
            ViewGroup viewGroup  = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                addListener(view);
            }
        }else {
            view.setClickable(true);
            view.setOnClickListener(getOnClickListener());
        }
    }

    private OnClickListener getOnClickListener() {
        if (mOnClickListener == null){
            mOnClickListener = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //开始执行粒子动画
                    ExplosionField.this.explode(v);

                }
            };
        }
        return mOnClickListener;

    }

    /**
     * 分裂
     * @param mView 当前点击的View
     */
    public void explode(final View mView){
        //防止重复点击
        if (mViewExplosionAnimatorHashMap.get(mView)!=null&&mViewExplosionAnimatorHashMap.get(mView)
        .isStarted()){
            return;
        }
        if (mView.getVisibility()!=VISIBLE || mView.getAlpha() == 0){
            return;
        }

        //得到View相对于整个屏幕的坐标(由于受到标题栏的影响，需要上移)
        final Rect rect = new Rect();
        mView.getGlobalVisibleRect(rect);

        //标题栏高度
        int currentTop = ((ViewGroup)getParent()).getTop();

        //状态栏高度

        Rect frame  = new Rect();
        ((Activity)getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);


        int statusBarHeight = frame.top;
        //去掉状态栏和标题栏高度
        rect.offset(0,-currentTop-statusBarHeight);

        if (rect.width() == 0 || rect.height() == 0){
            //不能做分裂处理
            return;
        }

        //开始震动

        ValueAnimator animator = ValueAnimator.ofFloat(0f,1.0f).setDuration(150);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mView.setTranslationX((Utils.RANDOM.nextFloat()-0.5f)*mView.getWidth()*0.05f);
                mView.setTranslationY((Utils.RANDOM.nextFloat()-0.5f)*mView.getHeight()*0.05f);
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //震动结束
                mView.setTranslationX(0);
                mView.setTranslationY(0);
                //开始分裂动画
                explode(mView,rect);

            }
        });
        animator.start();
    }

    private void explode(final View view, Rect rect){
        //粒子爆炸动画
        final ExplosionAnimator animator = new ExplosionAnimator(this,Utils.createBitmapFromView(view),rect,mParticleFactory);
        mExplosionAnimators.add(animator);
        mViewExplosionAnimatorHashMap.put(view,animator);
        animator.addListener(new AnimatorListenerAdapter() {


            @Override
            public void onAnimationStart(Animator animation) {
                view.setClickable(false);
                view.animate().setDuration(150).scaleX(0f).scaleY(0f).alpha(0f).start();


            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setClickable(true);
                view.animate().setDuration(150).scaleX(1f).scaleY(1f).alpha(1f).start();
                //移除动画
                mExplosionAnimators.remove(animation);
                mViewExplosionAnimatorHashMap.remove(view);
            }
        });
        animator.start();
    }
}
