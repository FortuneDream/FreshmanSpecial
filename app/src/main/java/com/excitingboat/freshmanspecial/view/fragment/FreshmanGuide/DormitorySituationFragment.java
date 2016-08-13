package com.excitingboat.freshmanspecial.view.fragment.FreshmanGuide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.Dormitory;
import com.excitingboat.freshmanspecial.presenter.GetInformationPresenter;
import com.excitingboat.freshmanspecial.view.activity.FreshmenGuideActivity;
import com.excitingboat.freshmanspecial.view.iview.IGetInformation;

import java.util.List;

/**
 * Created by xushuzhan on 2016/8/13.
 */
public class DormitorySituationFragment extends Fragment implements IGetInformation<Dormitory> {
    View view;
    ImageView one;
    ImageView two;
    ImageView three;

    private GetInformationPresenter<Dormitory> presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_fg_dormitory_situation,container,false);
        initview();

            return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {

            presenter.getInformation(new int[]{0,4});
        }catch (Exception e){
            Log.d("123456", "onCreateView: "+e.getMessage());
        }

    }

    public void setPresenter(GetInformationPresenter<Dormitory> presenter) {
        this.presenter = presenter;
    }
    private void initview() {
        one = (ImageView) view.findViewById(R.id.picture_one);
        two = (ImageView) view.findViewById(R.id.picture_two);
        three = (ImageView) view.findViewById(R.id.picture_three);

    }


    @Override
    public void requestSuccess(List<Dormitory> list) {
        Glide.with(getContext())
                .load(list.get(0).getData().get(0).getPhoto_src())
                .into(one);
        Log.d("123456", "list");
        Log.d("123456", "requestSuccess: "+list.get(0).getData().get(0).getPhoto_src());
//        Glide.with(getContext())
//                .load(list.get(1).getData().get(0).getPhoto())
//                .into(two);
//        Glide.with(getContext())
//                .load(list.get(2).getData().get(0).getPhoto())
//                .into(two);
    }

    @Override
    public void requestFail() {
        Log.d("123456", "requestFail: ");
    }
}
