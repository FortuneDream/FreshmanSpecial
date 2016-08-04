package com.excitingboat.freshmanspecial.presenter;

import com.excitingboat.freshmanspecial.config.Config;
import com.excitingboat.freshmanspecial.model.bean.User;
import com.excitingboat.freshmanspecial.model.net.LoginModule;
import com.excitingboat.freshmanspecial.view.iview.ILoginView;

import rx.functions.Action1;

/**
 * Created by PinkD on 2016/8/3
 * LoginPresenter
 */
public class LoginPresenter {;

    private LoginModule loginModule;
    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        loginModule = new LoginModule();
    }

    public void Login(User user) {
        loginModule.login(user, new LoginSuccess(), new LoginFail());
    }

    class LoginSuccess implements Action1<User> {
        @Override
        public void call(User user) {
            //TODO 账号系统
            iLoginView.LoginSuccess();
        }
    }

    class LoginFail implements Action1<Throwable> {
        @Override
        public void call(Throwable throwable) {
            String msg = throwable.getMessage();
            //TODO 错误处理
            iLoginView.LoginFail(Config.ERROR_INCORRECT);
        }
    }

}
