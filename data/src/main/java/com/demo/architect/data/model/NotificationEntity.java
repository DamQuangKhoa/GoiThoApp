package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yen on 3/26/2018.
 */

public class NotificationEntity {
    @SerializedName("notificationId")
    @Expose
    private String notificationId;
    @SerializedName("notificationName")
    @Expose
    private String notificationName;
    @SerializedName("notificationContent")
    @Expose
    private String notificationContent;
    @SerializedName("status")
    @Expose
    private Integer status;

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationName() {
        return notificationName;
    }

    public void setNotificationName(String notificationName) {
        this.notificationName = notificationName;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
