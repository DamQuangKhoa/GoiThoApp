package com.demo.architect.data.model;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Skull on 30/12/2017.
 */

public class ActivityEntity implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("parent_id")
    @Expose
    private String parentId;

    @SerializedName("farmer_id")
    @Expose
    private String farmerId;

    @SerializedName("product_id")
    @Expose
    private String productId;

    @SerializedName("product_action_id")
    @Expose
    private String productActionId;

    @SerializedName("activity_action_id")
    @Expose
    private String activityActionId;

    @SerializedName("assignee_id")
    @Expose
    private String assigneeId;

    @SerializedName("reporter_id")
    @Expose
    private String reporterId;

    @SerializedName("note")
    @Expose
    private String note;

    @SerializedName("images")
    @Expose
    private String images;

    @SerializedName("editor")
    @Expose
    private String editor;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;

    @SerializedName("product_action")
    @Expose
    private ActionEntity actionEntity;

    @SerializedName("buy_fertilizer")
    @Expose
    private ActivityBuyFertilizerEntity activityBuyFertilizerEntity;

    @SerializedName("use_fertilizer")
    @Expose
    private ActivityUseFertilizerEntity activityUseFertilizerEntity;

    @SerializedName("farmer")
    @Expose
    private FarmerEntity farmer;

    @SerializedName("product")
    @Expose
    private ProductEntity productEntity;

    public ActivityEntity(ActivityEntity item) {
        this.id = item.id;
        this.parentId = item.parentId;
        this.farmerId = item.farmerId;
        this.productId = item.productId;
        this.productActionId = item.productActionId;
        this.activityActionId = item.activityActionId;
        this.assigneeId = item.assigneeId;
        this.note = item.note;
        this.images = item.images;
        this.status = item.status;
        this.address = item.address;
        this.date = item.date;
        this.editor = item.editor;
        this.createdAt = item.createdAt;
        this.updatedAt = item.updatedAt;
        this.actionEntity = item.actionEntity;
        this.activityBuyFertilizerEntity = item.activityBuyFertilizerEntity;
        this.activityUseFertilizerEntity = item.activityUseFertilizerEntity;
    }

    public ActivityEntity() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductActionId() {
        return productActionId;
    }

    public void setProductActionId(String productActionId) {
        this.productActionId = productActionId;
    }

    public String getActivityActionId() {
        return activityActionId;
    }

    public void setActivityActionId(String activityActionId) {
        this.activityActionId = activityActionId;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateString() {
        return date;
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

    public ActionEntity getActionEntity() {
        return actionEntity;
    }

    public void setActionEntity(ActionEntity actionEntity) {
        this.actionEntity = actionEntity;
    }

    public ActivityBuyFertilizerEntity getActivityBuyFertilizerEntity() {
        return activityBuyFertilizerEntity;
    }

    public void setActivityBuyFertilizerEntity(ActivityBuyFertilizerEntity activityBuyFertilizerEntity) {
        this.activityBuyFertilizerEntity = activityBuyFertilizerEntity;
    }

    public ActivityUseFertilizerEntity getActivityUseFertilizerEntity() {
        return activityUseFertilizerEntity;
    }

    public void setActivityUseFertilizerEntity(ActivityUseFertilizerEntity activityUseFertilizerEntity) {
        this.activityUseFertilizerEntity = activityUseFertilizerEntity;
    }

    public Date getUpdatedDate() {
        if (!TextUtils.isEmpty(updatedAt)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            try {
                return format.parse(updatedAt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new Date();
    }

    public Date getDate() {

        if (!TextUtils.isEmpty(date)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            try {
                return format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (!TextUtils.isEmpty(createdAt)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            try {
                return format.parse(createdAt);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new Date();
    }

    public FarmerEntity getFarmer() {
        return farmer;
    }

    public void setFarmer(FarmerEntity farmer) {
        this.farmer = farmer;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }
}
