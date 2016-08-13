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
public class DormitorySituationFragment extends Fragment implements IGetInformation<Dormitory> {
    private ImageView[] imageViews;
    private TextView[] textViews;
    private GetInformationPresenter<Dormitory> presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_fg_dormitory_situation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getInformation(new int[]{0, 4});
        imageViews = new ImageView[]{
                (ImageView) view.findViewById(R.id.picture_1),
                (ImageView) view.findViewById(R.id.picture_2),
                (ImageView) view.findViewById(R.id.picture_3)
        };
        textViews = new TextView[]{
                (TextView) view.findViewById(R.id.dormitory_1),
                (TextView) view.findViewById(R.id.dormitory_2),
                (TextView) view.findViewById(R.id.dormitory_3),
                (TextView) view.findViewById(R.id.dormitory_4)
        };

    }

    public void setPresenter(GetInformationPresenter<Dormitory> presenter) {
        this.presenter = presenter;
    }

    @Override
    public void requestSuccess(List<Dormitory> list) {
        for (int i = 0; i < imageViews.length; i++) {
            Glide.with(getContext())
                    .load(list.get(i).getPhoto().get(0).getPhoto_src())
                    .into(imageViews[i]);
        }
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setText(list.get(i).getIntroduction());
        }
    }

    @Override
    public void requestFail() {

    }
}
