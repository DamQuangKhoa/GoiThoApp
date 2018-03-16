package com.goitho.customerapp.manager;

import com.demo.architect.data.model.EmployeeEntity;

import java.util.List;

/**
 * Created by Skull on 29/01/2018.
 */

public class EmployeeManager {
    private static EmployeeManager instance;
    private List<EmployeeEntity> list;

    public static synchronized EmployeeManager getInstance() {
        if (instance == null) {
            instance = new EmployeeManager();
        }
        return instance;
    }

    public void setList(List<EmployeeEntity> list){
        this.list = list;
    }

    public List<EmployeeEntity> getList(){
        return list;
    }

    public String getNameEmployeeById (String id){
        if(list != null){
            for (EmployeeEntity entity : list) {
                if(entity.getId().equals(id)){
                    return entity.getName();
                }
            }
        }
        return "";
    }
}
