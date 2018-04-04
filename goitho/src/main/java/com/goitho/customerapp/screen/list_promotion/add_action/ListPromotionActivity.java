package com.goitho.customerapp.screen.list_promotion.add_action;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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

public class ListPromotionActivity extends BaseActivity {
    @Inject
    ListPromotionPresenter ListPromotionPresenter;

    ListPromotionFragment fragment;

    public static void start(Context context) {
        Intent intent = new Intent(context, ListPromotionActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, String product) {
        Intent intent = new Intent(context, ListPromotionActivity.class);
        intent.putExtra(Constants.ADD_ACTION_ACTIVITY, product);
        context.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initFragment();

        // Create the presenter
        CoreApplication.getInstance().getApplicationComponent()
                .plus(new ListPromotionModule(fragment))
                .inject(this);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
//        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
    }

    private void initFragment() {
        fragment = (ListPromotionFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = ListPromotionFragment.newInstance();
            addFragmentToBackStack(fragment, R.id.fragmentContainer);
        }
    }

    private void addFragmentToBackStack(ListPromotionFragment fragment, int frameId) {
        Precondition.checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
