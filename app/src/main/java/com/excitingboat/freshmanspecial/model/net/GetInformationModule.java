package com.excitingboat.freshmanspecial.model.net;

import com.excitingboat.freshmanspecial.model.bean.Wrapper;
import com.excitingboat.freshmanspecial.net.GetInformation;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PinkD on 2016/8/4.
 * getInformation From Server
 */
public class GetInformationModule {
    public void getInformation(String[] param, int which, Subscriber<Wrapper> subscriber) {
        GetInformation getInformation = SingleRetrofit.getInstance().getRetrofit().create(GetInformation.class);
        switch (which) {
            case GetInformation.STUDENT:
                getInformation.getStudent(param[0], param[1])
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
                break;
            case GetInformation.TEACHER:
                getInformation.getTeacher(param[0], param[1])
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
                break;
            case GetInformation.VIDEO:
                getInformation.getVideo(param[0], param[1])
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
                break;
            case GetInformation.DORMITORY:
                getInformation.getDormitory(param[0], param[1])
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
                break;
            case GetInformation.SIGHT:
                getInformation.getSight(param[0], param[1])
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
                break;
            case GetInformation.DAILY_LIFE:
                getInformation.getDaily(param[0], param[1])
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
                break;
            case GetInformation.FOOD:
                getInformation.getFood(param[0], param[1])
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
                break;
        }
    }
}

