package com.grayboy.weibowelcome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by gray on 5/23/2016.
 */
public class MyViewPagerAdapter extends PagerAdapter {

    private final String SHAREDPREFERENCES_NAME = "first_pref";
    private Activity activity;
    private List<View> views;

    public MyViewPagerAdapter(List<View> views, Activity activity) {
        this.activity = activity;
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        if (position == views.size() - 1) {
            ImageView imageView = (ImageView) container.findViewById(R.id.id_iv_last);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setGuide();
                    goHome();
                }
            });
        }
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    private void setGuide() {
        SharedPreferences preferences = activity.getSharedPreferences(SHAREDPREFERENCES_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstIn", false);
        editor.commit();
    }

    private void goHome() {
        Intent i = new Intent(activity, MainActivity.class);
        activity.startActivity(i);
        activity.finish();
    }

}
