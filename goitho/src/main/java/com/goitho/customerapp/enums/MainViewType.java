package com.goitho.customerapp.enums;

/**
 * Created by admin on 4/25/17.
 */

public enum MainViewType {
    COUPON(0),
    PROMOTION(1),
    INFORMATION(2),
    ALERT(3),
    PRODUCT(4),
    HEADER(5);

    private int type;

    MainViewType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static MainViewType getTypeFromInt(int type) {
        for (MainViewType mainViewType : MainViewType.values()) {
            if (mainViewType.getType() == type) {
                return mainViewType;
            }
        }
        return null;
    }
}

