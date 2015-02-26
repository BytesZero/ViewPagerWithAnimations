package com.zhengsonglan.viewpagerwithanimations.animations;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by zsl on 2015/2/25.
 * 动画1
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;


    public void transformPage(View view, float position) {
        int width = view.getWidth();
        int pageWidth = width;
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            ViewHelper.setAlpha(view, 0);


        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                float result1=horzMargin-vertMargin/2;
                ViewHelper.setTranslationX(view,result1);
            } else {
                float result2=-horzMargin + vertMargin / 2;
                ViewHelper.setTranslationY(view,result2);
            }

            // Scale the page down (between MIN_SCALE and 1)

            ViewHelper.setScaleX(view,scaleFactor);
            ViewHelper.setScaleY(view,scaleFactor);

            ViewHelper.setAlpha(view,MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            ViewHelper.setAlpha(view,0);
        }
    }
}
