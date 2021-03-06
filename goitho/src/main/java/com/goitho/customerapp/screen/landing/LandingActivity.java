package com.goitho.customerapp.screen.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.base.BaseActivity;
import com.goitho.customerapp.app.di.Precondition;
import com.goitho.customerapp.screen.phone_verification.PhoneVerificationActivity;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class LandingActivity extends BaseActivity {


    LandingFragment fragment;

    public static void start(Context context) {
        Intent intent = new Intent(context, LandingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initFragment();



        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
    }

    private void initFragment() {
        fragment = (LandingFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = LandingFragment.newInstance();
            addFragmentToBackStack(fragment, R.id.fragmentContainer);
        }
    }

    private void addFragmentToBackStack(LandingFragment fragment, int frameId) {
        Precondition.checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
