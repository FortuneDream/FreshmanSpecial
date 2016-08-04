package com.excitingboat.freshmanspecial.view.iview;

import com.excitingboat.freshmanspecial.model.bean.Person;
import com.excitingboat.freshmanspecial.model.bean.TitleContent;
import com.excitingboat.freshmanspecial.model.bean.TitleContentPicture;

import java.util.List;

/**
 * Created by PinkD on 2016/8/4.
 * Interface GetInformation
 */
public abstract class IGetInformation {
    /**
     * {@link com.excitingboat.freshmanspecial.model.bean.TitleContent}
     */
    public void requestSuccess1(List<TitleContent> titleContents) {
    }

    /**
     * {@link com.excitingboat.freshmanspecial.model.bean.TitleContentPicture}
     */
    public void requestSuccess2(List<TitleContentPicture> titleContentPictures) {
    }

    /**
     * {@link com.excitingboat.freshmanspecial.model.bean.User}
     */
    public void requestSuccess3(List<Person> persons) {
    }

    public void requestSuccess4(List<String> pictures) {
    }

    public abstract void requestFail();
}
