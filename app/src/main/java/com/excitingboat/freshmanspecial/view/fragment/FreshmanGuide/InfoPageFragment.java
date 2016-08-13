package com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.PlaceWithIntroduction;
import com.excitingboat.freshmanspecial.presenter.GetInformationPresenter;
import com.excitingboat.freshmanspecial.utils.RoundImageView;
import com.excitingboat.freshmanspecial.view.adapter.FreshGuideRecyclerViewAdapter;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import java.util.List;

/**
 * Created by xushuzhan on 2016/8/4.
 */
public class InfoPageFragment extends Fragment implements FreshGuideRecyclerViewAdapter.OnItemClickListener, IGetInformation<PlaceWithIntroduction> {
    private RecyclerView recyclerview;
    private FreshGuideRecyclerViewAdapter recyclerviewAdapter;
    private GetInformationPresenter<PlaceWithIntroduction> presenter;
    private int currentPage;
    private Dialog dialog;
    RoundImageView dialogPicture;
    public void setRecyclerviewAdapter(FreshGuideRecyclerViewAdapter recyclerviewAdapter) {
        this.recyclerviewAdapter = recyclerviewAdapter;
        currentPage = 0;
    }

    public void setPresenter(GetInformationPresenter<PlaceWithIntroduction> presenter) {
        this.presenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (recyclerview == null) {
            recyclerview = (RecyclerView) LayoutInflater.from(getContext()).inflate(R.layout.recyclerview, container, false);
        }
        return recyclerview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(recyclerviewAdapter);
        recyclerviewAdapter.setOnItemClickListener(this);
        presenter.getInformation(new int[]{currentPage, 15});
    }

/*
    public static InfoPageFragment newInstance(int pageId) {
        //利用bundle传值
        Bundle bundle = new Bundle();
        bundle.putInt(PAGER_ID, pageId);
        //实例化
        InfoPageFragment fragment = new InfoPageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
*/


    @Override
    public void onItemClick(View view,String url) {
        if (dialog == null) {
            dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_fg);
            dialogPicture = (RoundImageView) dialog.findViewById(R.id.dialog_picture);

    }
        Glide.with(getContext())
                .load(url)
                .into(dialogPicture);

        dialog.show();
    }


    @Override
    public void requestSuccess(List<PlaceWithIntroduction> list) {
        if (list.size() > 0) {
            recyclerviewAdapter.addAll(list);
            ++currentPage;
            presenter.getInformation(new int[]{currentPage, 15});
        }
    }

    @Override
    public void requestFail() {

    }
}
