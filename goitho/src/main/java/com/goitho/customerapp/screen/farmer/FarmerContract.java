package com.goitho.customerapp.screen.farmer;

import com.demo.architect.data.model.FarmerEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by Skull on 27/11/2017.
 */

public interface FarmerContract {
    interface View extends BaseView<Presenter> {
        void showListFarmer(List<FarmerEntity> list);

        void showError();

        void showDataToNavigation(String name, String address, String avatar, Double rating);

        void startDiaryActivity(String farmerId);
    }

    interface Presenter extends BasePresenter {
        void loadListFarmer();

        void loadDataOfNavigation();
    }
}

