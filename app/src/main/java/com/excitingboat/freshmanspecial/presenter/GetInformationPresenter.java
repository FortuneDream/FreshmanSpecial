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
    private int which;
    private IGetInformation<T> iGetInformation;
    private GetInformationModule<T> getInformationModule;
    private Action1<List> requestSuccess;
    private RequestFail requestFail;


    public GetInformationPresenter(IGetInformation<T> iGetInformation, int which) {
        this.iGetInformation = iGetInformation;
        getInformationModule = new GetInformationModule<>();
        requestSuccess = new RequestSuccess();
        requestFail = new RequestFail();
        this.which = which;
    }

    public int getWhich() {
        return which;
    }

    public void getInformation(String param) {
        getInformationModule.getInformation(param, which, requestSuccess, requestFail);
    }

    @Override
    public void unBind() {
        iGetInformation = null;
    }

    private class RequestSuccess implements Action1<List> {
        @Override
        public void call(List list) {
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
