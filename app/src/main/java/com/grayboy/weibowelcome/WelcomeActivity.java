package com.grayboy.weibowelcome;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mWelcomePage;
    private List<View> views;
    private MyViewPagerAdapter mAdapter;
    private LinearLayout mDotsll;
    private ImageView mDotsIv[];
    private int curIndex = 0;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initViews();
        initDots();
    }

    private void initDots() {
        mDotsll = (LinearLayout) findViewById(R.id.id_dot_ll);
        mDotsIv = new ImageView[views.size() + 1];
        for (int i = 0; i < views.size(); i++) {
            mDotsIv[i] = (ImageView) mDotsll.getChildAt(i);
            mDotsIv[i].setEnabled(true);
        }
        mDotsIv[curIndex].setEnabled(false);

    }

    private void initViews() {
        mWelcomePage = (ViewPager) findViewById(R.id.id_welcome_viewpager);
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.welcom1, null));
        views.add(inflater.inflate(R.layout.welcom2, null));
        views.add(inflater.inflate(R.layout.welcom3, null));
        views.add(inflater.inflate(R.layout.welcom4, null));

        mAdapter = new MyViewPagerAdapter(views, this);
        mWelcomePage.setAdapter(mAdapter);
        mWelcomePage.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    private void setDot(int position) {
        if (position < 0 || position > views.size() - 1
                || curIndex == position) {
            return;
        }
        mDotsIv[curIndex].setEnabled(true);
        mDotsIv[position].setEnabled(false);
        curIndex = position;
    }

    @Override
    public void onPageSelected(int position) {
        mWelcomePage.setCurrentItem(position);
        setDot(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
