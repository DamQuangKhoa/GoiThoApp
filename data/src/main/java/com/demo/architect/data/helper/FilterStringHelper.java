package com.demo.architect.data.helper;

/**
 * Created by Skull on 07/01/2018.
 */

public class FilterStringHelper {

    public static String getFilterString(String key, String value) {
        return value != null
                ? ("{\"" + key + "\":{\"$eq\":\"" + value + "\"}}")
                : ("{\"" + key + "\":{\"$eq\": null}}");
    }

    public static String mergeFilterStrings(String filterString1, String filterString2) {
        return filterString1.substring(0, filterString1.length() - 1) + ", "
                + filterString2.substring(1, filterString2.length());
    }
}
