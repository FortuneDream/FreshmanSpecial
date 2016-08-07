package com.excitingboat.freshmanspecial.net;

import com.excitingboat.freshmanspecial.model.bean.Person;
import com.excitingboat.freshmanspecial.model.bean.TitleContent;
import com.excitingboat.freshmanspecial.model.bean.TitleContentPicture;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by PinkD on 2016/8/4.
 * Retrofit Network Interface
 */
public interface GetInformation<T> {

    @GET("params")
    Observable<List<T>> getInformation(@Query("type") String type);

}
