package com.goitho.customerapp.screen.user;

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
        void showContent();
    }

    interface Presenter extends BasePresenter {
        void logout();
    }
}
