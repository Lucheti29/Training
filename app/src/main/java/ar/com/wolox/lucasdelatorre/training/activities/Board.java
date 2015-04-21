package ar.com.wolox.lucasdelatorre.training.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import ar.com.wolox.lucasdelatorre.training.R;
import ar.com.wolox.lucasdelatorre.training.supportabs.SlidingTabLayout;
import ar.com.wolox.lucasdelatorre.training.supportabs.ViewPagerAdapter;


public class Board extends ActionBarActivity {

    private Toolbar mToolbar;
    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;
    private SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        init();
        setUi();
        populate();
    }

    private void init() {
        CharSequence titles[] = {"NEWS","PROFILE"};
        mAdapter =  new ViewPagerAdapter(getSupportFragmentManager(), titles, titles.length);
    }

    private void setUi() {
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mPager = (ViewPager) findViewById(R.id.pager);
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
    }

    private void populate() {
        setSupportActionBar(mToolbar);
        mPager.setAdapter(mAdapter);
        mTabs.setDistributeEvenly(true);

        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.greenwolox);
            }
        });

        mTabs.setViewPager(mPager);
    }
}