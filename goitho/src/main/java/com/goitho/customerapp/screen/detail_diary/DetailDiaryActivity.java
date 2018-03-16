package com.goitho.customerapp.screen.detail_diary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.demo.architect.data.model.offline.DiaryEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.base.BaseActivity;
import com.goitho.customerapp.app.di.Precondition;
import com.goitho.customerapp.constants.Constants;

import javax.inject.Inject;

/**
 * Created by Skull on 29/11/2017.
 */

public class DetailDiaryActivity extends BaseActivity {
    @Inject
    DetailDiaryPresenter presenter;

    DetailDiaryFragment fragment;

    public static void start(Context context, DiaryEntity diaryEntity) {
        Intent intent = new Intent(context, DetailDiaryActivity.class);
        intent.putExtra(Constants.KEY_DETAIL_DIARY_ACTIVITY, diaryEntity);
        context.startActivity(intent);
    }
    public static void start(Context context, @NonNull int position) {
        Intent intent = new Intent(context, DetailDiaryActivity.class);
        intent.putExtra(Constants.KEY_DETAIL_DIARY_ACTIVITY, position);
        context.startActivity(intent);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initFragment();

        // Create the presenter
        CoreApplication.getInstance().getApplicationComponent()
                .plus(new DetailDiaryModule(fragment))
                .inject(this);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        presenter.setDiaryEntity((DiaryEntity) getIntent().getSerializableExtra(Constants.KEY_DETAIL_DIARY_ACTIVITY));

    }

    private void initFragment() {
        fragment = (DetailDiaryFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = DetailDiaryFragment.newInstance();
            addFragmentToBackStack(fragment, R.id.fragmentContainer);
        }
    }

    private void addFragmentToBackStack(DetailDiaryFragment fragment, int frameId) {
        Precondition.checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
