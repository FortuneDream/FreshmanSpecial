package com.excitingboat.freshmanspecial.model.net;

import com.excitingboat.freshmanspecial.model.bean.TitleContent;
import com.excitingboat.freshmanspecial.model.bean.User;
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
            case 1:
                getInformation.getInformation(target)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(success,fail);
                break;
            case 2:
                getInformation.getInformation1(target)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(success,fail);
                break;
        }
    }
}

