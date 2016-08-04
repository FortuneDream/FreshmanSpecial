package com.excitingboat.freshmanspecial.presenter;

import com.excitingboat.freshmanspecial.model.bean.TitleContent;
import com.excitingboat.freshmanspecial.model.bean.TitleContentPicture;
import com.excitingboat.freshmanspecial.model.net.GetInformationModule;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by PinkD on 2016/8/4.
 */
public class GetInformationPresenter {
    private IGetInformation iGetInformation;
    private GetInformationModule getInformationModule;
    private RequestSuccess requestSuccess;
    private RequestSuccess1 requestSuccess1;
    private RequestFail requestFail;


    public GetInformationPresenter(IGetInformation iGetInformation) {
        this.iGetInformation = iGetInformation;
        getInformationModule = new GetInformationModule();
        requestSuccess = new RequestSuccess();
        requestSuccess1 = new RequestSuccess1();
        requestFail = new RequestFail();
    }

    public void GetInformation(String type) {
        getInformationModule.getInformation(type, 1, requestSuccess, requestFail);
    }

    public void GetInformation1(String type) {
        getInformationModule.getInformation(type, 2, requestSuccess1, requestFail);
    }

    class RequestSuccess implements Action1<List<TitleContent>> {
        @Override
        public void call(List<TitleContent> titleContents) {
            //TODO 处理数据
            iGetInformation.requestSuccess1(titleContents);
        }
    }

    class RequestSuccess1 implements Action1<List<TitleContentPicture>> {

        @Override
        public void call(List<TitleContentPicture> titleContentPictures) {
            //TODO 处理数据
            iGetInformation.requestSuccess2(titleContentPictures);
        }
    }

    class RequestFail implements Action1<Throwable> {
        @Override
        public void call(Throwable throwable) {
            //TODO

        }
    }

}
