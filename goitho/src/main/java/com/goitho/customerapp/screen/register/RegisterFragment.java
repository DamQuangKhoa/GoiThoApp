package com.goitho.customerapp.screen.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.dialogs.CustomNotiDialog;
import com.goitho.customerapp.screen.login.LoginActivity;
import com.goitho.customerapp.screen.phone_verification.PhoneVerificationActivity;
import com.goitho.customerapp.util.Precondition;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class RegisterFragment extends BaseFragment implements RegisterContract.View {

    private static final String TAG = RegisterFragment.class.getName();
    private RegisterContract.Presenter mPresenter;

    @Bind(R.id.et_username)
    EditText etUsername;

    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.et_phone)
    EditText etPhone;

    @Bind(R.id.img_cover)
    ImageView imgCover;

    @Bind(R.id.img_logo)
    ImageView imgLogo;

    @Bind(R.id.layout)
    RelativeLayout layout;

    private boolean isClicked = false;

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
        if (etUsername.getText().toString().length() < 6) {
            startDialogNoti(getActivity().getString(R.string.text_username_null));
            return;
        }
        if (etPassword.getText().toString().length() < 6) {
            startDialogNoti(getActivity().getString(R.string.text_password_null));
            return;
        }
        if (etPhone.getText().toString().length() < 10) {
            startDialogNoti(getActivity().getString(R.string.text_phone_null));
            return;
        }
        mPresenter.register(etUsername.getText().toString(), etPassword.getText().toString(),
                etPhone.getText().toString());
    }

    @OnClick(R.id.img_see_password)
    public void seePassword() {
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

    public void startDialogNoti(String content) {
        CustomNotiDialog dialog = new CustomNotiDialog();
        dialog.show(getActivity().getFragmentManager(), TAG);
        dialog.setContent(content);
    }

    @Override
    public void startPhoneVerificationActivity(int userId) {
        PhoneVerificationActivity.startFromRegister(getActivity(), userId);
        getActivity().finish();
    }

    @Override
    public void startLoginActivity() {
        LoginActivity.start(getActivity());
        getActivity().finish();
    }


    @Override
    public void showErrorAccountExists() {
        CustomNotiDialog dialog = new CustomNotiDialog();
        dialog.show(getActivity().getFragmentManager(), TAG);
        dialog.setContent(getString(R.string.text_account_exist));
        dialog.setCloseButtonText(getString(R.string.text_agree));
    }
}
