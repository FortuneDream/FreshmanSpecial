package com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide;

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
import com.excitingboat.freshmanspecial.model.bean.Dormitory;
import com.excitingboat.freshmanspecial.model.bean.Place;
import com.excitingboat.freshmanspecial.view.activity.ShowBigPictureActivity;
import com.excitingboat.freshmanspecial.view.adapter.FreshGuideRecyclerViewAdapter;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xushuzhan on 2016/8/4.
 */
public class InfoPageFragment extends Fragment {
    View view;
    RecyclerView recyclerview;
    FreshGuideRecyclerViewAdapter recyclerviewAdapter;
    private static final String PAGER_ID = "position";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle!=null){
            bundle.getInt(PAGER_ID);
        }
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fg_info_page,container,false);
        }
        initView();
        return view;
    }

    public static InfoPageFragment newInstance(int pageId) {
        //利用bundle传值
        Bundle bundle = new Bundle();
        bundle.putInt(PAGER_ID, pageId);
        //实例化
        InfoPageFragment fragment = new InfoPageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    private void initView() {
        recyclerview = (RecyclerView) view.findViewById(R.id.fg_info_page_recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerviewAdapter = new FreshGuideRecyclerViewAdapter();
        recyclerview.setAdapter(recyclerviewAdapter);
        recyclerviewAdapter.setOnRcyclerviewClickListener(new FreshGuideRecyclerViewAdapter.OnRcyclerviewClickListener() {
            @Override
            public void onItemClick(View view) {
                ShowBigPictureActivity.showBigPicture(getContext(),"http://img5.imgtn.bdimg.com/it/u=471926043,2104091042&fm=21&gp=0.jpg",view);
                //添加过渡动画，一定要添加！！
                getActivity().overridePendingTransition(0, R.anim.abc_fade_in);
                Toast.makeText(getContext(), "你点了一下图片", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
