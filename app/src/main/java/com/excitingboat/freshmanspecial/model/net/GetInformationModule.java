package com.excitingboat.freshmanspecial.model.net;

import com.excitingboat.freshmanspecial.config.Config;
import com.excitingboat.freshmanspecial.net.GetInformation;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by PinkD on 2016/8/4.
 * GetInformation From Server
 */
public class GetInformationModule {
    public void getInformation(String target, int type, Action1 success, Action1<Throwable> fail) {
        GetInformation getInformation = SingleRetrofit.getInstance().getRetrofit().create(GetInformation.class);
        switch (type) {
            case Config.INFORMATION_TYPE_TITLE_CONTENT:
                getInformation.getInformation1(target)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(success, fail);
                break;
            case Config.INFORMATION_TYPE_TITLE_CONTENT_PICTURE:
                getInformation.getInformation2(target)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(success, fail);
                break;
            case Config.INFORMATION_TYPE_PERSONAL:
                getInformation.getInformation3(target)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(success, fail);
                break;
            case Config.INFORMATION_TYPE_PICTURE:
                getInformation.getInformation4(target)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(success, fail);
                break;
        }
    }
}

