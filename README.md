# ViewPagerWithAnimations
给viewPager的添加上动画效果，并且使之兼容API11以下的版本

###重写ViewPager类
```java
public class MyViewPager extends ViewGroup {
...
      public void setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer) {
//        if (Build.VERSION.SDK_INT >= 11) {
            final boolean hasTransformer = transformer != null;
            final boolean needsPopulate = hasTransformer != (mPageTransformer != null);
            mPageTransformer = transformer;
            setChildrenDrawingOrderEnabledCompat(hasTransformer);
            if (hasTransformer) {
                mDrawingOrder = reverseDrawingOrder ? DRAW_ORDER_REVERSE : DRAW_ORDER_FORWARD;
            } else {
                mDrawingOrder = DRAW_ORDER_DEFAULT;
            }
            if (needsPopulate) populate();
//        }
    }
...
}
```
1. 注释掉判断SDK版本的地方 ```if (Build.VERSION.SDK_INT >= 11) {```
2. ```PageTransformer``` 改为```ViewPager.PageTransformer```

###编写动画

######动画1
```java
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
```

######效果

![](https://github.com/yy1300326388/ViewPagerWithAnimations/blob/master/image/viewpager1.gif)

######动画2

```java

/**
 * Created by zsl on 2015/2/25.
 * 动画2
 */
public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            ViewHelper.setAlpha(view,0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page

            ViewHelper.setAlpha(view,1);
            ViewHelper.setTranslationX(view,0);
            ViewHelper.setScaleX(view,1);
            ViewHelper.setScaleY(view,1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            ViewHelper.setAlpha(view,1-position);

            // Counteract the default slide transition
            ViewHelper.setTranslationX(view,pageWidth * -position);
            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            ViewHelper.setScaleX(view,scaleFactor);
            ViewHelper.setScaleY(view,scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            ViewHelper.setAlpha(view,0);
        }
    }
}

```
