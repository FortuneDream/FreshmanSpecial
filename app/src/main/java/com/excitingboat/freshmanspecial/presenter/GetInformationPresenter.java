package com.excitingboat.freshmanspecial.presenter;

import com.excitingboat.freshmanspecial.model.net.GetInformationModule;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by PinkD on 2016/8/4.
 * 通用Presenter
 */
public class GetInformationPresenter<T> implements BasePresenter {
    private IGetInformation iGetInformation;
    private GetInformationModule<T> getInformationModule;
    private Action1<List<T>> requestSuccess;
    private RequestFail requestFail;


    public GetInformationPresenter(IGetInformation iGetInformation) {
        this.iGetInformation = iGetInformation;
        getInformationModule = new GetInformationModule<>();
        requestSuccess = new RequestSuccess<T>();
        requestFail = new RequestFail();
    }

    public void getInformation(String type) {
        getInformationModule.getInformation(type, requestSuccess, requestFail);
    }

    @Override
    public void unBind() {
        iGetInformation = null;
    }

    private class RequestSuccess<A> implements Action1<List<A>> {
        @Override
        public void call(List<A> list) {
            iGetInformation.requestSuccess(list);
        }
    }

    private class RequestFail implements Action1<Throwable> {
        @Override
        public void call(Throwable throwable) {
            iGetInformation.requestFail();
        }
    }
}
