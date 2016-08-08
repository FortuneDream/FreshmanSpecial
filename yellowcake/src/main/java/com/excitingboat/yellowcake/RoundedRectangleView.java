package com.excitingboat.yellowcake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by PinkD on 2016/8/8.
 * RoundedRectangleView
 */
public class RoundedRectangleView extends View {
    private boolean DEBUG = true;
    private static final String TAG = "RoundedRectangleView";
    private Paint mPaint;
    private RectF rectF;
    private float radius;
    private float cornerDegree;

    public RoundedRectangleView(Context context) {
        this(context, null);
    }

    public RoundedRectangleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedRectangleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        int width = 0;
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                if (DEBUG) {

                    Log.d(TAG, "widthMode: EXACTLY");
                }
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                if (DEBUG) {
                    Log.d(TAG, "widthMode: UNSPECIFIED");
                }
            case MeasureSpec.AT_MOST:
                if (DEBUG) {
                    Log.d(TAG, "widthMode: AT_MOST");
                }
                width = Utils.dp2px(getContext(), 24);
                break;

        }
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                if (DEBUG) {
                    Log.d(TAG, "heightMode: EXACTLY");
                }
                height = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                if (DEBUG) {
                    Log.d(TAG, "heightMode: UNSPECIFIED");
                }
            case MeasureSpec.AT_MOST:
                if (DEBUG) {
                    Log.d(TAG, "heightMode: AT_MOST");
                }
                height = Utils.dp2px(getContext(), 24);
                break;
        }
        if (DEBUG) {
            Log.d(TAG, "onMeasure: width:" + width + "height:" + height);
        }
        radius = Math.min(height, width) / 2;
//        textSize = Utils.px2sp(getContext(), Utils.dp2px(getContext(), radius / scale / 8 * 7));
        setMeasuredDimension((int) (2 * radius), (int) (2 * radius));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF.set(0, 0, getMeasuredWidth(), getMeasuredHeight());

    }


    private void resetPaint() {
        mPaint.reset();
        mPaint.setAntiAlias(true);
    }

    private void resetStrokePaint() {
        resetPaint();
        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(15 / scale * radius / 180);
    }

}
