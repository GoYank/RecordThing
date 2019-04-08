package com.example.record.recordthing.view;

import android.content.Context;
import android.widget.Scroller;

/**
 * Author : Gyk
 * CreateBy : 2019/03/08/14:12
 * PackageName : com.example.gyk.chatim.view
 * Describe : TODO
 **/
public class FixedSpeedScroller extends Scroller {
    private int mDuration = 1500;

    public FixedSpeedScroller(Context context) {
        super(context);
    }

    public FixedSpeedScroller(Context context, android.view.animation.Interpolator interpolator) {
        super(context, interpolator);


    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, duration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy);
    }

    public void setmDuration(int time) {
        mDuration = time;
    }

    public int getmDuration() {
        return mDuration;
    }
}
