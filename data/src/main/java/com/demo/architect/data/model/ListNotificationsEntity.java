package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Yen on 4/21/2018.
 */

public class ListNotificationsEntity {
    @SerializedName("listNotification")
    @Expose
    private List<NotificationEntity> listNotification;

    public List<NotificationEntity> getListNotification() {
        return listNotification;
    }
}
