package com.goitho.customerapp.manager;

import com.demo.architect.data.model.ActionEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skull on 13/12/2017.
 */

public class ActionManager {
    private static ActionManager instance;
    private List<ActionEntity> listAction;

    public static synchronized ActionManager getInstance() {
        if (instance == null) {
            instance = new ActionManager();
        }
        return instance;
    }

    public List<ActionEntity> getListAction() {
        return listAction;
    }

    public void setListAction(List<ActionEntity> listAction) {
        this.listAction = listAction;
    }

    public  ArrayList<String> getStringListAction(){
        ArrayList<String> strings = new ArrayList<>();
        for (ActionEntity entity: listAction) {
            strings.add(entity.getName() + " - " + entity.getId());
        }
        return strings;
    }

    public boolean isBuyFertilizer(String id) {
        for ( ActionEntity entity : listAction) {
            if(entity.getId().equals(id) && entity.getName().contains("Mua phân bón/thuốc")) {
                return true;
            }
        }
        return false;
    }

    public boolean isUseFertilizer(String id) {
        for ( ActionEntity entity : listAction) {
            if(entity.getId().equals(id) && entity.getName().contains("Sử dụng phân bón/thuốc")) {
                return true;
            }
        }
        return false;
    }

    public String getNameProductById(String id){
        if(listAction != null){
            for (ActionEntity entity : listAction) {
                if(entity.getId().equals(id))
                    return entity.getName();
            }
        }
        return "";
    }
}
