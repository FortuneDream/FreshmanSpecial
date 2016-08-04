package com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.excitingboat.freshmanspecial.R;

/**
 * Created by xushuzhan on 2016/8/4.
 */
public class InfoPageFragment extends Fragment {
    View view;
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
}
