package com.goitho.customerapp.screen.edit_profile;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.io.File;

/**
 * Created by Skull on 29/11/2017.
 */

public interface EditProfileContract {
    interface View extends BaseView<Presenter> {
        void showError();

        void showDataToNavigation(String name, String address, String avatar, Double rating,
                                  String id, boolean isEmployee);
        void showMapData(Double latitude, Double longitude);
    }

    interface Presenter extends BasePresenter {
        void loadDataOfNavigation();
        void loadMapData();

        void uploadImage(File file);
    }
}
