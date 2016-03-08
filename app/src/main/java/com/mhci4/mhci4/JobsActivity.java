package com.mhci4.mhci4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.SupportMapFragment;
import com.mhci4.mhci4.fragments.TaskListFragment;
import com.mhci4.mhci4.fragments.TaskMapFragment;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class JobsActivity extends FragmentActivity implements MaterialTabListener,ViewPager.OnPageChangeListener {

    private static int NUM_ITEMS = 2;
    MaterialTabHost tabHost;
    ViewPager taskPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);

        taskPager = (ViewPager)findViewById(R.id.task_pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        taskPager.setAdapter(pagerAdapter);
        taskPager.addOnPageChangeListener(this);

        tabHost.addTab(tabHost.newTab()
                .setIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_task_white_24))
                .setTabListener(this));

        tabHost.addTab(tabHost.newTab()
                .setIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_map_white))
                .setTabListener(this));

    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        tabHost.setSelectedNavigationItem(tab.getPosition());
        taskPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    //On Page Change Listener methods

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position)
    {
        Log.i("JobsActivity","Position: " + position);
        tabHost.setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class PagerAdapter extends FragmentStatePagerAdapter
    {

        private static final int PAGE_TASK_LIST = 0;
        private static final int PAGE_TASK_MAP = 1;
        public PagerAdapter(FragmentManager fm)
        {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch(position)
            {
                case PAGE_TASK_LIST:
                    fragment = new TaskListFragment();
                    break;
                case PAGE_TASK_MAP:
                    fragment = new TaskMapFragment();
                    break;
                default:
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }
    }

}
