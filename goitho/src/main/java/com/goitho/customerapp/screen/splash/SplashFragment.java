package com.goitho.customerapp.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.goitho.customerapp.R;
import com.goitho.customerapp.app.CoreApplication;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.screen.diary.DiaryActivity;
import com.goitho.customerapp.screen.farmer.FarmerActivity;
import com.goitho.customerapp.screen.login_permission.LoginPermissionActivity;
import com.goitho.customerapp.util.Precondition;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class SplashFragment extends BaseFragment implements SplashContract.View {

    private SplashContract.Presenter mPresenter;



    public SplashFragment() {
        // Required empty public constructor
    }


    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
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
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);



        return view;
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
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

    @OnClick(R.id.btn_begin)
    public void begin(){
        SharedPreferenceHelper.getInstance(CoreApplication.getInstance())
                .pushWasStartedBoolean(true);
        startLoginPermission();
    }

    @Override
    public void startDiaryActivity(String id) {
        DiaryActivity.start(getActivity(), id);
        getActivity().finish();
    }

    @Override
    public void startFarmerActivity() {
        FarmerActivity.start(getActivity());
        getActivity().finish();
    }

    @Override
    public void startLoginPermission() {
        LoginPermissionActivity.start(getActivity());
        getActivity().finish();
    }
}
