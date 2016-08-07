package com.excitingboat.freshmanspecial.view.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.excitingboat.freshmanspecial.R;

import com.excitingboat.freshmanspecial.utils.BitmapUtil;

/**
 * Created by xushuzhan on 2016/8/7.
 */
public class ShowBigPictureActivity extends Activity implements View.OnClickListener {
    RelativeLayout relativeLayout;
    ImageView imageView;
    byte [] bisBackground;

    public static void showBigPicture(Context context, byte [] backgroud) {
        Intent intent = new Intent(context, ShowBigPictureActivity.class);
        intent.putExtra("background", backgroud);
        context.startActivity(intent);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_big_picture);

        relativeLayout = (RelativeLayout) findViewById(R.id.ralativelaout);
        relativeLayout.setOnClickListener(this);
        imageView = (ImageView) findViewById(R.id.pic_enlarge);
        imageView.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent != null){
            bisBackground= intent.getByteArrayExtra("background");
        }
        Bitmap background= BitmapFactory.decodeByteArray(bisBackground, 0, bisBackground.length);
        //高斯模糊后转换成drawable设置背景
        relativeLayout.setBackground(BitmapUtil.bitmapToDrawable(BitmapUtil.blur(ShowBigPictureActivity.this,background,5)
                ,getResources()));

        //显示图片并释放内存
        if(FreshmenGuideActivity.pictureChecked!=null){
            imageView.setImageBitmap(FreshmenGuideActivity.pictureChecked);
            FreshmenGuideActivity.pictureChecked= null;}
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.abc_fade_out);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
