package com.goitho.customerapp.screen.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.architect.data.model.ActivityEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.HistoryAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.util.Precondition;
import com.goitho.customerapp.widgets.customtablayout.CustomTabLayout;
import com.goitho.customerapp.widgets.customtablayout.TabEntity;
import com.goitho.customerapp.widgets.customtablayout.listener.CustomTabEntity;
import com.goitho.customerapp.widgets.customtablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Skull on 14/12/2017.
 */

public class DashboardFragment extends BaseFragment implements DashboardContract.View {

    private DashboardContract.Presenter mPresenter;

    @Bind(R.id.tabs)
    CustomTabLayout tabLayout;

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private int[] mIconUnselectIds = {
         };
    private int[] mIconSelectIds = {
           };

    public DashboardFragment() {
        // Required empty public constructor
    }


    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
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
        View view = inflater.inflate(R.layout.fragment_dashboard_main, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    private void initView(View view) {

        setupViewPager(viewPager);
        setupTabItem(tabLayout);

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {

                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setCurrentHome();
    }

    public void setCurrentHome() {
        tabLayout.setCurrentTab(0);
        viewPager.setCurrentItem(0);
    }

    private void setupTabItem(CustomTabLayout tabLayout) {

        for (int i = 0; i < 4; i++) {
            mTabEntities.add(new TabEntity(mTitles[i],mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
//        adapter.addFragment(dashboardHomeFragment);
//        adapter.addFragment(notificationFragment);
//        adapter.addFragment(barcodeFragment);
//        adapter.addFragment(userFragment);
        viewPager.setAdapter(adapter);
    }


    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
        this.mPresenter = Precondition.checkNotNull(presenter);
    }

    @Override
    public void showProgressBar() {
        showProgressDialog();
    }

    @Override
    public void hideProgressBar() {
        hideProgressDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.stop();
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
                    return "HOME";
                case 1:
                    return "ĐỊA ĐIỂM";
                case 2:
                    return "DỊCH VỤ";
                case 3:
                    return "ĐƠN HÀNG";
                case 4:
                    return "CÂU HỎI";
                case 5:
                    return "CÀI ĐẶT";
            }
            return mFragmentList.get(position).getClass().getSimpleName();
        }
    }

}
