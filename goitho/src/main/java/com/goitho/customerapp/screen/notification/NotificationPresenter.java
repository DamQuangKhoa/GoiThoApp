package com.goitho.customerapp.screen.notification;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.architect.data.model.NotificationEntity;
import com.demo.architect.data.repository.base.local.LocalRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by MSI on 26/11/2017.
 */

public class NotificationPresenter implements NotificationContract.Presenter {

    private final String TAG = NotificationPresenter.class.getName();
    private final NotificationContract.View view;

    @Inject
    LocalRepository localRepository;

    @Inject
    NotificationPresenter(@NonNull NotificationContract.View view) {
        this.view = view;

    }

    @Inject
    public void setupPresenter() {
        view.setPresenter(this);
    }


    @Override
    public void start() {
        Log.d(TAG, TAG + ".start() called");
        view.showListNotification(notificationList());

    }

    @Override
    public void stop() {
        Log.d(TAG, TAG + ".stop() called");
    }


    @Override
    public List<NotificationEntity> notificationList() {
        List<NotificationEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setNotificationContent("Chọn thợ thành công");
            notificationEntity.setNotificationName("Đơn hàng ABC");
            list.add(notificationEntity);
        }
        return list;
    }
}
