package com.goitho.customerapp.screen.add_action;

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
import com.goitho.customerapp.constants.Constants;

import javax.inject.Inject;

/**
 * Created by Skull on 11/12/2017.
 */

public class AddActionActivity extends BaseActivity {
    @Inject
    AddActionPresenter AddActionPresenter;

    AddActionFragment fragment;

    public static void start(Context context) {
        Intent intent = new Intent(context, AddActionActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, String product) {
        Intent intent = new Intent(context, AddActionActivity.class);
        intent.putExtra(Constants.ADD_ACTION_ACTIVITY, product);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initFragment();

        // Create the presenter
        CoreApplication.getInstance().getApplicationComponent()
                .plus(new AddActionModule(fragment))
                .inject(this);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
    }

    private void initFragment() {
        fragment = (AddActionFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = AddActionFragment.newInstance();
            addFragmentToBackStack(fragment, R.id.fragmentContainer);
        }
    }

    private void addFragmentToBackStack(AddActionFragment fragment, int frameId) {
        Precondition.checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
