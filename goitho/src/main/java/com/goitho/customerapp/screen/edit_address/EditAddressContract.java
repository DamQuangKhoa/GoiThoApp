package com.goitho.customerapp.screen.edit_address;

import android.graphics.Bitmap;

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

        void editProfile(Bitmap avatar, String fullName, String address1, String address2, String email);
    }
}
