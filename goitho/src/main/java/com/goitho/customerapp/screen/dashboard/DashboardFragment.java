package com.goitho.customerapp.screen.dashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.goitho.customerapp.R;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.screen.booking.BookingFragment;
import com.goitho.customerapp.screen.booking.BookingModule;
import com.goitho.customerapp.screen.booking.BookingPresenter;
import com.goitho.customerapp.screen.home.HomeFragment;
import com.goitho.customerapp.screen.home.HomeModule;
import com.goitho.customerapp.screen.home.HomePresenter;
import com.goitho.customerapp.screen.landing.LandingFragment;
import com.goitho.customerapp.screen.notification.NotificationFragment;
import com.goitho.customerapp.screen.notification.NotificationModule;
import com.goitho.customerapp.screen.notification.NotificationPresenter;
import com.goitho.customerapp.screen.order.OrderFragment;
import com.goitho.customerapp.screen.order.OrderModule;
import com.goitho.customerapp.screen.order.OrderPresenter;
import com.goitho.customerapp.screen.user.UserFragment;
import com.goitho.customerapp.screen.user.UserModule;
import com.goitho.customerapp.screen.user.UserPresenter;
import com.goitho.customerapp.widgets.CustomViewPager;
import com.goitho.customerapp.widgets.customtablayout.CustomTabLayout;
import com.goitho.customerapp.widgets.customtablayout.TabEntity;
import com.goitho.customerapp.widgets.customtablayout.listener.CustomTabEntity;
import com.goitho.customerapp.widgets.customtablayout.listener.OnTabSelectListener;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Skull on 14/12/2017.
 */

public class DashboardFragment extends BaseFragment implements DashboardContract.View {

    private DashboardContract.Presenter mPresenter;

    @Inject
    HomePresenter homePresenter;

    @Inject
    UserPresenter userPresenter;

    @Inject
    NotificationPresenter notificationPresenter;

    @Inject
    OrderPresenter orderPresenter;

    @Inject
    BookingPresenter bookingPresenter;

    @Bind(R.id.tabs)
    CustomTabLayout tabLayout;

    @Bind(R.id.viewpager)
    CustomViewPager viewPager;

    @Bind(R.id.view)
    View view;

    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private NotificationFragment notificationFragment;
    private UserFragment userFragment;
    private BookingFragment bookingFragment;
    private LandingFragment landingFragment;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"Trang chủ", "Đơn hàng", "", "Tin nhắn", "Tài khoản"};
    private int[] mIconUnselectIds = {R.drawable.ic_home_main_unselete, R.drawable.ic_order_main_unselete,
            R.drawable.ic_back, R.drawable.ic_mess_main_unselete, R.drawable.ic_user_main_unselete
    };
    private int[] mIconSelectIds = {R.drawable.ic_home_main_selete, R.drawable.ic_order_main_selete,
            R.drawable.ic_back, R.drawable.ic_mess_main_unselete, R.drawable.ic_user_main_selete
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
        configFragments();
        initView();
        return view;
    }

    private void configFragments() {
        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
        }

        if (notificationFragment == null) {
            notificationFragment = NotificationFragment.newInstance();
        }
        if (orderFragment == null) {
            orderFragment = OrderFragment.newInstance();
        }

        if (bookingFragment == null) {
            bookingFragment = BookingFragment.newInstance();
        }

        if (userFragment == null) {
            userFragment = UserFragment.newInstance();
        }
        if (landingFragment == null) {
            landingFragment = LandingFragment.newInstance();
        }
        CoreApplication.getInstance().getApplicationComponent()
                .plus(new HomeModule(homeFragment),
                        new OrderModule(orderFragment),
                        new BookingModule(bookingFragment),
                        new NotificationModule(notificationFragment),
                        new UserModule(userFragment))
                .inject(this);

    }

    private void initView() {
        if (checkNavigationBarSize() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setVisibility(View.VISIBLE);
        }
        setupViewPager(viewPager);
        setupTabItem(tabLayout);

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

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
        openHome();

    }


    private void setupTabItem(CustomTabLayout tabLayout) {

        for (int i = 0; i < 5; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        tabLayout.showMsg(3, 5);
        tabLayout.setMsgMargin(3, -20, 8);

    }


    private void setupViewPager(CustomViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(homeFragment);
        adapter.addFragment(orderFragment);
        adapter.addFragment(bookingFragment);
        adapter.addFragment(notificationFragment);
        if (SharedPreferenceHelper.getInstance(getContext())
                .getBoolean(Constants.KEY_CHECK_LOGIN, false)) {
            adapter.addFragment(userFragment);
        } else {
            adapter.addFragment(landingFragment);
        }
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);
    }

    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
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

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @OnClick(R.id.layout_booking)
    public void booking() {
        tabLayout.setCurrentTab(2);
        viewPager.setCurrentItem(2);
    }

    @Override
    public void setupView(boolean login) {

    }

    public void openHome() {
        tabLayout.setCurrentTab(0);
        viewPager.setCurrentItem(0);
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
    }

    public boolean checkNavigationBarSize() {
        Point appUsableSize = getAppUsableScreenSize();
        Point realScreenSize = getRealScreenSize();
        boolean check = false;
        // navigation bar at the bottom
        if (appUsableSize.y < realScreenSize.y) {
            check = true;
        }
        // navigation bar is not present
        return check;
    }

    public Point getAppUsableScreenSize() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public Point getRealScreenSize() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();

        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealSize(size);
        } else if (Build.VERSION.SDK_INT >= 14) {
            try {
                size.x = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                size.y = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            } catch (NoSuchMethodException e) {
            }
        }

        return size;
    }
}
