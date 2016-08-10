package com.excitingboat.freshmanspecial.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.TheExcellent;
import com.excitingboat.freshmanspecial.utils.RoundRectImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by PinkD on 2016/8/10.
 * GridRecyclerAdapter
 */
public class GridRecyclerAdapter extends RecyclerView.Adapter<GridRecyclerAdapter.ViewHolder> {
    private List<TheExcellent> data;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public GridRecyclerAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    public void add(TheExcellent theExcellent) {
        data.add(theExcellent);
        notifyDataSetChanged();
    }

    public void addAll(Collection<TheExcellent> theExcellents) {
        data.addAll(theExcellents);
        notifyDataSetChanged();
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

        public void setData(Context context, TheExcellent theExcellent) {
            Glide.with(context)
                    .load(theExcellent.getImage())
                    .into(roundRectImageView);
            name.setText(theExcellent.getName());
            school.setText(theExcellent.getSchool());
        }
    }
}
