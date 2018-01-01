package com.platacode.chronos.Adapters;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.platacode.chronos.GeneralFragment;
import com.platacode.chronos.HistoryFragment;
import com.platacode.chronos.R;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GeneralFragment();
            case 1:
                return new HistoryFragment();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getResources().getText(R.string.general_tab);
            case 1:
                return context.getResources().getText(R.string.history_tab);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
