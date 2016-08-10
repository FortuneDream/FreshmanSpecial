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
    public void getInformation(String param, int which, Action1<List> success, Action1<Throwable> fail) {
        GetInformation getInformation =  SingleRetrofit.getInstance().getRetrofit().create(GetInformation.class);
        switch (which) {
            case GetInformation.STUDENT:
                getInformation.getStudent(param)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(success, fail);
                break;
            case GetInformation.TEACHER:
                getInformation.getTeacher(param)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(success, fail);
                break;
            case GetInformation.VIDEO:
                getInformation.getVideo(param)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(success, fail);
                break;
        }
    }
}

