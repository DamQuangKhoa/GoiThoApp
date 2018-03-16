package com.demo.architect.data.model;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Skull on 30/12/2017.
 */

public class ActivityUseFertilizerEntity implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("activity_id")
    @Expose
    private String activityId;
    @SerializedName("field_id")
    @Expose
    private String fieldId;
    @SerializedName("field_area")
    @Expose
    private String fieldArea;
    @SerializedName("fertilizer")
    @Expose
    private String fertilizer;
    @SerializedName("fertilizer_id")
    @Expose
    private String fertilizerId;
    @SerializedName("dosage")
    @Expose
    private String dosage;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("goal")
    @Expose
    private String goal;
    @SerializedName("equiment")
    @Expose
    private String equiment;
    @SerializedName("isolation_time")
    @Expose
    private Integer isolationTime;
    @SerializedName("cleaned_equipment")
    @Expose
    private Boolean cleanedEquipment;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;
    @SerializedName("activity")
    @Expose
    private Object activity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldArea() {
        return fieldArea;
    }

    public void setFieldArea(String fieldArea) {
        this.fieldArea = fieldArea;
    }

    public String getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(String fertilizer) {
        this.fertilizer = fertilizer;
    }

    public String getFertilizerId() {
        return fertilizerId;
    }

    public void setFertilizerId(String fertilizerId) {
        this.fertilizerId = fertilizerId;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getEquiment() {
        return equiment;
    }

    public void setEquiment(String equiment) {
        this.equiment = equiment;
    }

    public Integer getIsolationTime() {
        return isolationTime;
    }

    public void setIsolationTime(Integer isolationTime) {
        this.isolationTime = isolationTime;
    }

    public Boolean getCleanedEquipment() {
        return cleanedEquipment;
    }

    public void setCleanedEquipment(Boolean cleanedEquipment) {
        this.cleanedEquipment = cleanedEquipment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Object getActivity() {
        return activity;
    }

    public void setActivity(Object activity) {
        this.activity = activity;
    }

    public Date getDate() {
        if (!TextUtils.isEmpty(createdAt)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            try {
                return format.parse(createdAt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new Date();
    }
}
