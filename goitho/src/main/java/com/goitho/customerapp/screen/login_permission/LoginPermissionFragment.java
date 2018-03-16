package com.goitho.customerapp.screen.login_permission;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.screen.login.LoginActivity;
import com.goitho.customerapp.util.Precondition;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class LoginPermissionFragment extends BaseFragment implements LoginPermissionContract.View {

    private LoginPermissionContract.Presenter mPresenter;

    @Bind(R.id.btn_employee)
    Button btnEmployee;

    @Bind(R.id.btn_farmer)
    Button btnFarmer;

    public LoginPermissionFragment() {
        // Required empty public constructor
    }


    public static LoginPermissionFragment newInstance() {
        LoginPermissionFragment fragment = new LoginPermissionFragment();
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
        View view = inflater.inflate(R.layout.fragment_login_permission, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(LoginPermissionContract.Presenter presenter) {
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

    @OnClick(R.id.btn_employee)
    public void clickEmployee(){
        LoginActivity.start(getActivity(), true);
        getActivity().finish();
    }

    @OnClick(R.id.btn_farmer)
    public void clickFarmer(){
        LoginActivity.start(getActivity(), false);
        getActivity().finish();
    }
}
