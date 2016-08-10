package com.excitingboat.freshmanspecial.view.fragment.Style;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.Video;
import com.excitingboat.freshmanspecial.presenter.GetInformationPresenter;
import com.excitingboat.freshmanspecial.view.adapter.GridRecyclerAdapter;
import com.excitingboat.freshmanspecial.view.adapter.LinearRecyclerAdapter;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import java.util.List;

/**
 * Created by PinkD on 2016/8/9.
 * PictureListFragment
 */
public class PictureListFragment extends Fragment implements IGetInformation<Video>,GridRecyclerAdapter.OnItemClickListener{

    private GetInformationPresenter<Video> presenter;
    private RecyclerView recyclerView;
    private LinearRecyclerAdapter linearRecyclerAdapter;

    public void setPresenter(GetInformationPresenter<Video> presenter) {
        this.presenter = presenter;
        linearRecyclerAdapter = new LinearRecyclerAdapter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.recyclerview, container, false);
        recyclerView.setAdapter(linearRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return recyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //TODO
        presenter.getInformation(null);
    }

    @Override
    public void requestSuccess(List<Video> list) {
        linearRecyclerAdapter.addAll(list);
    }

    @Override
    public void requestFail() {
        Toast.makeText(getContext(), R.string.load_fail, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view,int position) {

    }
}
