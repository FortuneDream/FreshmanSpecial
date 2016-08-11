package com.excitingboat.freshmanspecial.net;

import com.excitingboat.freshmanspecial.config.Api;
import com.excitingboat.freshmanspecial.model.bean.Dormitory;
import com.excitingboat.freshmanspecial.model.bean.Place;
import com.excitingboat.freshmanspecial.model.bean.PlaceWithIntroduction;
import com.excitingboat.freshmanspecial.model.bean.Student;
import com.excitingboat.freshmanspecial.model.bean.Teacher;
import com.excitingboat.freshmanspecial.model.bean.Video;
import com.excitingboat.freshmanspecial.model.bean.Wrapper;

import retrofit2.http.Field;
import retrofit2.http.POST;
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
    int DORMITORY = 3;
    int SIGHT = 4;
    int DAILY_LIFE = 5;
    int FOOD = 6;

    @POST(Api.STUDENT)
    Observable<Wrapper<Student>> getStudent(@Query("page") String page, @Query("size") String size);

    @POST(Api.TEACHER)
    Observable<Wrapper<Teacher>> getTeacher(@Query("page") String page, @Query("size") String size);

    @POST(Api.ORIGINAL)
    Observable<Wrapper<Video>> getVideo(@Query("page") String page, @Query("size") String size);

    @POST(Api.DORMITORY)
    Observable<Wrapper<Dormitory>> getDormitory(@Query("page") String page, @Query("size") String size);

    @POST(Api.SIGHT)
    Observable<Wrapper<PlaceWithIntroduction>> getSight(@Query("page") String page, @Query("size") String size);

    @POST(Api.DAILY_LIFE)
    Observable<Wrapper<Place>> getDaily(@Query("page") String page, @Query("size") String size);

    @POST(Api.FOOD)
    Observable<Wrapper<PlaceWithIntroduction>> getFood(@Query("page") String page, @Query("size") String size);

}
