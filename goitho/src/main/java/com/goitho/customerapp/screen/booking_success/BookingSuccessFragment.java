package com.goitho.customerapp.screen.booking_success;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.screen.dashboard.DashboardActivity;
import com.goitho.customerapp.screen.register.RegisterActivity;
import com.goitho.customerapp.util.Precondition;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class BookingSuccessFragment extends BaseFragment implements BookingSuccessContract.View {
    private final String TAG = BookingSuccessFragment.class.getName();
    private BookingSuccessContract.Presenter mPresenter;


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

    @OnClick(R.id.layout_go_home)
    public void goHome() {
        DashboardActivity.start(getContext());
        getActivity().finish();
    }

    @OnClick(R.id.layout_register)
    public void register() {
        RegisterActivity.start(getContext());
    }


}
