package com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.Dormitory;
import com.excitingboat.freshmanspecial.presenter.GetInformationPresenter;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import java.util.List;

/**
 * Created by xushuzhan on 2016/8/13.
 */
public class DormitorySituationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_fg_dormitory_situation, container, false);
    }

}
