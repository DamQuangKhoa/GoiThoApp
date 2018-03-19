package com.goitho.customerapp.screen.phone_verification;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.screen.diary.DiaryActivity;
import com.goitho.customerapp.screen.farmer.FarmerActivity;
import com.goitho.customerapp.util.Precondition;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by MSI on 26/11/2017.
 */

public class PhoneVerificationFragment extends BaseFragment implements PhoneVerificationContract.View {

    private PhoneVerificationContract.Presenter mPresenter;


    @Bind(R.id.img_cover)
    ImageView imgCover;

    @Bind(R.id.img_logo)
    ImageView imgLogo;

    @Bind(R.id.layout)
    RelativeLayout layout;

    @Bind(R.id.txt_send_code)
    TextView txtSendCode;

    public PhoneVerificationFragment() {
        // Required empty public constructor
    }

    public static PhoneVerificationFragment newInstance() {
        PhoneVerificationFragment fragment = new PhoneVerificationFragment();
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
        View view = inflater.inflate(R.layout.fragment_phone_verification, container, false);
        ButterKnife.bind(this, view);
        setBackground();
        return view;
    }

    private void setBackground() {
        int heightCover = imgCover.getHeight();
        int heightLogo = imgLogo.getHeight();
        RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        relativeParams.setMargins(0, (heightCover - (heightLogo/2)), 0, 0);  // left, top, right, bottom
        layout.setLayoutParams(relativeParams);
        txtSendCode.setPaintFlags(txtSendCode.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void setPresenter(PhoneVerificationContract.Presenter presenter) {
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
