package com.goitho.customerapp.screen.support_center;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.app.di.Precondition;
import com.goitho.customerapp.screen.dashboard.DashboardActivity;
import com.goitho.customerapp.screen.question.QuestionActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class SupportCenterFragment extends BaseFragment implements SupportCenterContract.View {

    private SupportCenterContract.Presenter mPresenter;


    public SupportCenterFragment() {
        // Required empty public constructor
    }


    public static SupportCenterFragment newInstance() {
        SupportCenterFragment fragment = new SupportCenterFragment();
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
        View view = inflater.inflate(R.layout.fragment_support_center, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(SupportCenterContract.Presenter presenter) {
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

    @OnClick(R.id.layout_faq)
    public void question(){
        QuestionActivity.start(getContext());
    }

    @OnClick(R.id.img_back)
    public void back() {
        getActivity().finish();
    }


}
