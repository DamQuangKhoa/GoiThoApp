package com.goitho.customerapp.screen.exhibition;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.screen.exhibition_cancel.ExhibitionCancelFragment;
import com.goitho.customerapp.screen.exhibition_doing.ExhibitionDoingFragment;
import com.goitho.customerapp.screen.exhibition_done.ExhibitionDoneFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Skull on 27/11/2017.
 */

public class ExhibitionFragment extends BaseFragment {

    @Bind(R.id.tabs)
    TabLayout tabLayout;

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    private ExhibitionDoingFragment exhibitionDoingFragment;
    private ExhibitionDoneFragment exhibitionDoneFragment;
    private ExhibitionCancelFragment exhibitionCancelFragment;

    public ExhibitionFragment() {
        // Required empty public constructor
    }


    public static ExhibitionFragment newInstance() {
        ExhibitionFragment fragment = new ExhibitionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exhibition_main, container, false);
        ButterKnife.bind(this, view);
        configFragments();
        initView();
        return view;
    }
    
    private void configFragments() {
        if (exhibitionDoingFragment == null) {
            exhibitionDoingFragment = ExhibitionDoingFragment.newInstance();
        }

        if (exhibitionDoneFragment == null) {
            exhibitionDoneFragment = ExhibitionDoneFragment.newInstance();
        }


        if (exhibitionCancelFragment == null) {
            exhibitionCancelFragment = ExhibitionCancelFragment.newInstance();
        }

    }
    

    private void initView() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(exhibitionDoingFragment);
        adapter.addFragment(exhibitionDoneFragment);
        adapter.addFragment(exhibitionCancelFragment);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.text_doing);
                case 1:
                    return getString(R.string.text_completed);
                case 2:
                    return getString(R.string.text_cancel);
            }
            return mFragmentList.get(position).getClass().getSimpleName();
        }
    }
}
