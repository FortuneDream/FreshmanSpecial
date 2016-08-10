package com.excitingboat.freshmanspecial.net;

import com.excitingboat.freshmanspecial.model.bean.TheExcellent;
import com.excitingboat.freshmanspecial.model.bean.Video;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by PinkD on 2016/8/4.
 * Retrofit Network Interface
 */
public interface GetInformation {

    int STUDENT = 0;
    int TEACHER = 1;
    int VIDEO = 2;
    @GET("api")
    Observable<List<TheExcellent>> getStudent(@Query("param") String param);

    @GET("api")
    Observable<List<TheExcellent>> getTeacher(@Query("param") String param);

    @GET("api")
    Observable<List<Video>> getVideo(@Query("param") String param);

}
