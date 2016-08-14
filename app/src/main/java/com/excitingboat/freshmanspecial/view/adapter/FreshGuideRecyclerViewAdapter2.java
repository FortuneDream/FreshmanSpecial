package com.excitingboat.freshmanspecial.view.adapter;

import android.support.v4.app.Fragment;
import android.view.View;

import com.bumptech.glide.Glide;
import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.SurroundSight;

import java.util.List;

/**
 * Created by xushuzhan on 2016/8/8.
 * FreshGuideRecyclerViewAdapter
 */
public class FreshGuideRecyclerViewAdapter2 extends FreshGuideRecyclerViewAdapter {
    private List<SurroundSight> data;

    public FreshGuideRecyclerViewAdapter2(Fragment fragment) {
        super(fragment);
        this.data = super.data;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (data.size() > 0) {
            Glide.with(context)
                    .load(data.get(position).getPhoto().get(0).getPhoto_src())
                    .into(holder.getImageView());

            holder.itemView.setTag(data.get(position).getPhoto().get(0).getPhoto_src());
            if (data.get(position).getTourroute() == null) {
                holder.itemView.findViewById(R.id.tv_fg_info_content_position).setVisibility(View.INVISIBLE);
                holder.getAddress().setVisibility(View.INVISIBLE);
            } else {
                holder.itemView.findViewById(R.id.tv_fg_info_content_position).setVisibility(View.VISIBLE);
                holder.getAddress().setVisibility(View.VISIBLE);
                holder.getAddress().setText(data.get(position).getTourroute());
            }
            if (data.get(position).getIntroduction() == null) {
                holder.getIntroduction().setVisibility(View.INVISIBLE);
                holder.itemView.findViewById(R.id.tv_fg_info_content_introduction).setVisibility(View.INVISIBLE);
            } else {
                holder.getIntroduction().setVisibility(View.VISIBLE);
                holder.itemView.findViewById(R.id.tv_fg_info_content_introduction).setVisibility(View.VISIBLE);
                holder.getIntroduction().setText(data.get(position).getIntroduction());
            }

            holder.getTitle().setText(data.get(position).getName());

        }
    }
}
