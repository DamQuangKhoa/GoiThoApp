package com.goitho.customerapp.screen.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.architect.data.model.PointEntity;
import com.demo.architect.data.model.UserEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.dialogs.CustomDialogLogOut;
import com.goitho.customerapp.screen.dashboard.DashboardActivity;
import com.goitho.customerapp.screen.edit_address.EditAddressActivity;
import com.goitho.customerapp.screen.edit_profile.EditProfileActivity;
import com.goitho.customerapp.util.Precondition;
import com.goitho.customerapp.widgets.CircleTransform;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by MSI on 26/11/2017.
 */

public class UserFragment extends BaseFragment implements UserContract.View {

    private static final String TAG = UserFragment.class.getName();
    private UserContract.Presenter mPresenter;

    @Bind(R.id.txt_username)
    TextView txtUsername;

    @Bind(R.id.txt_name2)
    TextView txtName2;

    @Bind(R.id.txt_address1)
    TextView txtAddress;

    @Bind(R.id.txt_address2)
    TextView txtAddress2;

    @Bind(R.id.img_avatar)
    ImageView imgAvatar;

    @Bind(R.id.txt_phone)
    TextView txtPhone;

    @Bind(R.id.txt_money)
    TextView txtMoney;

    @Bind(R.id.txt_success)
    TextView txtSuccess;

    @Bind(R.id.txt_point)
    TextView txtPoint;

    @Bind(R.id.txt_password)
    TextView txtPassword;

    @Bind(R.id.txt_name)
    TextView txtName;

    @Bind(R.id.txt_email)
    TextView txtEmail;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
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
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void setPresenter(UserContract.Presenter presenter) {
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

    @OnClick(R.id.btn_edit_address)
    public void editAddress() {
        startEditAddressActivity();
    }


    @OnClick(R.id.btn_edit_info)
    public void editInfo() {
        startEditProfileActivity();
    }

    @OnClick(R.id.layout_add_address)
    public void addAddress() {

    }

    @OnClick(R.id.txt_logout)
    public void logout() {
        startDialogLogOut();
    }

    @Override
    public void startEditProfileActivity() {
        EditProfileActivity.start(getContext());
    }

    @Override
    public void startEditAddressActivity() {
        EditAddressActivity.start(getContext());
    }

    @Override
    public void startDialogLogOut() {
        CustomDialogLogOut dialog = new CustomDialogLogOut();
        dialog.show(getActivity().getFragmentManager(), TAG);
        dialog.setListener(new CustomDialogLogOut.OnYesClickListener() {
            @Override
            public void onYesClick() {
                mPresenter.logout();
            }
        });
    }

    @Override
    public void startDashboardActivity() {
        DashboardActivity.start(getContext());
        getActivity().finish();
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

    @Override
    public void showInfoUser(UserEntity user) {
        txtUsername.setText(getActivity().getString(R.string.text_username)+": "+user.getUserName());
        txtName2.setText(getActivity().getString(R.string.text_name)+": "+user.getUserFullName());
        Picasso.with(getContext()).load(user.getAvatarImageUrl()).transform(new CircleTransform()).into(imgAvatar);
        txtAddress.setText(getActivity().getString(R.string.text_address)+": "+user.getAddress1());
        txtAddress2.setText(getActivity().getString(R.string.text_address2)+": "+user.getAddress2());
        txtEmail.setText(getActivity().getString(R.string.text_email)+": "+user.getEmail());
        txtPhone.setText(getActivity().getString(R.string.text_phone)+": "+user.getMobilePhone());
        txtName.setText(getActivity().getString(R.string.text_name)+": "+user.getUserFullName());

    }

    @Override
    public void showInfoPoint(PointEntity pointEntity) {
        txtSuccess.setText(pointEntity.getTotalSuccess()+"");
        txtMoney.setText(pointEntity.getTotalSuccess()+" vnđ");
        txtPoint.setText(pointEntity.getTotalPoint()+"");
    }
}
