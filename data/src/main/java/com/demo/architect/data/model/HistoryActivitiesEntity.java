package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Skull on 08/01/2018.
 */

public class HistoryActivitiesEntity {
    @SerializedName("rows")
    @Expose
    private List<ActivityEntity> activities;

    public List<ActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityEntity> activities) {
        this.activities = activities;
    }
}
