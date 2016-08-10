package com.excitingboat.freshmanspecial.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.TheExcellent;
import com.excitingboat.freshmanspecial.model.bean.Video;
import com.excitingboat.freshmanspecial.utils.RoundRectImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by PinkD on 2016/8/10.
 * GridRecyclerAdapter
 */
public class LinearRecyclerAdapter extends RecyclerView.Adapter<LinearRecyclerAdapter.ViewHolder> {
    private List<Video> data;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public LinearRecyclerAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    public void add(Video video) {
        data.add(video);
        notifyDataSetChanged();
    }

    public void addAll(Collection<Video> videos) {
        data.addAll(videos);
        notifyDataSetChanged();
    }

    public List<Video> getData() {
        return data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(context, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RoundRectImageView roundRectImageView;
        private TextView name;
        private TextView school;

        public ViewHolder(final View itemView) {
            super(itemView);
            roundRectImageView = (RoundRectImageView) itemView.findViewById(R.id.item_picture);
            name = (TextView) itemView.findViewById(R.id.item_name);
            school = (TextView) itemView.findViewById(R.id.item_school);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }

        public void setData(Context context, Video video) {
            Glide.with(context)
                    .load(video.getData().get(0))
                    .into(roundRectImageView);
            name.setText(video.getName());
            school.setText(video.getTime());
        }
    }
}
