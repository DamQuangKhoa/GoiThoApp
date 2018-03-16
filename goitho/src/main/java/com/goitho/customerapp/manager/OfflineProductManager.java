package com.goitho.customerapp.manager;



import com.demo.architect.data.model.offline.ProductEntity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Skull on 02/12/2017.
 */

public class OfflineProductManager {
    private static OfflineProductManager instance;
    private ArrayList<ProductEntity> list;

    public static synchronized OfflineProductManager getInstance() {
        if (instance == null) {
            instance = new OfflineProductManager();
        }
        return instance;
    }

    public void initList(){
        list = new ArrayList<>();
        list.add(new ProductEntity("Khoai lang","PRODUCT001"));
        list.add(new ProductEntity("Cà rốt","PRODUCT002"));
        list.add(new ProductEntity("Rau dền","PRODUCT003"));
        list.add(new ProductEntity("Cà chua","PRODUCT004"));
        list.add(new ProductEntity("Dưa leo","PRODUCT005"));
    }

    public ArrayList<ProductEntity> loadList(){
        return list;
    }

    public void addProduct(ProductEntity productEntity){
        list.add(productEntity);
    }

    public ArrayList<String> toStringProduct(){
        ArrayList<String> listString = new ArrayList<>();
        for (ProductEntity item: list) {
            listString.add(item.getNameProduct() + " - " + item.getIdProduct());
        }
        return listString;
    }

    public ProductEntity getRandomProduct(){
        Random generator = new Random();
        int i = generator.nextInt(list.size());
        return list.get(i);
    }
}
