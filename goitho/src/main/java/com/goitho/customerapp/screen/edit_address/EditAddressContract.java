package com.goitho.customerapp.screen.edit_address;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by Skull on 29/11/2017.
 */

public interface EditAddressContract {
    interface View extends BaseView<Presenter> {
        void startRegisterSuccessActivity();
        void startDialogLibraryCapture();
        void showError();
        void startCamera();
        void startGallery();
    }

    interface Presenter extends BasePresenter {

    }
}
