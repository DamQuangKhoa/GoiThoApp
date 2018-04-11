package com.goitho.customerapp.screen.phone_verification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.base.BaseActivity;
import com.goitho.customerapp.app.di.Precondition;
import com.goitho.customerapp.constants.Constants;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class PhoneVerificationActivity extends BaseActivity {

    public static final int VERIFICATION_TYPE_REGISTER = 1;
    public static final int VERIFICATION_TYPE_RESET_PASSWORD = 2;
    public static final int VERIFICATION_TYPE_RESET_PHONE = 3;
    public static final String KEY_VERIFICATION_TYPE = "KEY_VERIFICATION_TYPE";
    public static final String KEY_NEW_PHONE = "KEY_NEW_PHONE";
    public static final String KEY_NEW_PASSWORD = "KEY_NEW_PASSWORD";

    @Inject
    PhoneVerificationPresenter registerPresenter;

    PhoneVerificationFragment fragment;

    public static void startFromResetPhone(Context context, int userId, String newPhone) {
        Intent intent = new Intent(context, PhoneVerificationActivity.class);
        intent.putExtra(Constants.KEY_USER_ID, userId);
        intent.putExtra(KEY_VERIFICATION_TYPE, VERIFICATION_TYPE_RESET_PHONE);
        intent.putExtra(KEY_NEW_PHONE, newPhone);
        context.startActivity(intent);
    }

    public static void startFromResetPassword(Context context, int userId, String newPassword) {
        Intent intent = new Intent(context, PhoneVerificationActivity.class);
        intent.putExtra(Constants.KEY_USER_ID, userId);
        intent.putExtra(KEY_VERIFICATION_TYPE, VERIFICATION_TYPE_RESET_PASSWORD);
        intent.putExtra(KEY_NEW_PASSWORD, newPassword);
        context.startActivity(intent);
    }

    public static void startFromRegister(Context context, int userId) {
        Intent intent = new Intent(context, PhoneVerificationActivity.class);
        intent.putExtra(KEY_VERIFICATION_TYPE, VERIFICATION_TYPE_REGISTER);
        intent.putExtra(Constants.KEY_USER_ID, userId);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initFragment();

        // Create the presenter
        CoreApplication.getInstance().getApplicationComponent()
                .plus(new PhoneVerificationModule(fragment))
                .inject(this);

        Window w = getWindow(); // in Activity's onCreate() for instance
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
    }

    private void initFragment() {
        fragment = (PhoneVerificationFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = PhoneVerificationFragment.newInstance();
            addFragmentToBackStack(fragment, R.id.fragmentContainer);
        }
    }

    private void addFragmentToBackStack(PhoneVerificationFragment fragment, int frameId) {
        Precondition.checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
