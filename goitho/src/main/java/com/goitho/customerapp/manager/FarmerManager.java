package com.goitho.customerapp.manager;

import com.demo.architect.data.model.FarmerEntity;

import java.util.List;

/**
 * Created by Skull on 07/01/2018.
 */

public class FarmerManager {
    private static FarmerManager instance;
    private List<FarmerEntity> list;

    public static synchronized FarmerManager getInstance() {
        if (instance == null) {
            instance = new FarmerManager();
        }
        return instance;
    }

    public void setList(List<FarmerEntity> list){
        this.list = list;
    }

    public List<FarmerEntity> getList(){
        return list;
    }

    public String getNameFarmerById (String id){
        if(list != null){
            for (FarmerEntity entity : list) {
                if(entity.getId().equals(id)){
                    return entity.getName();
                }
            }
        }
        return "";
    }
}
