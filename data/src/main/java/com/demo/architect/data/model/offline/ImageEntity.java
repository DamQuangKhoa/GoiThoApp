package com.demo.architect.data.model.offline;

import android.graphics.Bitmap;

/**
 * Created by Skull on 30/11/2017.
 */

public class ImageEntity {
    private String uri;
    private Bitmap bitmap;
    private Integer rscImage;

    public ImageEntity(Integer rscImage) {
        this.rscImage = rscImage;
    }



    public Integer getRscImage() {
        return rscImage;
    }

    public void setRscImage(Integer rscImage) {
        this.rscImage = rscImage;
    }

    public ImageEntity(String uri) {
        this.uri = uri;
    }

    public ImageEntity(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
