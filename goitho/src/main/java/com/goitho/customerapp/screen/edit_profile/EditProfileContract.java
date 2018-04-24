package com.goitho.customerapp.screen.edit_profile;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by Skull on 29/11/2017.
 */

public interface EditProfileContract {
    interface View extends BaseView<Presenter> {
        void showError();
        void startPhoneVerificationActivity( String userId, String newPhone);
    }

    interface Presenter extends BasePresenter {
        void editAccount(String pass, String newPhoneNumber);
    }
}
