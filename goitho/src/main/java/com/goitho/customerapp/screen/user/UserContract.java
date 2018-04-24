package com.goitho.customerapp.screen.user;

import com.demo.architect.data.model.PointEntity;
import com.demo.architect.data.model.UserEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by MSI on 26/11/2017.
 */

public interface UserContract {
    interface View extends BaseView<Presenter> {
        void startEditProfileActivity();
        void startEditAddressActivity();
        void startDialogLogOut();
        void startDashboardActivity();
        void showError();
        void showInfoUser(UserEntity userEntity);
        void showInfoPoint(PointEntity pointEntity);
    }

    interface Presenter extends BasePresenter {
        void logout();
        UserEntity getInfo();
        void getInfoPoint();
    }
}
