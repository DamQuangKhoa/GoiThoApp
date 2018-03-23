package com.goitho.customerapp.screen.edit_address;

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
import com.goitho.customerapp.dialogs.CustomDialogLibraryCapture;
import com.goitho.customerapp.util.Precondition;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Skull on 29/11/2017.
 */

public class EditAddressFragment extends BaseFragment implements EditAddressContract.View {

    private static final String TAG = EditAddressFragment.class.getName();
    private EditAddressContract.Presenter mPresenter;

    @Bind(R.id.et_name)
    EditText etName;

    @Bind(R.id.et_address1)
    EditText etAddress1;

    @Bind(R.id.et_address2)
    EditText etAddress2;

    @Bind(R.id.et_email)
    EditText etEmail;


    public EditAddressFragment() {
        // Required empty public constructor
    }


    public static EditAddressFragment newInstance() {
        EditAddressFragment fragment = new EditAddressFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_address, container, false);
        ButterKnife.bind(this, view);
        return view;
    }



    @OnClick(R.id.btn_complete)
    public void complete() {
        getActivity().finish();
    }

    @OnClick(R.id.layoutCapture)
    public void capture(){

    }

    @Override
    public void setPresenter(EditAddressContract.Presenter presenter) {
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
    public void startDialogLibraryCapture() {
        CustomDialogLibraryCapture dialog = new CustomDialogLibraryCapture();
        dialog.show(getActivity().getFragmentManager(), TAG);

    }

    @Override
    public void showError() {

    }

}
