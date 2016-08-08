package com.excitingboat.freshmanspecial.view.activity;

import android.annotation.TargetApi;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.excitingboat.freshmanspecial.R;

import com.excitingboat.freshmanspecial.utils.BitmapUtil;
import com.excitingboat.freshmanspecial.utils.ScreenUtils;

/**
 * Created by xushuzhan on 2016/8/7.
 */
public class ShowBigPictureActivity extends Activity implements View.OnClickListener {
    ProgressBar progressBar;
    RelativeLayout relativeLayout;
    ImageView imageView;
    byte[] bisBackground;
    String BigPictureUrl;

//        public static void showBigPicture(Context context, byte [] backgroud,String BigPicUrl) {
//            long startMs = System.currentTimeMillis();
//        Intent intent = new Intent(context, ShowBigPictureActivity.class);
//        intent.putExtra("background", backgroud);
//        intent.putExtra("bigpicture",BigPicUrl);
//        context.startActivity(intent);
//            long endMs = System.currentTimeMillis();
//            Log.d("time_difference", "first:"+startMs
//                    +"->second:"+endMs
//                    +"->timeDifference:"
//                    +(endMs-startMs)
//            );
//    }
    public static void showBigPicture(Context context,String BigPicUrl, View view) {
        long startMs = System.currentTimeMillis();
        //获取屏幕截图
        Bitmap background = ScreenUtils.screenShot(view.getRootView());
        Bitmap background_small= ThumbnailUtils.extractThumbnail(background,
                (int) (view.getRootView().getWidth()/2),
                (int) (view.getRootView().getHeight()/2));
        Intent intent = new Intent(context, ShowBigPictureActivity.class);
        intent.putExtra("bigpicture", BigPicUrl);
        intent.putExtra("background",BitmapUtil.getBytesFromBitmap(background_small));
        context.startActivity(intent);
        long endMs = System.currentTimeMillis();
        Log.d("time_difference", "first:"+startMs
                +"->second:"+endMs
                +"->timeDifference:"
                +(endMs-startMs)
        );
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_big_picture);

            progressBar = (ProgressBar) findViewById(R.id.progress_show_picture);
            progressBar.setVisibility(View.VISIBLE);

        relativeLayout = (RelativeLayout) findViewById(R.id.ralativelaout);
        relativeLayout.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.pic_enlarge);
        imageView.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent != null) {
            bisBackground = intent.getByteArrayExtra("background");
            BigPictureUrl = intent.getStringExtra("bigpicture");
        }

        Bitmap background = BitmapFactory.decodeByteArray(bisBackground, 0, bisBackground.length);
        //高斯模糊后转换成drawable设置背景
        relativeLayout.setBackground(BitmapUtil.bitmapToDrawable(BitmapUtil.blur(ShowBigPictureActivity.this, background, 5)
                , getResources()));

        //显示图片
        Glide.with(ShowBigPictureActivity.this)
                .load(BigPictureUrl)
                //.placeholder(R.drawable.loading)//加载中显示的图片
                .error(R.drawable.picture_load_failed)//加载失败时显示的图片
                .into(imageView);

        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.abc_fade_out);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pic_enlarge:
                finish();
                overridePendingTransition(0, R.anim.abc_fade_out);
                break;
            case R.id.ralativelaout:
                finish();
                overridePendingTransition(0, R.anim.abc_fade_out);
                break;
            default:
                finish();
                overridePendingTransition(0, R.anim.abc_fade_out);
                break;

        }
    }
}
