package com.excitingboat.freshmanspecial.net;

import com.excitingboat.freshmanspecial.model.bean.TitleContent;
import com.excitingboat.freshmanspecial.model.bean.TitleContentPicture;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by PinkD on 2016/8/4.
 */
public interface GetInformation {

    @GET("params")
    Observable<List<TitleContent>> getInformation(@Query("type") String type);

    @GET("params")
    Observable<List<TitleContentPicture>> getInformation1(@Query("type") String type);

}
