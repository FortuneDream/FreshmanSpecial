package com.excitingboat.freshmanspecial.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup.LayoutParams;
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
public class FreshGuideRecyclerViewAdapter extends RecyclerView.Adapter<FreshGuideRecyclerViewAdapter.MyViewHolder> implements View.OnClickListener {
    private List<PlaceWithIntroduction> data;
    private Fragment context;
    private OnItemClickListener onItemClickListener;

    public FreshGuideRecyclerViewAdapter(Fragment fragment) {
        context = fragment;
        this.data = new ArrayList<>();
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(view, (String) view.getTag());
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view,String url);
    }

    public void setData(List<PlaceWithIntroduction> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void addAll(List<PlaceWithIntroduction> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void add(PlaceWithIntroduction data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fg_info_page, parent, false);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (data.size() > 0) {
            Glide.with(context)
                    .load(data.get(position).getPhoto().get(0).getPhoto_src())
                    .into(holder.imageView);

            holder.itemView.setTag(data.get(position).getPhoto().get(0).getPhoto_src());
            if (data.get(position).getAddress() != null) {
                holder.itemView.findViewById(R.id.tv_fg_info_content_position).setVisibility(View.VISIBLE);
                holder.address.setVisibility(View.VISIBLE);
                holder.address.setText(data.get(position).getAddress());
            } else {
                holder.itemView.findViewById(R.id.tv_fg_info_content_position).setVisibility(View.INVISIBLE);
                holder.address.setVisibility(View.INVISIBLE);
            }
            if (data.get(position).getIntroduction() != null) {
                holder.introduction.setVisibility(View.VISIBLE);
                holder.itemView.findViewById(R.id.tv_fg_info_content_introduction).setVisibility(View.VISIBLE);
                holder.introduction.setText(data.get(position).getIntroduction());
            } else if(data.get(position).getIntroduction()==null) {
                holder.IntroductionTitle.setText("位置:");
                holder.introduction.setText(data.get(position).getAddress());
                holder.address.setVisibility(View.INVISIBLE);
                holder.itemView.findViewById(R.id.tv_fg_info_content_position).setVisibility(View.INVISIBLE);
            }

            holder.title.setText(data.get(position).getName());

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

   class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView introduction;
        private TextView address;
        private TextView IntroductionTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_fg_info_small_pic);
            title = (TextView) itemView.findViewById(R.id.tv_fg_info_title);
            introduction = (TextView) itemView.findViewById(R.id.tv_fg_info_content_s);
            address = (TextView) itemView.findViewById(R.id.tv_fg_info_content_address);
            IntroductionTitle= (TextView) itemView.findViewById(R.id.tv_fg_info_content_introduction);

        }
    }
}
