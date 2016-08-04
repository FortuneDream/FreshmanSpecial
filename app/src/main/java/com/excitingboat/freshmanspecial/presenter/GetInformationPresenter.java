package com.excitingboat.freshmanspecial.presenter;

import com.excitingboat.freshmanspecial.config.Config;
import com.excitingboat.freshmanspecial.model.bean.Person;
import com.excitingboat.freshmanspecial.model.bean.TitleContent;
import com.excitingboat.freshmanspecial.model.bean.TitleContentPicture;
import com.excitingboat.freshmanspecial.model.net.GetInformationModule;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by PinkD on 2016/8/4.
 * 通用Presenter
 */
public class GetInformationPresenter {
    private IGetInformation iGetInformation;
    private GetInformationModule getInformationModule;
    private Action1 requestSuccess;
    private RequestFail requestFail;


    public GetInformationPresenter(IGetInformation iGetInformation, int type) {
        this.iGetInformation = iGetInformation;
        getInformationModule = new GetInformationModule();
        switch (type) {
            case Config.INFORMATION_TYPE_TITLE_CONTENT:
                requestSuccess = new RequestSuccess1();
                break;
            case Config.INFORMATION_TYPE_TITLE_CONTENT_PICTURE:
                requestSuccess = new RequestSuccess2();
                break;
            case Config.INFORMATION_TYPE_PICTURE:
                requestSuccess = new RequestSuccess3();
                break;
            case Config.INFORMATION_TYPE_PERSONAL:
                requestSuccess = new RequestSuccess4();
                break;

        }
        requestFail = new RequestFail();
    }

    public void GetInformation(String type) {
        getInformationModule.getInformation(type, 1, requestSuccess, requestFail);
    }

    private class RequestSuccess1 implements Action1<List<TitleContent>> {
        @Override
        public void call(List<TitleContent> titleContents) {
            //TODO 处理数据
            iGetInformation.requestSuccess1(titleContents);
        }
    }

    private class RequestSuccess2 implements Action1<List<TitleContentPicture>> {

        @Override
        public void call(List<TitleContentPicture> titleContentPictures) {
            //TODO 处理数据
            iGetInformation.requestSuccess2(titleContentPictures);
        }
    }

    private class RequestSuccess3 implements Action1<List<Person>> {
        @Override
        public void call(List<Person> persons) {
            //TODO 处理数据
            iGetInformation.requestSuccess3(persons);
        }
    }

    private class RequestSuccess4 implements Action1<List<String>> {
        @Override
        public void call(List<String> pictures) {
            //TODO 处理数据
            iGetInformation.requestSuccess4(pictures);
        }
    }

    private class RequestFail implements Action1<Throwable> {
        @Override
        public void call(Throwable throwable) {
            iGetInformation.requestFail();
        }
    }
}
