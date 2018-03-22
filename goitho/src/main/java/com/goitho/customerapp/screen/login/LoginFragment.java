package com.goitho.customerapp.screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.dialogs.CustomDialogForgetPassword;
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

    @Bind(R.id.et_id)
    EditText etId;

    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.txt_forgot)
    TextView txtForgot;

    @Bind(R.id.btn_login)
    Button btnLogin;


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

    }


    @OnClick(R.id.txt_register)
    public void register() {
        startRegisterActivity();
    }


    @OnClick(R.id.txt_forgot)
    public void forgetPass() {
        startDialogForgetPass();
    }

    @Override
    public void startRegisterActivity() {
        RegisterActivity.start(getActivity());
    }

    @Override
    public void startDialogForgetPass() {
        CustomDialogForgetPassword dialog = new CustomDialogForgetPassword();
        dialog.show(getActivity().getFragmentManager(), TAG);
        dialog.setListener(new CustomDialogForgetPassword.OnClickListener() {
            @Override
            public void onClick(String username, String phone) {
                Toast.makeText(getActivity(), username + " " + phone, Toast.LENGTH_SHORT).show();
                PhoneVerificationActivity.start(getActivity());
            }
        });

    }


}
