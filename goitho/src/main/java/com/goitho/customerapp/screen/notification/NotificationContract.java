package com.goitho.customerapp.screen.notification;

import com.demo.architect.data.model.NotificationEntity;
import com.goitho.customerapp.app.base.BasePresenter;
import com.goitho.customerapp.app.base.BaseView;

import java.util.List;

/**
 * Created by MSI on 26/11/2017.
 */

public interface NotificationContract {
    interface View extends BaseView<Presenter> {
        void startDialogNotification();
        void showListNotification(List<NotificationEntity> mList);
    }

    interface Presenter extends BasePresenter {
        List<NotificationEntity> notificationList();
    }
}
