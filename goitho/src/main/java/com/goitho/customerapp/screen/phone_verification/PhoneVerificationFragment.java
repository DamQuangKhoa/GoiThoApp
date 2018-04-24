package com.goitho.customerapp.screen.phone_verification;

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
import com.goitho.customerapp.dialogs.CustomNotiDialog;
import com.goitho.customerapp.screen.edit_address.EditAddressActivity;
import com.goitho.customerapp.util.Precondition;
import com.goitho.customerapp.widgets.customCodeInput.CodeInput;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class PhoneVerificationFragment extends BaseFragment implements PhoneVerificationContract.View {

    private static final String TAG = PhoneVerificationFragment.class.getName();
    private PhoneVerificationContract.Presenter mPresenter;


    @Bind(R.id.img_cover)
    ImageView imgCover;

    @Bind(R.id.img_logo)
    ImageView imgLogo;

    @Bind(R.id.layout)
    RelativeLayout layout;

    @Bind(R.id.codeInput)
    CodeInput codeInput;

    @Bind(R.id.txt_send_code)
    TextView txtSendCode;

    @Bind(R.id.txt_error_code)
    TextView txtError;

    private String userId;

    private String newPassword;

    private String newPhone;

    private int verificationType;

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
        userId = getActivity().getIntent().getStringExtra(Constants.KEY_USER_ID);
        newPhone = getActivity().getIntent().getStringExtra(PhoneVerificationActivity.KEY_NEW_PHONE);
        newPassword = getActivity().getIntent().getStringExtra(PhoneVerificationActivity.KEY_NEW_PASSWORD);
        verificationType = getActivity().getIntent().getIntExtra(PhoneVerificationActivity.KEY_VERIFICATION_TYPE, 0);
        setBackground();
        return view;
    }

    private void setBackground() {
        int heightCover = imgCover.getDrawable().getIntrinsicHeight();
        int heightLogo = imgLogo.getDrawable().getIntrinsicHeight();
        RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        relativeParams.setMargins(0, (heightCover - (heightLogo / 2)), 0, 0);  // left, top, right, bottom
        layout.setLayoutParams(relativeParams);
        txtSendCode.setPaintFlags(txtSendCode.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void initView() {
        codeInput.setOnTextChangedListener(new CodeInput.OnTextChangedListener() {
            @Override
            public void onTextChanged(int size) {
                if (size == 3) {
                    txtError.setVisibility(View.GONE);
                    codeInput.setTextColor(getActivity().getResources().getColor(R.color.lightNavyThree));
                }
            }
        });
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

    @OnClick(R.id.btn_active)
    public void active() {
        String authCode = "";
        for (int i = 0; i < codeInput.getCode().length; i++) {
            authCode += codeInput.getCode()[i];
        }
        if (authCode.length() < 4) {
            showDialog(getString(R.string.text_code_null));
            return;
        }


        if (verificationType == PhoneVerificationActivity.VERIFICATION_TYPE_REGISTER) {
            mPresenter.activeRegisterUser(userId, authCode);
        } else if (verificationType == PhoneVerificationActivity.VERIFICATION_TYPE_RESET_PASSWORD) {
            mPresenter.activeResetPassword(userId, authCode, newPassword);
        } else if (verificationType == PhoneVerificationActivity.VERIFICATION_TYPE_RESET_PHONE) {
            mPresenter.activeResetPhone(userId, authCode, newPhone);
        }
        startEditAddressActivity();
    }

    @OnClick(R.id.txt_send_code)
    public void sendActive() {
        mPresenter.sendActive(userId);
    }

    @Override
    public void startEditAddressActivity() {
        EditAddressActivity.start(getContext());
    }

    @Override
    public void showSuccessAndFinishActivity() {
        CustomNotiDialog dialog = new CustomNotiDialog();
        dialog.show(getActivity().getFragmentManager(), TAG);
        dialog.setContent(getString(R.string.text_reset_password_successfully));
        dialog.setCloseButtonText(getString(R.string.text_agree));
        dialog.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                getActivity().finish();
            }
        });
    }

    @Override
    public void showDialog(String content) {

        CustomNotiDialog dialog = new CustomNotiDialog();
        dialog.show(getActivity().getFragmentManager(), TAG);
        dialog.setContent(content);
    }

    @Override
    public void showError() {
        txtError.setVisibility(View.VISIBLE);
        codeInput.setTextColor(getActivity().getResources().getColor(R.color.tomato));
    }
}
