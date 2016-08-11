package com.excitingboat.freshmanspecial.view.fragment.Style;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.presenter.GetInformationPresenter;
import com.excitingboat.freshmanspecial.view.adapter.GridRecyclerAdapter;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import java.util.List;

/**
 * Created by PinkD on 2016/8/9.
 * PictureGridFragment
 */
public class PictureGridFragment<T> extends Fragment implements IGetInformation<T>, GridRecyclerAdapter.OnItemClickListener {
    private static final String TAG = "PictureGridFragment";
    private GetInformationPresenter<T> presenter;
    private RecyclerView recyclerView;
    private GridRecyclerAdapter gridRecyclerAdapter;
    private int currentPage;


    public void setPresenter(Context context, GetInformationPresenter<T> presenter) {
        this.presenter = presenter;
        gridRecyclerAdapter = new GridRecyclerAdapter(context);
        currentPage = 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        recyclerView = (RecyclerView) inflater.inflate(R.layout.recyclerview, container, false);
        return recyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        gridRecyclerAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(gridRecyclerAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        presenter.getInformation(new String[]{String.valueOf(currentPage), String.valueOf(15)});
    }

    @Override
    public void requestSuccess(List<T> list) {
        gridRecyclerAdapter.addAll(list);
        currentPage += 1;
    }

    @Override
    public void requestFail() {
        Toast.makeText(getContext(), R.string.load_fail, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, int position) {
        //TODO show a Dialog
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unBind();
    }
}
