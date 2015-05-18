package ar.com.wolox.lucasdelatorre.training.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ar.com.wolox.lucasdelatorre.training.fragments.News;
import ar.com.wolox.lucasdelatorre.training.fragments.Profile;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[];
    int NumbOfTabs;

    public ViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.Titles = titles;
        this.NumbOfTabs = titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            News tab1 = new News();
            return tab1;
        } else {
            Profile tab2 = new Profile();
            return tab2;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
