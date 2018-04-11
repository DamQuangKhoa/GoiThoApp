package com.demo.architect.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromotionEntity {
    @SerializedName("promotionId")
    @Expose
    private int promotionId;

    @SerializedName("promotionName")
    @Expose
    private String promotionName;

    @SerializedName("promotionContent")
    @Expose
    private String promotionContent;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("promotionDate")
    @Expose
    private String promotionDate;

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    @SerializedName("rule")
    @Expose
    private String rule;

    @SerializedName("bonus")
    @Expose
    private String bonus;


    public PromotionEntity() {
    }

    public PromotionEntity(int promotionId, String promotionName, String promotionContent,
                           int status, String promotionDate, String imageUrl, String rule, String bonus) {
        this.promotionId = promotionId;
        this.promotionName = promotionName;
        this.promotionContent = promotionContent;
        this.status = status;
        this.promotionDate = promotionDate;
        this.imageUrl = imageUrl;
        this.rule = rule;
        this.bonus = bonus;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public String getPromotionContent() {
        return promotionContent;
    }

    public int getStatus() {
        return status;
    }

    public String getPromotionDate() {
        return promotionDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRule() {
        return rule;
    }

    public String getBonus() {
        return bonus;
    }

    public String getPromotionName() {
        return promotionName;
    }
}


