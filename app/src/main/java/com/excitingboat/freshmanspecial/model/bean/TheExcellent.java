package com.excitingboat.freshmanspecial.model.bean;

import java.util.List;

/**
 * Created by PinkD on 2016/8/4.
 * TheExcellent
 */
public class TheExcellent {
//TODO
    private String id;
    private String name;
    private String description;
    private String introduction;
    private List<Photo> data;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TheExcellent{" +
                "data=" + data +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
