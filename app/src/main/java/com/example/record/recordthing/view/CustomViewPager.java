package com.example.record.recordthing.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.lang.reflect.Field;

/**
 * Author : Gyk
 * CreateBy : 2019/03/08/14:04
 * PackageName : com.example.gyk.chatim.ui
 * Describe : TODO
 **/
public class CustomViewPager extends ViewPager {
    public CustomViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setReadEffect();
        setSCrollerDuration();

    }

    private void setSCrollerDuration() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller fixedSpeedScroller = new FixedSpeedScroller(getContext(), new DecelerateInterpolator());
            field.set(this, fixedSpeedScroller);
            fixedSpeedScroller.setmDuration(300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setReadEffect() {
        setPageTransformer(true, new PageTransformer() {
            private static final float MIN_SCALE = 0.75f;

            @Override
            public void transformPage(@NonNull View view, float position) {
                int pageWidth = view.getWidth();
                int pageHeight = view.getHeight();
                if (position < -1) {
                    view.setAlpha(0);
                } else if (position <= 0) {
                    view.setAlpha(1);
                    view.setTranslationX(0);
                    view.setScaleX(1);
                    view.setScaleY(1);
                } else if (position <= 1) {
                    view.setAlpha(1);
                    view.setTranslationX(pageWidth * -position);
                } else {
                    view.setAlpha(0);
                }
            }
        });
    }
}
