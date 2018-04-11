package com.goitho.customerapp.screen.promotion;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.model.PromotionEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class PromotionPresenter implements PromotionContract.Presenter {

    private final String TAG = PromotionPresenter.class.getName();
    private final PromotionContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    PromotionPresenter(@NonNull PromotionContract.View view) {
        this.view = view;
    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showPromotionList(promotionList());
    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }

    @Override
    public List<PromotionEntity> promotionList() {
        List<PromotionEntity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new PromotionEntity(i, "Chương trình khuyến mãi",
                    "Mã khuyến mãi NEW034 - Tặng 50.000đ cho tài khoản đăng " +
                            "ký mới.", 1, "01/03/2018",
                    "", "", "100.000"));
        }
        return list;
    }
}
