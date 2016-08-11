package com.excitingboat.freshmanspecial.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.Video;
import com.excitingboat.freshmanspecial.utils.RoundRectImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by PinkD on 2016/8/10.
 * StudentRecyclerAdapter
 */
public class LinearRecyclerAdapter extends RecyclerView.Adapter<LinearRecyclerAdapter.ViewHolder> {
    private List<Video> data;
    private Context context;

    public LinearRecyclerAdapter(Context context, List<Video> data) {
        this.context = context;
        this.data.addAll(data);
    }

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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fg_info_page, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(data.get(position).getPhoto().get(0).getPhoto_src())
                .into(holder.roundRectImageView);
        holder.name.setText(data.get(position).getName());
        holder.introduction.setText(data.get(position).getIntroduction());
        holder.time.setText(data.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RoundRectImageView roundRectImageView;
        private TextView name;
        private TextView introduction;
        private TextView time;

        public ViewHolder(final View itemView) {
            super(itemView);
            roundRectImageView = (RoundRectImageView) itemView.findViewById(R.id.iv_fg_info_small_pic);
            name = (TextView) itemView.findViewById(R.id.tv_fg_info_title);
            introduction = (TextView) itemView.findViewById(R.id.tv_fg_info_content_big);
            time = (TextView) itemView.findViewById(R.id.tv_fg_info_content_s);
            ((TextView) itemView.findViewById(R.id.tv_fg_info_content_title_s)).setText("简介");
            ((TextView) itemView.findViewById(R.id.tv_fg_info_content_title_big)).setText("时长");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse(data.get(getLayoutPosition()).getVideo_url());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                }
            });
        }

    }

}
