package com.goitho.customerapp.screen.list_promotion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.PromotionAdapter;
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

public class ListPromotionFragment extends BaseFragment implements ListPromotionContract.View {

    private ListPromotionContract.Presenter mPresenter;
    public final String TAG = ListPromotionFragment.class.getName();


    @Bind(R.id.lvPromotion)
    ListView lvPromotion;


    public ListPromotionFragment() {
        // Required empty public constructor
    }

    public static ListPromotionFragment newInstance() {
        ListPromotionFragment fragment = new ListPromotionFragment();
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
        View view = inflater.inflate(R.layout.fragment_list_promotion, container, false);
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

        PromotionAdapter adapter = new PromotionAdapter(
                getActivity(), arr
        );
        makeLog("Adapter: " + adapter);
        lvPromotion.setAdapter(adapter);


    }

    private void makeLog(String message) {
        Log.e(TAG, message);
    }

    private void getIntent() {

    }

    @Override
    public void setPresenter(ListPromotionContract.Presenter presenter) {
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
    public void back() {
        getActivity().finish();
    }

}
