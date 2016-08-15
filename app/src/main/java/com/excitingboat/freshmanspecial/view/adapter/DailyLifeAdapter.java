package com.excitingboat.freshmanspecial.view.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.utils.RoundImageView;

/**
 * Created by xushuzhan on 2016/8/15.
 */
public class DailyLifeAdapter extends RecyclerView.Adapter<DailyLifeAdapter.MyViewHolder>{

    Context context;
    Dialog dialog;
    ImageView dialogPicture;
    public boolean isShow = false;
    public DailyLifeAdapter( Context context){
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fg_daily_lify,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {




        Glide.with(context)
                .load("http://img4.imgtn.bdimg.com/it/u=885160639,3862652893&fm=21&gp=0.jpg")
                .into( holder.picture_dl);
        holder.picture_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog == null) {
                    dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_fg);
                    dialogPicture = (RoundImageView) dialog.findViewById(R.id.dialog_picture);

                }
                Glide.with(context)
                        .load("http://img4.imgtn.bdimg.com/it/u=885160639,3862652893&fm=21&gp=0.jpg")
                        .into(dialogPicture);

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Title;
        TextView Adress;
        ImageView picture_dl;
        public MyViewHolder(View itemView) {
            super(itemView);
            picture_dl = (ImageView) itemView.findViewById(R.id.iv_fg_dl_small_pic);
            Title = (TextView) itemView.findViewById(R.id.tv_fg_dl_title);
            Adress = (TextView) itemView.findViewById(R.id.tv_fg_dl_content_address);
        }
    }
}

