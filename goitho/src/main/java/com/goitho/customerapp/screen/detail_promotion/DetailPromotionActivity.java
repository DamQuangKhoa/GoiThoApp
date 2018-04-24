package com.goitho.customerapp.screen.detail_promotion;

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
 * Created by Skull on 11/12/2017.
 */

public class DetailPromotionActivity extends BaseActivity {
    public final static String KEY_PROMOTION_ID = "promotion_id";

    @Inject
    DetailPromotionPresenter DetailPromotionPresenter;

    DetailPromotionFragment fragment;

    public static void start(Context context, String promotionId) {
        Intent intent = new Intent(context, DetailPromotionActivity.class);
        intent.putExtra(KEY_PROMOTION_ID, promotionId);
        context.startActivity(intent);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initFragment();

        // Create the presenter
        CoreApplication.getInstance().getApplicationComponent()
                .plus(new DetailPromotionModule(fragment))
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
        fragment = (DetailPromotionFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = DetailPromotionFragment.newInstance();
            addFragmentToBackStack(fragment, R.id.fragmentContainer);
        }
    }

    private void addFragmentToBackStack(DetailPromotionFragment fragment, int frameId) {
        Precondition.checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
