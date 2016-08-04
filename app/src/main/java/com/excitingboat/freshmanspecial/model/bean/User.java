package com.excitingboat.freshmanspecial.model.bean;

/**
 * Created by PinkD on 2016/8/3.
 * Javabean User extend Person
 */
public class User extends Person{
    private String password;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
