package com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.excitingboat.freshmanspecial.presenter.GetInformationPresenter;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import java.util.List;

/**
 * Created by PinkD on 2016/8/11.
 * DemoFragment for xushuzhan
 */
public class DemoFragment<T> extends Fragment implements IGetInformation<T> {
    private int currentPage;
    private GetInformationPresenter<T> presenter;
//    private adapter;

    public void setPresenter(GetInformationPresenter<T> presenter) {
        this.presenter = presenter;
        currentPage = 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
        //inflate RecyclerView
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getInformation(new int[]{currentPage, 15});
        //init RecyclerView
    }

    @Override
    public void requestSuccess(List<T> list) {
        if (list.size() > 0) {
            //adapter.addAll/notify
            presenter.getInformation(new int[]{++currentPage, 15});
        }
    }

    @Override
    public void requestFail() {
//fail
    }
}
