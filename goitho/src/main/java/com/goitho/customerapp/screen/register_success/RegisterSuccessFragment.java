package com.goitho.customerapp.screen.register_success;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.app.di.Precondition;
import com.goitho.customerapp.screen.dashboard.DashboardActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class RegisterSuccessFragment extends BaseFragment implements RegisterSuccessContract.View {

    private RegisterSuccessContract.Presenter mPresenter;


    public RegisterSuccessFragment() {
        // Required empty public constructor
    }


    public static RegisterSuccessFragment newInstance() {
        RegisterSuccessFragment fragment = new RegisterSuccessFragment();
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
        View view = inflater.inflate(R.layout.fragment_register_success, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(RegisterSuccessContract.Presenter presenter) {
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

    @OnClick(R.id.btn_gohome)
    public void goHome() {
        startDashboardActivity();
        getActivity().finishAffinity();
    }

    @OnClick(R.id.img_back)
    public void back() {
        getActivity().finish();
    }

    @Override
    public void startDashboardActivity() {
        DashboardActivity.start(getActivity());
        getActivity().finish();
    }
}
