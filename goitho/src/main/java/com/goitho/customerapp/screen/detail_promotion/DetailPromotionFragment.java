package com.goitho.customerapp.screen.detail_promotion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.architect.data.model.PromotionEntity;
import com.goitho.customerapp.R;
import com.goitho.customerapp.app.base.BaseFragment;
import com.goitho.customerapp.screen.support_center.SupportCenterActivity;
import com.goitho.customerapp.util.Precondition;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Mind on 11/12/2017.
 */

public class DetailPromotionFragment extends BaseFragment implements DetailPromotionContract.View {

    private DetailPromotionContract.Presenter mPresenter;

    @Bind(R.id.txt_id_promotion)
    TextView txtPromotionCode;

    @Bind(R.id.txt_title)
    TextView txtTitle;

    @Bind(R.id.txt_expiry_date)
    TextView txtExpiryDate;

    @Bind(R.id.txt_rule)
    TextView txtRule;

    @Bind(R.id.iv_cover)
    ImageView ivCover;

    private String promotionId;

    public DetailPromotionFragment() {
        // Required empty public constructor
    }

    public static DetailPromotionFragment newInstance() {
        DetailPromotionFragment fragment = new DetailPromotionFragment();
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
        View view = inflater.inflate(R.layout.fragment_detail_promotion, container, false);
        promotionId = getActivity().getIntent().getStringExtra(DetailPromotionActivity.KEY_PROMOTION_ID);
        ButterKnife.bind(this, view);
        initVars();

        return view;
    }

    private void initVars() {
        mPresenter.getDetailPromotion(promotionId);
    }

    @Override
    public void setPresenter(DetailPromotionContract.Presenter presenter) {
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
    public void showPromotion(PromotionEntity promotion) {
        txtTitle.setText(promotion.getPromotionName());
        txtPromotionCode.setText(Html.fromHtml("<b>" + getActivity().getString(R.string.text_id_promotion_booking) + " "
                + promotion.getPromotionId() + "</b> " + promotion.getPromotionContent()));
        txtExpiryDate.setText(promotion.getPromotionDate());
        txtRule.setText(promotion.getRule());
        Picasso.with(getContext()).load(promotion.getImageUrl()).into(ivCover);

    }


    @OnClick(R.id.img_back)
    public void back() {
        getActivity().finish();
    }

    @OnClick(R.id.layout_support_center)
    public void supportCenter() {
        SupportCenterActivity.start(getContext());
    }
}
