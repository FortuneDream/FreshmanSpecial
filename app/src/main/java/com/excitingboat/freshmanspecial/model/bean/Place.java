package com.excitingboat.freshmanspecial.model.bean;

import java.util.List;

/**
 * Created by PinkD on 2016/8/11.
 * Place
 */
public class Place {
    private String id;
    private String name;
    private String address;
    private List<Photo> data;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Photo> getData() {
        return data;
    }

    public void setData(List<Photo> data) {
        this.data = data;
    }
}
