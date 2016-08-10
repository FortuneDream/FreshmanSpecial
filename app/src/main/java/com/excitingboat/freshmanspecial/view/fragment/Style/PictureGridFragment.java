package com.excitingboat.freshmanspecial.view.fragment.Style;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.TheExcellent;
import com.excitingboat.freshmanspecial.presenter.GetInformationPresenter;
import com.excitingboat.freshmanspecial.view.adapter.GridRecyclerAdapter;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import java.util.List;

/**
 * Created by PinkD on 2016/8/9.
 * PictureGridFragment
 */
public class PictureGridFragment extends Fragment implements IGetInformation<TheExcellent>,GridRecyclerAdapter.OnItemClickListener{
    private GetInformationPresenter<TheExcellent> presenter;
    private RecyclerView recyclerView;
    private GridRecyclerAdapter gridRecyclerAdapter;

    public void setPresenter(GetInformationPresenter<TheExcellent> presenter) {
        this.presenter = presenter;
        gridRecyclerAdapter = new GridRecyclerAdapter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.recyclerview, container, false);
        gridRecyclerAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(gridRecyclerAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return recyclerView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //TODO
//        presenter.getInformation(null);
    }

    @Override
    public void requestSuccess(List<TheExcellent> list) {
        gridRecyclerAdapter.addAll(list);
    }

    @Override
    public void requestFail() {
        Toast.makeText(getContext(), R.string.load_fail, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(View view, int position) {
        //TODO show a Dialog
    }
}
