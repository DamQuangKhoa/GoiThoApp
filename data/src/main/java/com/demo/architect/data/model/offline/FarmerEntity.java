package com.demo.architect.data.model.offline;

/**
 * Created by Skull on 27/11/2017.
 */

public class FarmerEntity {
    private String name;
    private String address;
    private String id;
    private String imageUri;

    public FarmerEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
