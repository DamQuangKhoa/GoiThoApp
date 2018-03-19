package com.goitho.customerapp.screen.user;

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

public class UserActivity extends BaseActivity {
    @Inject
    UserPresenter registerPresenter;

    UserFragment fragment;

    public static void start(Context context, @NonNull boolean isEmployee) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(Constants.KEY_LOGIN_ACTIVITY, isEmployee);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initFragment();

        // Create the presenter
        CoreApplication.getInstance().getApplicationComponent()
                .plus(new UserModule(fragment))
                .inject(this);

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(getResources().getColor(R.color.transparent));
    }

    private void initFragment() {
        fragment = (UserFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = UserFragment.newInstance();
            addFragmentToBackStack(fragment, R.id.fragmentContainer);
        }
    }

    private void addFragmentToBackStack(UserFragment fragment, int frameId) {
        Precondition.checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
