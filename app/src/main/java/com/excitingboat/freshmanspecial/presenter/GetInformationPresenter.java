package com.excitingboat.freshmanspecial.presenter;

import android.util.Log;

import com.excitingboat.freshmanspecial.model.bean.Wrapper;
import com.excitingboat.freshmanspecial.model.net.GetInformationModule;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import rx.Subscriber;

/**
 * Created by PinkD on 2016/8/4.
 * 通用Presenter
 */
public class GetInformationPresenter<T> implements BasePresenter {
    private static final String TAG = "GetInformationPresenter";
    private int total;
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
        Log.d(TAG, "request");
    }

    public int getTotal() {
        return total;
    }

    @Override
    public void unBind() {
        iGetInformation = null;
    }

    class MySubscriber extends Subscriber<Wrapper<T>> {

        @Override
        public void onCompleted() {

            Log.d(TAG, "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            iGetInformation.requestFail();
            Log.d(TAG, "onError");
        }

        @Override
        public void onNext(Wrapper<T> wrapper) {
            Log.d(TAG, "onNext");
            iGetInformation.requestSuccess(wrapper.getData());
            total = wrapper.getTotal();
        }
    }
}
