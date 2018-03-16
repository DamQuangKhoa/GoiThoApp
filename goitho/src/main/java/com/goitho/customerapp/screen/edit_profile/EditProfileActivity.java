package com.goitho.customerapp.screen.edit_profile;

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

import javax.inject.Inject;

/**
 * Created by Skull on 29/11/2017.
 */

public class EditProfileActivity extends BaseActivity {
    private static final String KEY_IS_EMPLOYEE = "key_is_employee";
    @Inject
    EditProfilePresenter presenter;

    EditProfileFragment fragment;

    public static void start(Context context) {
        start(context, false);
    }

    public static void start(Context context, boolean isEmployee) {
        Intent intent = new Intent(context, EditProfileActivity.class);
        intent.putExtra(KEY_IS_EMPLOYEE, isEmployee);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initFragment();

        // Create the presenter
        CoreApplication.getInstance().getApplicationComponent()
                .plus(new EditProfileModule(fragment))
                .inject(this);

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        presenter.setIsEmployee(getIntent().getBooleanExtra(KEY_IS_EMPLOYEE, false));
    }

    private void initFragment() {
        fragment = (EditProfileFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = EditProfileFragment.newInstance();
            addFragmentToBackStack(fragment, R.id.fragmentContainer);
        }
    }

    private void addFragmentToBackStack(EditProfileFragment fragment, int frameId) {
        Precondition.checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
