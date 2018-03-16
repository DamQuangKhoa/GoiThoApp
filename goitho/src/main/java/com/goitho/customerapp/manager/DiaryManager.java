package com.goitho.customerapp.manager;

import com.demo.architect.data.model.offline.DiaryEntity;

import java.util.ArrayList;

/**
 * Created by Skull on 13/12/2017.
 */

public class DiaryManager {
    private static DiaryManager instance;
    private ArrayList<DiaryEntity> list;

    public static synchronized DiaryManager getInstance() {
        if (instance == null) {
            instance = new DiaryManager();
        }
        return instance;
    }


    public ArrayList<DiaryEntity> loadList(){
        return list;
    }

    public DiaryEntity getDiaryAt(int position){
        return list.get(position);
    }
}
