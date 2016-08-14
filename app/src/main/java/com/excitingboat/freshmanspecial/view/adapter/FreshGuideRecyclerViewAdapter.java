package com.excitingboat.freshmanspecial.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.model.bean.PlaceWithIntroduction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xushuzhan on 2016/8/8.
 * FreshGuideRecyclerViewAdapter
 */
public abstract class FreshGuideRecyclerViewAdapter extends RecyclerView.Adapter<FreshGuideRecyclerViewAdapter.MyViewHolder> {
    protected List data;
    protected Fragment context;
    private OnItemClickListener onItemClickListener;

    public FreshGuideRecyclerViewAdapter(Fragment fragment) {
        context = fragment;
        this.data = new ArrayList<>();
    }


    public interface OnItemClickListener {
        void onItemClick(View view, String url);
    }

    public void setData(List data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void addAll(List data) {
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
        return new MyViewHolder(view);
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

        public MyViewHolder(final View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_fg_info_small_pic);
            title = (TextView) itemView.findViewById(R.id.tv_fg_info_title);
            introduction = (TextView) itemView.findViewById(R.id.tv_fg_info_content_s);
            address = (TextView) itemView.findViewById(R.id.tv_fg_info_content_address);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, (String) itemView.getTag());
                    }
                }
            });
        }

        public TextView getAddress() {
            return address;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public TextView getIntroduction() {
            return introduction;
        }


        public TextView getTitle() {
            return title;
        }
    }
}
