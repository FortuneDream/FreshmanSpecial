package com.excitingboat.freshmanspecial.view.fragment.Style;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.Video;
import com.excitingboat.freshmanspecial.presenter.GetInformationPresenter;
import com.excitingboat.freshmanspecial.view.activity.WebActivity;
import com.excitingboat.freshmanspecial.view.adapter.GridRecyclerAdapter;
import com.excitingboat.freshmanspecial.view.adapter.LinearRecyclerAdapter;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import java.util.List;

/**
 * Created by PinkD on 2016/8/9.
 * PictureListFragment
 */
public class PictureListFragment extends Fragment implements IGetInformation<Video>, GridRecyclerAdapter.OnItemClickListener {

    private static final String TAG = "PictureListFragment";
    private GetInformationPresenter<Video> presenter;
    private RecyclerView recyclerView;
    private LinearRecyclerAdapter linearRecyclerAdapter;
    private int currentPage;

    public void setPresenter(Context context,GetInformationPresenter<Video> presenter) {
        this.presenter = presenter;
        linearRecyclerAdapter = new LinearRecyclerAdapter(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.recyclerview, container, false);
        return recyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setAdapter(linearRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.getInformation(new String[]{String.valueOf(currentPage), String.valueOf(15)});
    }

    @Override
    public void requestSuccess(List<Video> list) {
        linearRecyclerAdapter.addAll(list);
        currentPage += 1;
    }

    @Override
    public void requestFail() {
        Toast.makeText(getContext(), R.string.load_fail, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getContext(), WebActivity.class);
        intent.putExtra("url", linearRecyclerAdapter.getData().get(position).getVideo_url());
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unBind();
    }
}
