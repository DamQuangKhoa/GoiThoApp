package com.demo.architect.data.model.offline;

import com.demo.architect.data.model.ActivityEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Skull on 28/11/2017.
 */

public class DiaryEntity implements Serializable {
    private String id;
    private ArrayList<ActivityEntity> detail;

    public DiaryEntity() {
    }

    public DiaryEntity(String id, ArrayList<ActivityEntity> detail) {
        this.id = id;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<ActivityEntity> getDetail() {
        return detail;
    }

    public void setDetail(ArrayList<ActivityEntity> detail) {
        this.detail = detail;
    }

    public void setDetail(List<ActivityEntity> list) {
        this.detail = new ArrayList<>();
        this.detail.addAll(list);
    }
}