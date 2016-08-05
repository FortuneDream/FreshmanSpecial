package com.excitingboat.yellowcake;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by PinkD on 2016/8/5.
 * Cake View
 */
public class Yellowcake extends View {
    private static final String TAG = "Yellowcake";
    private static final int X = 0;
    private static final int Y = 1;
    private static final int COS = 0;
    private static final int SIN = 1;
    private boolean divide;
    private boolean border;
    private boolean emptyCenter;
    private int textColor;
    private int scale;
    private int textSize;
    private int[] colors;
    private int[] numbers;
    private int sum;
    private float radius;
    private Paint paint;
    private RectF rectF;
    private Path path;

    public void setData(int[] numbers, int[] colors) {
        this.colors = colors;
        this.numbers = numbers;
        sum = 0;
        for (int number : numbers) {
            sum += number;
        }
    }

    public Yellowcake(Context context) {
        this(context, null);
    }

    public Yellowcake(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public Yellowcake(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        rectF = new RectF();
        path = new Path();

        //TODO remove
        //-------------------------------------------------
        setData(new int[]{3, 1, 3}, new int[]{0xFF66CCFF, 0xFFEE82EE, 0xFF66CCFF});
        //-------------------------------------------------

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Yellowcake);
        divide = typedArray.getBoolean(R.styleable.Yellowcake_divide, false);
        border = typedArray.getBoolean(R.styleable.Yellowcake_border, false);
        emptyCenter = typedArray.getBoolean(R.styleable.Yellowcake_emptyCenter, false);
        textColor = typedArray.getColor(R.styleable.Yellowcake_textColor, Color.WHITE);
        scale = typedArray.getInt(R.styleable.Yellowcake_textScale, 5);
        typedArray.recycle();
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
            case MeasureSpec.UNSPECIFIED:
                Log.d(TAG, "onMeasure: UNSPECIFIED");
            case MeasureSpec.EXACTLY:
                Log.d(TAG, "onMeasure: EXACTLY");
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                Log.d(TAG, "onMeasure: AT_MOST");
//TODO
                width = Utils.dp2px(getContext(), 128);
                break;

        }
        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                //TODO
                height = Utils.dp2px(getContext(), 128);
                break;

        }
        Log.d(TAG, "onMeasure: width:" + width + "height:" + height);
        radius = Math.min(height, width) / 2 - 5;
        textSize = Utils.px2sp(getContext(), Utils.dp2px(getContext(), radius / scale));
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (emptyCenter) {
//        Cut canvas
//        DIFFERENCE/XOR   ->  cut inner
//        null/others          ->  cut outer
            path.addCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius / 3, Path.Direction.CW);
            canvas.clipPath(path, Region.Op.DIFFERENCE);
        }

//TODO 多次画
        double startAngle = (Math.random() * 360);
        double angleCount = 0;
        double sweepAngle;
        double percent;
        double[] percents = new double[numbers.length];
        double[][] positions = new double[numbers.length][2];
        for (int i = 0; i < numbers.length; i++) {
            percent = numbers[i] * 100D / sum;
            sweepAngle = percent * 3.6;

            //保存文字位置数据
            positions[i] = getTextPosition(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius, (float) startAngle, (float) sweepAngle, 1F / 3);
            percents[i] = percent;

            //扇形
            resetPaint();
            paint.setColor(colors[i]);
            final int scale = 3;//TODO
            double[] tmp = getTriFunctions((float) startAngle, (float) sweepAngle);
            Log.d(TAG, "sin: " + tmp[SIN] + "cos: " + tmp[COS]);
            rectF.set((float) (getMeasuredWidth() / 2 - radius + tmp[COS] * scale), (float) (getMeasuredHeight() / 2 - radius + tmp[SIN] * scale),
                    (float) (getMeasuredWidth() / 2 + radius + tmp[COS] * scale), (float) (getMeasuredHeight() / 2 + radius + tmp[SIN] * scale));
            canvas.drawArc(rectF, (float) startAngle, (float) sweepAngle, true, paint);


            startAngle += sweepAngle;
            angleCount += sweepAngle;
            if (angleCount > 360) {
                throw new IllegalArgumentException("Angle more than 360");
            }
        }

        //文字
        for (int i = 0; i < numbers.length; i++) {
            resetPaint();
            paint.setColor(getDarkColor(colors[i]));
            paint.setTextSize(textSize);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(Utils.formatNumber(percents[i]) + "%", (float) positions[i][X], (float) positions[i][Y], paint);
        }


    }

    private void resetPaint() {
        paint.reset();
        paint.setAntiAlias(true);
    }


    private double[] getTextPosition(float x, float y, float r, float startAngle, float sweepAngle, float centerCutPercentage) {
        double targetAngle = (startAngle + sweepAngle / 2) / 180 * Math.PI;
        float realRadius = r * (1 + centerCutPercentage) / 2;
        x += Math.cos(targetAngle) * realRadius;
        y += Math.sin(targetAngle) * realRadius;
        return new double[]{x, y};
    }


    /**
     * Function 自动改变颜色
     *
     * @param color input color
     * @return changed color
     */
    private int getDarkColor(int color) {
        color = color & 0xFFFFFF;
        color = color >> 1;
        color = color | 0xFF000000;
        System.out.printf("%x", color);
        return color;
    }

    /**
     * Function 求出正余弦
     *
     * @param startAngle 开始角度
     * @param sweepAngle 扫过的角度
     * @return cos, sin
     */
    private double[] getTriFunctions(double startAngle, double sweepAngle) {
        double targetAngle = (startAngle + sweepAngle / 2) / 180 * Math.PI;
        return new double[]{Math.cos(targetAngle), Math.sin(targetAngle)};
    }

}
