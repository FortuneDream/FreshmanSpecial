package com.excitingboat.freshmanspecial.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.PlaceWithIntroduction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xushuzhan on 2016/8/8.
 * FreshGuideRecyclerViewAdapter
 */
public class FreshGuideRecyclerViewAdapter1 extends FreshGuideRecyclerViewAdapter{
    private List<PlaceWithIntroduction> data;

    public FreshGuideRecyclerViewAdapter1(Fragment fragment) {
        super(fragment);
        this.data = super.data;
    }

    @Override
    public void onBindViewHolder(FreshGuideRecyclerViewAdapter.MyViewHolder holder, int position) {
        if (data.size() > 0) {
            Glide.with(context)
                    .load(data.get(position).getPhoto().get(0).getPhoto_src())
                    .into(holder.getImageView());

            holder.itemView.setTag(data.get(position).getPhoto().get(0).getPhoto_src());
            if (data.get(position).getAddress() != null) {
                holder.itemView.findViewById(R.id.tv_fg_info_content_position).setVisibility(View.VISIBLE);
                holder.getAddress().setVisibility(View.VISIBLE);
                holder.getAddress().setText(data.get(position).getAddress());
            } else {
                holder.itemView.findViewById(R.id.tv_fg_info_content_position).setVisibility(View.INVISIBLE);
                holder.getAddress().setVisibility(View.INVISIBLE);
            }
            if (data.get(position).getIntroduction() != null) {
                holder.getIntroduction().setVisibility(View.VISIBLE);
                holder.itemView.findViewById(R.id.tv_fg_info_content_introduction).setVisibility(View.VISIBLE);
                holder.getIntroduction().setText(data.get(position).getIntroduction());
            } else if(data.get(position).getIntroduction()==null) {
                holder.getIntroductionTitle().setText("位置:");
                holder.getIntroductionTitle().setText(data.get(position).getAddress());
                holder.getAddress().setVisibility(View.INVISIBLE);
                holder.itemView.findViewById(R.id.tv_fg_info_content_position).setVisibility(View.INVISIBLE);
            }

            holder.getTitle().setText(data.get(position).getName());

        }

    }

}
