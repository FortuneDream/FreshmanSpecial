package com.excitingboat.freshmanspecial.presenter;

import com.excitingboat.freshmanspecial.model.bean.Wrapper;
import com.excitingboat.freshmanspecial.model.net.GetInformationModule;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import rx.Subscriber;

/**
 * Created by PinkD on 2016/8/4.
 * 通用Presenter
 */
public class GetInformationPresenter<T> implements BasePresenter {
    private int which;
    private IGetInformation<T> iGetInformation;
    private GetInformationModule getInformationModule;


    public GetInformationPresenter(IGetInformation<T> iGetInformation, int which) {
        this.iGetInformation = iGetInformation;
        getInformationModule = new GetInformationModule();
        this.which = which;
    }

    public int getWhich() {
        return which;
    }

    public void getInformation(String[] param) {
        getInformationModule.getInformation(param, which, (Subscriber) new MySubscriber());
    }

    @Override
    public void unBind() {
        iGetInformation = null;
    }

    class MySubscriber extends Subscriber<Wrapper<T>> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            iGetInformation.requestFail();
        }

        @Override
        public void onNext(Wrapper<T> wrapper) {
            iGetInformation.requestSuccess(wrapper.getData());
        }
    }
}
