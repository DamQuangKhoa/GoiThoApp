package com.goitho.customerapp.screen.add_action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.constants.Constants;
import com.goitho.customerapp.util.Precondition;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Skull on 11/12/2017.
 */

public class AddActionFragment extends BaseFragment implements AddActionContract.View {

    private AddActionContract.Presenter mPresenter;

    @Bind(R.id.txt_product)
    TextView txtProduct;

    @Bind(R.id.et_action)
    EditText etAction;

    public AddActionFragment() {
        // Required empty public constructor
    }

    public static AddActionFragment newInstance() {
        AddActionFragment fragment = new AddActionFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_action, container, false);
        ButterKnife.bind(this, view);
        getIntent();

        return view;
    }

    private void getIntent() {
        if(getActivity().getIntent() != null) {
            txtProduct.setText((String)getActivity().getIntent().getStringExtra(
                    Constants.ADD_ACTION_ACTIVITY));
        }
    }

    @Override
    public void setPresenter(AddActionContract.Presenter presenter) {
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
        Activity activity = getActivity();
        if (activity != null) {
            new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(getString(R.string.text_sweet_dialog_title))
                    .setContentText(getString(R.string.text_sweet_dialog_error))
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
    public void finishActivity() {
        getActivity().finish();
    }

    @OnClick(R.id.img_back)
    public void back(){
        getActivity().finish();
    }

    @OnClick(R.id.btn_add)
    public void addAction() {
        if(!TextUtils.isEmpty(etAction.getText())) {
            mPresenter.createActionProduct(etAction.getText().toString());
        } else {
            Activity activity = getActivity();
            if (activity != null) {
                new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(getString(R.string.text_sweet_dialog_title))
                        .setContentText(getString(R.string.text_sweet_text_empty))
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
}
