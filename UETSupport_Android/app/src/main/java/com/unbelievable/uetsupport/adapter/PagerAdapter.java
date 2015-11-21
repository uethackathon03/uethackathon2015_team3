package com.unbelievable.uetsupport.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.unbelievable.uetsupport.R;
import com.unbelievable.uetsupport.fragments.HelpFragment;
import com.unbelievable.uetsupport.fragments.MenuFragment;
import com.unbelievable.uetsupport.fragments.ProfileFragment;
import com.unbelievable.uetsupport.fragments.SocialFragment;
import com.unbelievable.uetsupport.fragments.NewsFragment;

/**
 * Created by Nam on 11/20/2015.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private Activity activity;
    private int tab_icon_size;
    private int[] icon = {
            R.mipmap.ic_web_white_24dp,
            R.mipmap.ic_school_white_24dp,
            R.mipmap.ic_notifications_white_24dp,
            R.mipmap.ic_help_outline_white_24dp,
            R.mipmap.ic_menu_white_24dp
    };

    public PagerAdapter(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity = activity;
        tab_icon_size = (int)activity.getResources().getDimension(R.dimen.tab_icon_size);
    }

    private final int numbOfTabs = 5;
    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable = activity.getResources().getDrawable(icon[position]);
        drawable.setBounds(0,0,tab_icon_size,tab_icon_size);
        ImageSpan imageSpan = new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                NewsFragment web = new NewsFragment();
                return web;
            case 1:
                ProfileFragment personal = new ProfileFragment();
                return personal;
            case 2:
                SocialFragment social = new SocialFragment();
                return social;
            case 3:
                HelpFragment help = new HelpFragment();
                return help;
            default:
                MenuFragment menu = new MenuFragment();
                return menu;
        }
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}