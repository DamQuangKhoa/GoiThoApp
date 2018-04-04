package com.goitho.customerapp.screen.booking_success;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.dialogs.CustomDialogForgetPassword;
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

public class BookingSuccessFragment extends BaseFragment implements BookingSuccessContract.View {
    private final String TAG = BookingSuccessFragment.class.getName();
    private BookingSuccessContract.Presenter mPresenter;

    @Bind(R.id.et_id)
    EditText etId;

    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.txt_forgot)
    TextView txtForgot;

    @Bind(R.id.btn_login)
    Button btnLogin;


    public BookingSuccessFragment() {
        // Required empty public constructor
    }


    public static BookingSuccessFragment newInstance() {
        BookingSuccessFragment fragment = new BookingSuccessFragment();
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
        View view = inflater.inflate(R.layout.fragment_booking_success, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void setPresenter(BookingSuccessContract.Presenter presenter) {
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





}
