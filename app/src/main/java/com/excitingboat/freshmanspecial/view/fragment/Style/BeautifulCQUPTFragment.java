package com.excitingboat.freshmanspecial.view.fragment.Style;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.config.Config;

/**
 * Created by PinkD on 2016/8/9.
 * BeautifulCQUPTFragment
 */
public class BeautifulCQUPTFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beautiful_cqupt, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//TODO 加载图片请求
        ImageView[] imageViews = new ImageView[]{
                (ImageView) view.findViewById(R.id.beauty_image_1),
                (ImageView) view.findViewById(R.id.beauty_image_2),
                (ImageView) view.findViewById(R.id.beauty_image_3),
                (ImageView) view.findViewById(R.id.beauty_image_4),
                (ImageView) view.findViewById(R.id.beauty_image_5),
                (ImageView) view.findViewById(R.id.beauty_image_6),
                (ImageView) view.findViewById(R.id.beauty_image_7),
                (ImageView) view.findViewById(R.id.beauty_image_8),
                (ImageView) view.findViewById(R.id.beauty_image_9),
                (ImageView) view.findViewById(R.id.beauty_image_10)
        };
        for (int i = 0; i < 10; i++) {
            Glide.with(getActivity())
                    .load(Config.BASE_URL + i)
                    .into(imageViews[i]);
        }
    }
}
