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

    }

    interface Presenter extends BasePresenter {

    }
}
