package com.demo.architect.data.model;

import com.demo.architect.data.model.offline.ImageEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Skull on 13/01/2018.
 */

public class UploadEntity {
    @SerializedName("imageList")
    @Expose
    private List<ImageEntity> imageList;

    public List<ImageEntity> getImageList() {
        return imageList;
    }
}
