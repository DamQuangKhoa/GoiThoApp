package com.demo.architect.data.model;

import com.demo.architect.data.model.offline.ImageEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Yen on 3/27/2018.
 */

public class OrderEntity implements Serializable {
    @SerializedName("orderId")
    @Expose
    private String orderId;

    @SerializedName("orderName")
    @Expose
    private String orderName;

    @SerializedName("Address")
    @Expose
    private String Address;

    @SerializedName("surveyTime")
    @Expose
    private String surveyTime;

    @SerializedName("contructionTime")
    @Expose
    private String contructionTime;

    @SerializedName("acceptanceTime")
    @Expose
    private String acceptanceTime;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("customerName")
    @Expose
    private String customerName;

    @SerializedName("customerPhone")
    @Expose
    private String customerPhone;

    @SerializedName("customerAddress")
    @Expose
    private String customerAddress;

    @SerializedName("listImage")
    @Expose
    private ArrayList<ImageEntity> listImage;

    @SerializedName("orderContent")
    @Expose
    private String orderContent;

    @SerializedName("latitudeOrder")
    @Expose
    private String latitudeOrder;

    @SerializedName("longitudeOrder")
    @Expose
    private String longitudeOrder;

    @SerializedName("latitudeCustomer")
    @Expose
    private String latitudeCustomer;

    @SerializedName("longitudeCustomer")
    @Expose
    private String longitudeCustomer;

    @SerializedName("rateContent")
    @Expose
    private String rateContent;

    @SerializedName("ratePoint")
    @Expose
    private float ratePoint;

    @SerializedName("rateContentCustomer")
    @Expose
    private String rateContentCustomer;

    @SerializedName("ratePointCustomer")
    @Expose
    private String ratePointCustomer;

    @SerializedName("orderValue")
    @Expose
    private String orderValue;

    public String getLatitudeCustomer() {
        return latitudeCustomer;
    }

    public void setLatitudeCustomer(String latitudeCustomer) {
        this.latitudeCustomer = latitudeCustomer;
    }

    public String getLongitudeCustomer() {
        return longitudeCustomer;
    }

    public void setLongitudeCustomer(String longitudeCustomer) {
        this.longitudeCustomer = longitudeCustomer;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public String getRateContent() {
        return rateContent;
    }

    public void setRateContent(String rateContent) {
        this.rateContent = rateContent;
    }

    public float getRatePoint() {
        return ratePoint;
    }

    public void setRatePoint(float ratePoint) {
        this.ratePoint = ratePoint;
    }

    public String getRateContentCustomer() {
        return rateContentCustomer;
    }

    public void setRateContentCustomer(String rateContentCustomer) {
        this.rateContentCustomer = rateContentCustomer;
    }

    public String getRatePointCustomer() {
        return ratePointCustomer;
    }

    public void setRatePointCustomer(String ratePointCustomer) {
        this.ratePointCustomer = ratePointCustomer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(String surveyTime) {
        this.surveyTime = surveyTime;
    }

    public String getContructionTime() {
        return contructionTime;
    }

    public void setContructionTime(String contructionTime) {
        this.contructionTime = contructionTime;
    }

    public String getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setAcceptanceTime(String acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public ArrayList<ImageEntity> getListImage() {
        return listImage;
    }

    public void setListImage(ArrayList<ImageEntity> listImage) {
        this.listImage = listImage;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getLatitudeOrder() {
        return latitudeOrder;
    }

    public void setLatitudeOrder(String latitudeOrder) {
        this.latitudeOrder = latitudeOrder;
    }

    public String getLongitudeOrder() {
        return longitudeOrder;
    }

    public void setLongitudeOrder(String longitudeOrder) {
        this.longitudeOrder = longitudeOrder;
    }
}
