package com.goitho.customerapp.screen.order;

import com.demo.architect.data.model.offline.DiaryEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by Skull on 27/11/2017.
 */

public interface OrderContract {
    interface View extends BaseView<Presenter> {
        void showListDiary(List<DiaryEntity> list);

        void showError();

        void showDataToNavigation(String name, String address, String avatar, Double rating);

        void showListDiaryByProduct(List<DiaryEntity> listDiary);
    }

    interface Presenter extends BasePresenter {
        void loadListDiary(OrderPresenter.Filter filter, String query);

        void loadDataOfNavigation();

        void loadActivityList(String farmerId);
    }
}

