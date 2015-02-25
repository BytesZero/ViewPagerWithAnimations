package com.zhengsonglan.viewpagerwithanimations;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.zhengsonglan.viewpagerwithanimations.animations.DepthPageTransformer;
import com.zhengsonglan.viewpagerwithanimations.animations.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ViewPager mPager;

    private int[] imgs={R.drawable.guide_image1,R.drawable.guide_image2,R.drawable.guide_image3,R.drawable.guide_image1,R.drawable.guide_image2,R.drawable.guide_image3};
    private List<ImageView> images=new ArrayList<ImageView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mPager= (ViewPager) findViewById(R.id.main_viewpager);
        //设置viewpage的动画
        if (AppConfig.select==2){
            mPager.setPageTransformer(true,new DepthPageTransformer());
        }else{
            mPager.setPageTransformer(true,new ZoomOutPageTransformer());
        }

        mPager.setAdapter(new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView=new ImageView(MainActivity.this);
                imageView.setImageResource(imgs[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                images.add(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(images.get(position));
            }

            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view==o;
            }
        });
    }

    @Override
    public void onBackPressed() {
        //判断当前是否是第一张图片
        if (mPager.getCurrentItem()==0){
            super.onBackPressed();
        }else{
            //选择前一张图片
            mPager.setCurrentItem(mPager.getCurrentItem()-1);
        }

    }
}
