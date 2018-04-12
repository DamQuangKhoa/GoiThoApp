package com.goitho.customerapp.screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.architect.data.helper.SharedPreferenceHelper;
import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.dialogs.CustomDialogForgetPassword;
import com.goitho.customerapp.dialogs.CustomNotiDialog;
import com.goitho.customerapp.screen.dashboard.DashboardActivity;
import com.goitho.customerapp.screen.phone_verification.PhoneVerificationActivity;
import com.goitho.customerapp.screen.register.RegisterActivity;
import com.goitho.customerapp.util.Precondition;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {
    private final String TAG = LoginFragment.class.getName();
    private LoginContract.Presenter mPresenter;

    @Bind(R.id.et_phone)
    EditText etPhone;

    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.txt_forgot)
    TextView txtForgot;

    @Bind(R.id.btn_login)
    Button btnLogin;

    private boolean isClicked = false;

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
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

    @OnClick(R.id.btn_login)
    public void login() {
        SharedPreferenceHelper.getInstance(getContext()).pushBoolean(Constants.KEY_CHECK_LOGIN, true);
        startDashboardActivity();
    }


    @OnClick(R.id.txt_register)
    public void register() {
        startRegisterActivity();
    }


    @OnClick(R.id.txt_forgot)
    public void forgetPass() {
        startDialogForgetPass();
    }

    @OnClick(R.id.img_see_password)
    public void showPassword() {
        if (!isClicked) {
            isClicked = true;
        } else {
            isClicked = false;
        }
        etPassword.setInputType(
                !isClicked ? InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_TEXT);
        etPassword.setTransformationMethod(
                !isClicked ? PasswordTransformationMethod.getInstance() : null);
    }

    @Override
    public void startRegisterActivity() {
        RegisterActivity.start(getActivity());
    }

    @Override
    public void startDashboardActivity() {
        DashboardActivity.start(getContext());
        getActivity().finishAffinity();
    }

    @Override
    public void startDialogForgetPass() {
        CustomDialogForgetPassword dialog = new CustomDialogForgetPassword();
        dialog.show(getActivity().getFragmentManager(), TAG);
        dialog.setListener(new CustomDialogForgetPassword.OnClickListener() {
            @Override
            public void onClick(String username, String phone, String newPassword) {
                mPresenter.resetPassword(username, phone, newPassword);
            }
        });

    }

    @Override
    public void startPhoneVerificationActivity(int userId, String newPassword) {
        PhoneVerificationActivity.startFromResetPassword(getActivity(), userId, newPassword);
    }

    @Override
    public void showError() {
        CustomNotiDialog notiDialog = new CustomNotiDialog();
        notiDialog.show(getActivity().getFragmentManager(), TAG);
        notiDialog.setCloseButtonText(getString(R.string.text_sweet_dialog_confirm_text));
        notiDialog.setContent(getString(R.string.text_sweet_dialog_check_username_password));
    }

    @Override
    public void showErrorResetPassword() {
        CustomNotiDialog notiDialog = new CustomNotiDialog();
        notiDialog.show(getActivity().getFragmentManager(), TAG);
        notiDialog.setCloseButtonText(getString(R.string.text_sweet_dialog_confirm_text));
        notiDialog.setContent(getString(R.string.text_sweet_dialog_error));
    }


}
