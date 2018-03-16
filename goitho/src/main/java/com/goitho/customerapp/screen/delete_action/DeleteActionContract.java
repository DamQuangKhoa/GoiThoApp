package com.goitho.customerapp.screen.delete_action;

import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

/**
 * Created by Skull on 11/12/2017.
 */

public interface DeleteActionContract {
    interface View extends BaseView<Presenter> {
        void showError();
        void finishActivity();
    }

    interface Presenter extends BasePresenter {
        void deleteAction(String id);
        void checkPassword(String password, String action);
    }
}
