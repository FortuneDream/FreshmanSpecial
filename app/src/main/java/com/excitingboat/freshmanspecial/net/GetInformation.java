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
import retrofit2.http.GET;
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

    @GET(Api.STUDENT)
    Observable<Wrapper<Student>> getStudent(@Field("page") String page, @Field("size") String size);

    @GET(Api.TEACHER)
    Observable<Wrapper<Teacher>> getTeacher(@Field("page") String page, @Field("size") String size);

    @GET(Api.ORIGINAL)
    Observable<Wrapper<Video>> getVideo(@Field("page") String page, @Field("size") String size);

    @GET(Api.DORMITORY)
    Observable<Wrapper<Dormitory>> getDormitory(@Field("page") String page, @Field("size") String size);

    @GET(Api.SIGHT)
    Observable<Wrapper<PlaceWithIntroduction>> getSight(@Field("page") String page, @Field("size") String size);

    @GET(Api.DAILY_LIFE)
    Observable<Wrapper<Place>> getDaily(@Field("page") String page, @Field("size") String size);

    @GET(Api.FOOD)
    Observable<Wrapper<PlaceWithIntroduction>> getFood(@Field("page") String page, @Field("size") String size);

}
