package com.excitingboat.freshmanspecial.model.bean;

import java.util.List;

/**
 * Created by PinkD on 2016/8/11.
 * Dormitory
 */
public class Dormitory {
    private String id;
    private String introduction;
    private List<Photo> data;

    public List<Photo> getData() {
        return data;
    }

    public void setData(List<Photo> data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "data=" + data +
                ", id='" + id + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
