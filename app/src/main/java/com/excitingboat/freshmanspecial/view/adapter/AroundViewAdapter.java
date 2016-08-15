package com.excitingboat.freshmanspecial.view.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.excitingboat.freshmanspecial.R;
import com.excitingboat.freshmanspecial.utils.RoundImageView;

/**
 * Created by xushuzhan on 2016/8/15.
 */
public class AroundViewAdapter  extends RecyclerView.Adapter<AroundViewAdapter.MyViewHolder>{
    Context context;
    Dialog dialog;
    ImageView dialogPicture;
    public boolean isShow = false;
    public AroundViewAdapter( Context context){
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fg_around_view,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        holder.Introduction.setText();
//        holder.Adress.setText();



        Glide.with(context)
                .load("http://img4.imgtn.bdimg.com/it/u=885160639,3862652893&fm=21&gp=0.jpg")
                .into( holder.picture_av);
        holder.picture_av.setOnClickListener(new View.OnClickListener() {
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
        TextView Introduction;
        TextView Adress;
        ImageButton more_i;
        ImageButton more_a;
        ImageView picture_av;
        public MyViewHolder(View itemView) {
            super(itemView);
            picture_av = (ImageView) itemView.findViewById(R.id.iv_fg_av_small_pic);
            Title = (TextView) itemView.findViewById(R.id.tv_fg_av_title);
            Introduction = (TextView) itemView.findViewById(R.id.tv_fg_av_content_s);
            Adress = (TextView) itemView.findViewById(R.id.tv_fg_av_content_address);
            more_i = (ImageButton) itemView.findViewById(R.id.ib_fg_av_more_i);
            more_a = (ImageButton) itemView.findViewById(R.id.ib_fg_av_more_a);
            MoreAndLess();

        }




        private void MoreAndLess() {
            //展开收起效果
            more_i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isShow){
                        Introduction.setMaxLines(2);
                        more_i.setBackgroundResource(R.drawable.more);
                        isShow = false;
                    }else {
                        Introduction.setMaxLines(10000);
                        more_i.setBackgroundResource(R.drawable.less);
                        isShow = true;
                    }
                }
            });
            Introduction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isShow){
                        Introduction.setMaxLines(2);
                        more_i.setBackgroundResource(R.drawable.more);
                        isShow = false;
                    }else {
                        Introduction.setMaxLines(10000);
                        more_i.setBackgroundResource(R.drawable.less);
                        isShow = true;
                    }
                }
            });
            more_a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isShow){
                        Adress.setMaxLines(1);
                        more_a.setBackgroundResource(R.drawable.more);
                        isShow = false;
                    }else {
                        Adress.setMaxLines(10000);
                        more_a.setBackgroundResource(R.drawable.less);
                        isShow = true;
                    }
                }
            });
            Adress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isShow){
                        Adress.setMaxLines(1);
                        more_a.setBackgroundResource(R.drawable.more);
                        isShow = false;
                    }else {
                        Adress.setMaxLines(10000);
                        more_a.setBackgroundResource(R.drawable.less);
                        isShow = true;
                    }
                }
            });
        }
    }
}

