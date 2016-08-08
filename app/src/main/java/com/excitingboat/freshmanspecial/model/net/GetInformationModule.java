package com.excitingboat.freshmanspecial.model.net;

import com.excitingboat.freshmanspecial.net.GetInformation;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by PinkD on 2016/8/4.
 * getInformation From Server
 */
public class GetInformationModule<T> {
    public void getInformation(String target, Action1<List<T>> success, Action1<Throwable> fail) {
        GetInformation<T> getInformation = (GetInformation<T>) SingleRetrofit.getInstance().getRetrofit().create(GetInformation.class);
        getInformation.getInformation(target)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, fail);
    }
}

