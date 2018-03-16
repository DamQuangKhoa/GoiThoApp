package com.goitho.customerapp.manager;

import com.demo.architect.data.model.ProductEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skull on 07/01/2018.
 */

public class ProductManager {
    private static ProductManager instance;
    private List<ProductEntity> list;

    public static synchronized ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    public void setList(List<ProductEntity> list){
        this.list = list;
    }

    public List<ProductEntity> getList() {
        return list;
    }

    public String getNameProductById(String id){
        if(list != null){
            for (ProductEntity entity : list) {
                if(entity.getId().equals(id))
                    return entity.getName();
            }
        }
        return "";
    }

    public ArrayList<String> getStringListProduct(){
        ArrayList<String> strings = new ArrayList<>();
        for (ProductEntity entity: list) {
            strings.add(entity.getName() + " - " + entity.getId());
        }
        return strings;
    }
}
