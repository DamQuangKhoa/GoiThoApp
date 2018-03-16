package com.goitho.customerapp.screen.delete_action;

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

public class DeleteActionFragment extends BaseFragment implements DeleteActionContract.View {

    private DeleteActionContract.Presenter mPresenter;

    @Bind(R.id.et_password)
    EditText etPassword;

    @Bind(R.id.txt_question)
    TextView txtQuestion;

    @Bind(R.id.txt_description)
    TextView txtDescription;

    private String action;

    public DeleteActionFragment() {
        // Required empty public constructor
    }


    public static DeleteActionFragment newInstance() {
        DeleteActionFragment fragment = new DeleteActionFragment();
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
        View view = inflater.inflate(R.layout.fragment_delete_action, container, false);
        ButterKnife.bind(this, view);
        getIntent();

        return view;
    }

    private void getIntent() {
        if(getActivity().getIntent() != null) {
            action = getActivity().getIntent().getStringExtra(Constants.KEY_DELETE_ACTION_ACTIVITY);
            String[] strings = action.split(" - ");
            txtQuestion.setText(getResources().getString(R.string.text_delete_action_question) +
                    " " + strings[0] + " " + " ?");
            txtDescription.setText(getResources().getString(R.string.text_delete_action_description) +
                    " " + strings[0] + " " +
                    getResources().getString(R.string.text_will_be_deleted));
        }
        etPassword.setText("123456");
    }

    @Override
    public void setPresenter(DeleteActionContract.Presenter presenter) {
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

    @OnClick(R.id.img_back)
    public void back(){
        getActivity().finish();
    }

    @OnClick(R.id.btn_delete)
    public void deleteAction(){
        if(!TextUtils.isEmpty(etPassword.getText())) {
            mPresenter.checkPassword(etPassword.getText().toString(), action);
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
}
