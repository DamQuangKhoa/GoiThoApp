package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yen on 4/24/2018.
 */

public class BookingEntity {
    @SerializedName("orderId")
    @Expose
    private String orderId;

    @SerializedName("contentFix")
    @Expose
    private String contentFix;

    @SerializedName("dateFix")
    @Expose
    private String dateFix;

    @SerializedName("timeFix")
    @Expose
    private String timeFix;

    @SerializedName("saleId")
    @Expose
    private String saleId;

    @SerializedName("saleContent")
    @Expose
    private String saleContent;

    @SerializedName("salePrice")
    @Expose
    private String salePrice;

    @SerializedName("addressFix")
    @Expose
    private String addressFix;

    @SerializedName("phoneFix")
    @Expose
    private String phoneFix;

    @SerializedName("nameFix")
    @Expose
    private String nameFix;

    public BookingEntity() {
    }

    public String getOrderId() {
        return orderId;
    }

    public String getContentFix() {
        return contentFix;
    }

    public String getDateFix() {
        return dateFix;
    }

    public String getTimeFix() {
        return timeFix;
    }

    public String getSaleId() {
        return saleId;
    }

    public String getSaleContent() {
        return saleContent;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public String getAddressFix() {
        return addressFix;
    }

    public String getPhoneFix() {
        return phoneFix;
    }

    public String getNameFix() {
        return nameFix;
    }
}
