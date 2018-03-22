package com.goitho.customerapp.screen.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.screen.login.LoginActivity;
import com.goitho.customerapp.screen.phone_verification.PhoneVerificationActivity;
import com.goitho.customerapp.util.Precondition;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by MSI on 26/11/2017.
 */

public class RegisterFragment extends BaseFragment implements RegisterContract.View {

    private RegisterContract.Presenter mPresenter;


    @Bind(R.id.img_cover)
    ImageView imgCover;

    @Bind(R.id.img_logo)
    ImageView imgLogo;

    @Bind(R.id.layout)
    RelativeLayout layout;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
        setBackground();
        return view;
    }

    private void setBackground() {
        int heightCover = imgCover.getDrawable().getIntrinsicHeight();
        int heightLogo = imgLogo.getDrawable().getIntrinsicHeight();
        RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        relativeParams.setMargins(0, (heightCover - (heightLogo / 2)), 0, 0);  // left, top, right, bottom
        layout.setLayoutParams(relativeParams);
    }


    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
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

    @OnClick(R.id.txt_login)
    public void login() {
        startLoginActivity();
    }

    @OnClick(R.id.btn_register)
    public void register() {
        startVerificationActivity();
    }

    @Override
    public void startLoginActivity() {
        LoginActivity.start(getActivity());
    }

    @Override
    public void startVerificationActivity() {
        PhoneVerificationActivity.start(getActivity());
    }


    @Override
    public void showError() {
        Activity activity = getActivity();
        if (activity != null) {
            new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(getString(R.string.text_sweet_dialog_title))
                    .setContentText(getString(R.string.text_sweet_dialog_check_username_password))
                    .setConfirmText(getString(R.string.text_sweet_dialog_confirm_text))
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .show();
        }
    }
}
