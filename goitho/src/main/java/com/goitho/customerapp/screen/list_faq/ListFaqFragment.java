package com.goitho.customerapp.screen.list_faq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.FAQ_Adapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.util.Precondition;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Mind on 11/12/2017.
 */

public class ListFaqFragment extends BaseFragment implements ListFaqContract.View {

    private ListFaqContract.Presenter mPresenter;
    public final String TAG = ListFaqFragment.class.getName();


    @Bind(R.id.lvFAQ)
    ListView lvFAQ;


    public ListFaqFragment() {
        // Required empty public constructor
    }

    public static ListFaqFragment newInstance() {
        ListFaqFragment fragment = new ListFaqFragment();
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
        View view = inflater.inflate(R.layout.fragment_faq, container, false);
        ButterKnife.bind(this, view);
        initListView();

        return view;
    }

    private void initListView() {
        List<String> arr = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arr.add("A");
            makeLog("Vao Init ListView");
        }

        FAQ_Adapter adapter = new FAQ_Adapter(
                getActivity(), arr
        );
        makeLog("Adapter: " + adapter);
        lvFAQ.setAdapter(adapter);


    }

    private void makeLog(String message) {
        Log.e(TAG, message);
    }

    private void getIntent() {

    }

    @Override
    public void setPresenter(ListFaqContract.Presenter presenter) {
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


    @OnClick(R.id.img_back)
    public void back() {
        getActivity().finish();
    }

}
