package com.github.mehmetonur.backgroundtransition;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by mehmet onur on 14.11.2015.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final int NUM_PAGES = 5;

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    private Integer[] colors = null;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
//        mPager.setPageTransformer(true, new CrossFadePageTransformer());

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (mPagerAdapter.getCount() - 1) && position < (colors.length - 1)) {
                    mPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
                } else {
                    // the last page color
                    mPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setUpColors();

    }

    private void setUpColors() {
        Integer Page1Bg = ContextCompat.getColor(getApplicationContext(), R.color.Page1Bg);
        Integer Page2Bg = ContextCompat.getColor(getApplicationContext(), R.color.Page2Bg);
        Integer Page3Bg = ContextCompat.getColor(getApplicationContext(), R.color.Page3Bg);
        Integer Page4Bg = ContextCompat.getColor(getApplicationContext(), R.color.Page4Bg);
        Integer Page5Bg = ContextCompat.getColor(getApplicationContext(), R.color.Page5Bg);
        colors = new Integer[]{Page1Bg, Page2Bg, Page3Bg, Page4Bg, Page5Bg};
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ScreenSlidePageFragment();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.i(TAG, "Adding item #" + position);
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i(TAG, "Removing item #" + position);
            super.destroyItem(container, position, object);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
