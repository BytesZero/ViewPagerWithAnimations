package com.zhengsonglan.viewpagerwithanimations.animations;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by zsl on 2015/2/25.
 * 动画2
 */
public class Animation3Transformer implements ViewPager.PageTransformer {
    private static final float MAX_ROATE = 20f;


    @SuppressLint("NewApi")
    public void transformPage(View view, float position) {
        int width = view.getWidth();
        int pageWidth = width;
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            ViewHelper.setRotation(view,0);
        } else if (position <= 1) { // [-1,1]
            float result=position*MAX_ROATE;
            ViewHelper.setPivotX(view,pageWidth*0.5f);
            ViewHelper.setPivotY(view,pageHeight);
            ViewHelper.setRotation(view,result);
        } else { // (1,+Infinity]
            ViewHelper.setRotation(view,0);
        }
    }
}
