package com.demo.architect.data.model.offline;

/**
 * Created by Skull on 01/12/2017.
 */

public class ProductEntity {
    private String nameProduct;
    private String idProduct;

    public ProductEntity() {
    }

    public ProductEntity(String nameProduct, String idProduct) {
        this.nameProduct = nameProduct;
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }
}
