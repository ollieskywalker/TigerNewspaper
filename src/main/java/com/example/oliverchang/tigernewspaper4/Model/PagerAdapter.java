package com.example.oliverchang.tigernewspaper4.Model;

/**
 * Created by Oliver Chang on 12/17/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.oliverchang.tigernewspaper4.Presenter.FeatureFragment;
import com.example.oliverchang.tigernewspaper4.Presenter.NewsFragment;
import com.example.oliverchang.tigernewspaper4.Presenter.OpinionFragment;
import com.example.oliverchang.tigernewspaper4.Presenter.RecentFragment;
import com.example.oliverchang.tigernewspaper4.Presenter.SportsFragment;

/**
 * Created by Oliver Chang on 12/17/2016.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                RecentFragment tab1 = new RecentFragment();
                return tab1;
            case 1:
                NewsFragment tab2 = new NewsFragment();
                return tab2;
            case 2:
                FeatureFragment tab3 = new FeatureFragment();
                return tab3;
            case 3:
                SportsFragment tab4 = new SportsFragment();
                return tab4;
            case 4:
                OpinionFragment tab5 = new OpinionFragment();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
