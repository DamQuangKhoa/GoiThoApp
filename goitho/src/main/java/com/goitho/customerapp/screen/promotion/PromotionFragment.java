package com.goitho.customerapp.screen.promotion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.architect.data.model.PromotionEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.adapter.PromotionAdapter;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.app.di.Precondition;
import com.goitho.customerapp.manager.ListPromotionsManager;
import com.goitho.customerapp.screen.detail_promotion.DetailPromotionActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class PromotionFragment extends BaseFragment implements PromotionContract.View {

    private PromotionContract.Presenter mPresenter;

    private PromotionAdapter adapter;

    @Bind(R.id.rv_promotion)
    RecyclerView rvPromotion;

    public PromotionFragment() {
        // Required empty public constructor
    }


    public static PromotionFragment newInstance() {
        PromotionFragment fragment = new PromotionFragment();
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
        View view = inflater.inflate(R.layout.fragment_promotion, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        adapter = new PromotionAdapter(new ArrayList<PromotionEntity>(), getContext(),
                new PromotionAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(PromotionEntity item) {
                        DetailPromotionActivity.start(getContext(), item.getPromotionId());
                    }
                });
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext());
        rvPromotion.setLayoutManager(layoutManager);
        rvPromotion.setAdapter(adapter);
    }


    @Override
    public void setPresenter(PromotionContract.Presenter presenter) {
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
    public void back() {
        getActivity().finish();
    }


    @Override
    public void showPromotionList() {
        adapter.setData(ListPromotionsManager.getInstance().getListPromotions());
    }

    @Override
    public void showError() {

    }
}
