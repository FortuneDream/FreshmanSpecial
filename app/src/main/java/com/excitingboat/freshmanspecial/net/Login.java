package com.excitingboat.freshmanspecial.net;

import com.excitingboat.freshmanspecial.model.bean.User;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by PinkD on 2016/8/3.
 */
public interface Login {
    @FormUrlEncoded
    @POST("test.php")
    Observable<User> login(@Field("username") String username, @Field("password") String password);
}
