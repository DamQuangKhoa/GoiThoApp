package com.goitho.customerapp.screen.edit_profile;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.screen.phone_verification.PhoneVerificationActivity;
import com.goitho.customerapp.util.Precondition;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Skull on 29/11/2017.
 */

public class EditProfileFragment extends BaseFragment implements EditProfileContract.View {

    private EditProfileContract.Presenter mPresenter;

    @Bind(R.id.txt_back)
    TextView txtBack;

    @Bind(R.id.et_username)
    EditText etUsername;

    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.et_phone)
    EditText etPhone;

    @Bind(R.id.img_logo)
    ImageView imgLogo;

    @Bind(R.id.layout)
    RelativeLayout layout;


    public EditProfileFragment() {
        // Required empty public constructor
    }


    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        ButterKnife.bind(this, view);
        setBackground();
        return view;
    }

    private void setBackground() {
        RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        int heightLogo = imgLogo.getDrawable().getIntrinsicHeight();
        relativeParams.setMargins(0, (heightLogo / 2), 0, 0);  // left, top, right, bottom
        layout.setLayoutParams(relativeParams);
        txtBack.setPaintFlags(txtBack.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }


    @OnClick(R.id.txt_back)
    public void back() {
        getActivity().finish();
    }

    @OnClick(R.id.btn_update)
    public void updateProfile() {
        PhoneVerificationActivity.startFromResetPhone(getContext(),0,"");
    }

    @Override
    public void setPresenter(EditProfileContract.Presenter presenter) {
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

    @Override
    public void showError() {

    }

}
