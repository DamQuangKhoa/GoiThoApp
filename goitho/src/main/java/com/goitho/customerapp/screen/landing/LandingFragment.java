package com.goitho.customerapp.screen.landing;

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
import com.goitho.customerapp.screen.login.LoginActivity;
import com.goitho.customerapp.screen.register.RegisterActivity;
import com.goitho.customerapp.util.Precondition;
import com.goitho.customerapp.widgets.scviewpager.SCViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class LandingFragment extends BaseFragment implements LandingContract.View {

    private LandingContract.Presenter mPresenter;



    public LandingFragment() {
        // Required empty public constructor
    }


    public static LandingFragment newInstance() {
        LandingFragment fragment = new LandingFragment();
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
        View view = inflater.inflate(R.layout.fragment_landing, container, false);
        ButterKnife.bind(this, view);



        return view;
    }

    @Override
    public void setPresenter(LandingContract.Presenter presenter) {
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

    @OnClick(R.id.btn_register)
    public void register(){
        startRegisterActivity();
    }


    @OnClick(R.id.btn_login)
    public void login(){
        startLoginActivity();
    }

    @Override
    public void startRegisterActivity() {
        RegisterActivity.start(getActivity());
    }

    @Override
    public void startLoginActivity() {
        LoginActivity.start(getActivity());
    }
}
