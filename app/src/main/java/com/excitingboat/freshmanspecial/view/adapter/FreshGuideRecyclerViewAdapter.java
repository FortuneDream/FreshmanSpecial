package com.excitingboat.freshmanspecial.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.excitingboat.freshmanspecial.R;

/**
 * Created by xushuzhan on 2016/8/8.
 */
public class FreshGuideRecyclerViewAdapter extends RecyclerView.Adapter<FreshGuideRecyclerViewAdapter.MyViewHolder> implements View.OnClickListener{
    @Override
    public void onClick(View view) {
        if(onRcyclerviewClickListener!=null){
            onRcyclerviewClickListener.onItemClick(view);
        }
    }

    public interface OnRcyclerviewClickListener{
        void onItemClick(View view);
    }
    OnRcyclerviewClickListener onRcyclerviewClickListener;

    public void setOnRcyclerviewClickListener(OnRcyclerviewClickListener onRcyclerviewClickListener){
        this.onRcyclerviewClickListener = onRcyclerviewClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fg_info_page,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        holder.imageView.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 12;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_fg_info_small_pic);
        }
    }
}
