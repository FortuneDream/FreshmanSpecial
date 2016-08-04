package com.excitingboat.freshmanspecial.model.net;

import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.excitingboat.freshmanspecial.config.Config;
import com.excitingboat.freshmanspecial.model.bean.User;
import com.excitingboat.freshmanspecial.net.Login;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by PinkD on 2016/8/3.
 * Login module
 */
public class LoginModule {
    public void login(User user, Action1<User> success, Action1<Throwable> fail) {
        Login login = SingleRetrofit.getInstance().getRetrofit().create(Login.class);
        login.login(user.getUsername(), user.getPassword())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(success,fail);
    }
}
