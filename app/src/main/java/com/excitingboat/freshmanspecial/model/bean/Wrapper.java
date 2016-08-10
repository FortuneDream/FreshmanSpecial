package com.excitingboat.freshmanspecial.model.bean;

import java.util.List;

/**
 * Created by PinkD on 2016/8/11.
 * Wrapper
 */
public class Wrapper<T> {
    private int status;
    private String info;
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
