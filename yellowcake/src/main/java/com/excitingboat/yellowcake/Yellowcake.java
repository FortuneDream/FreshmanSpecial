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

import static com.excitingboat.yellowcake.Utils.getDarkColor;

/**
 * Created by PinkD on 2016/8/5.
 * Cake View
 */
public class Yellowcake extends View {
    private boolean DEBUG = false;
    private static final String TAG = "Yellowcake";
    private static final int X = 0;
    private static final int Y = 1;
    private static final int COS = 0;
    private static final int SIN = 1;
    private boolean divide;
    private boolean border;
    private boolean emptyCenter;
    private int textColor;
    private float scale;
    private final float requestedCornerDegree;
    private float cornerDegree;
    private int textSize;
    private int[] colors;
    private double[] numbers;
    private double sum;
    private float radius;
    private Paint mPaint;
    private RectF rectF;
    private Path path;

    private float dpScale;
    private boolean centerBorder = false;


    public void setCenterBorder(boolean centerBorder) {
        this.centerBorder = centerBorder;
    }

    public void setData(double[] numbers, int[] colors) {
        this.colors = colors;
        this.numbers = numbers;
        sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        invalidate();
    }

    public Yellowcake(Context context) {
        this(context, null);
    }

    public Yellowcake(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        //Default data
//        setData(new int[]{3, 1, 3}, new int[]{0xFF66CCFF, 0xFFEE82EE, 0xFF66CCFF});
        setData(new double[]{1}, new int[]{0xFF66CCFF});
    }


    public Yellowcake(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        rectF = new RectF();
        path = new Path();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Yellowcake);
        divide = typedArray.getBoolean(R.styleable.Yellowcake_divide, true);
        border = typedArray.getBoolean(R.styleable.Yellowcake_border, true);
        emptyCenter = typedArray.getBoolean(R.styleable.Yellowcake_emptyCenter, true);
        textColor = typedArray.getColor(R.styleable.Yellowcake_textColor, 1);
        scale = typedArray.getFloat(R.styleable.Yellowcake_scale, 4);
        //TODO 需要巨量的计算来适配
//        cornerDegree = typedArray.getFloat(R.styleable.Yellowcake_cornerDegree, 5);
        requestedCornerDegree = 5;
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
                width = Utils.dp2px(getContext(), 128);
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
                height = Utils.dp2px(getContext(), 128);
                break;

        }
        if (DEBUG) {
            Log.d(TAG, "onMeasure: width:" + width + "height:" + height);
        }
        radius = Math.min(height, width) / 2 - Utils.dp2px(getContext(), 2 * scale);
        textSize = Utils.px2sp(getContext(), Utils.dp2px(getContext(), radius / scale / 8 * 7));
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boolean drawBorder = true;
        boolean borderDrawn = false;
        dpScale = 20 / scale * radius / 180;
        if (numbers.length == 1) {
            cornerDegree = 0;
        } else {
            cornerDegree = requestedCornerDegree;
        }
        if (emptyCenter) {
//        Cut canvas
//        DIFFERENCE/XOR   ->  cut inner
//        null/others          ->  cut outer
            path.addCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius / 3 - dpScale / 5, Path.Direction.CW);
            canvas.clipPath(path, Region.Op.DIFFERENCE);
        }

        final double finalStartAngle = (Math.random() * 360);
        double sweepAngle;
        double percent;
        double[] percents = new double[numbers.length];
        double[][] positions = new double[numbers.length][2];

        while (drawBorder) {
            double startAngle = finalStartAngle;
            for (int i = 0; i < numbers.length; i++) {
                percent = numbers[i] * 100D / sum;
                sweepAngle = percent * 3.6;

                //保存文字位置数据
                positions[i] = getTextPosition(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius, (float) startAngle, (float) sweepAngle, 1F / 3);
                percents[i] = percent;

                //去掉两边的扇形->小于半径的扇形->角上的圆
                //扇形
                if (borderDrawn) {
                    resetPaint();
                    mPaint.setColor(colors[i]);
                } else {
                    resetStrokePaint();
                    mPaint.setColor(getDarkColor(colors[i]));
                }
                double[] triFunctions = getTriFunctions((float) startAngle, (float) sweepAngle);
                if (DEBUG) {
                    Log.d(TAG, "sin: " + triFunctions[SIN] + "cos: " + triFunctions[COS]);
                }
                rectF.set((float) (getMeasuredWidth() / 2 - radius + triFunctions[COS] * dpScale),
                        (float) (getMeasuredHeight() / 2 - radius + triFunctions[SIN] * dpScale),
                        (float) (getMeasuredWidth() / 2 + radius + triFunctions[COS] * dpScale),
                        (float) (getMeasuredHeight() / 2 + radius + triFunctions[SIN] * dpScale));
                canvas.drawArc(rectF, (float) startAngle + cornerDegree, (float) sweepAngle - 2 * cornerDegree, true, mPaint);
                //小扇形
                double cornerRadius = getCornerRadius(cornerDegree, radius);
                if (DEBUG) {
                    Log.d(TAG, "cornerRadius: " + cornerRadius);
                }
                rectF.set((float) (getMeasuredWidth() / 2 - radius + triFunctions[COS] * dpScale + cornerRadius),
                        (float) (getMeasuredHeight() / 2 - radius + triFunctions[SIN] * dpScale + cornerRadius),
                        (float) (getMeasuredWidth() / 2 + radius + triFunctions[COS] * dpScale - cornerRadius),
                        (float) (getMeasuredHeight() / 2 + radius + triFunctions[SIN] * dpScale - cornerRadius));
                //误差导致出现间隙，所以角度要稍微调整
                canvas.drawArc(rectF, (float) startAngle, (float) (cornerDegree + 0.1), true, mPaint);
                canvas.drawArc(rectF, (float) (startAngle + sweepAngle - cornerDegree - 0.1), cornerDegree, true, mPaint);

                //角圆
                double[] tmp = getTriFunctions(startAngle, 2.3 * 5);
                if (DEBUG) {
                    Log.d(TAG, "X: " + (getMeasuredWidth() / 2 + (float) (tmp[COS] * (radius - cornerRadius) + triFunctions[COS] * dpScale)) + "  Y: " + (getMeasuredHeight() / 2 + (float) (tmp[SIN] * (radius - cornerRadius) + triFunctions[SIN] * dpScale)));
                }
                if (borderDrawn) {
                    mPaint.setColor(colors[i]);
                } else {
                    mPaint.setColor(getDarkColor(colors[i]));
                }
                canvas.drawCircle(
                        getMeasuredWidth() / 2 + (float) (tmp[COS] * (radius - cornerRadius) + triFunctions[COS] * dpScale),
                        getMeasuredHeight() / 2 + (float) (tmp[SIN] * (radius - cornerRadius) + triFunctions[SIN] * dpScale),
                        (float) cornerRadius, mPaint);

                tmp = getTriFunctions(startAngle + sweepAngle - 2.3 * 5, 2.3 * 5);
                canvas.drawCircle(
                        getMeasuredWidth() / 2 + (float) (tmp[COS] * (radius - cornerRadius) + triFunctions[COS] * dpScale),
                        getMeasuredHeight() / 2 + (float) (tmp[SIN] * (radius - cornerRadius) + triFunctions[SIN] * dpScale), (
                                float) cornerRadius, mPaint);

                //中间边界
                //TODO 需要优化方案。。。因为误差导致了一些奇怪的问题。。。
                if (centerBorder) {
                    if (border && borderDrawn) {
                        resetPaint();
                        mPaint.setColor(getDarkColor(colors[i]));
                        rectF.set((float) (getMeasuredWidth() / 2 - radius / 3 + triFunctions[COS] * dpScale),
                                (float) (getMeasuredHeight() / 2 - radius / 3 + triFunctions[SIN] * dpScale),
                                (float) (getMeasuredWidth() / 2 + radius / 3 + triFunctions[COS] * dpScale),
                                (float) (getMeasuredHeight() / 2 + radius / 3 + triFunctions[SIN] * dpScale));
                        canvas.drawArc(rectF, (float) startAngle, (float) sweepAngle, true, mPaint);
                    }
                }

//            小圆圆心标注
//            paint.setColor(Color.BLACK);
//            paint.setStrokeWidth(5);
//            canvas.drawPoint(getMeasuredWidth() / 2 + (float) (tmp[COS] * (radius - cornerRadius) + triFunctions[COS] * tmpScale), (getMeasuredHeight() / 2 + (float) (tmp[SIN] * (radius - cornerRadius) + triFunctions[SIN] * tmpScale)), paint);

                startAngle += sweepAngle;
            }
            if (!border || borderDrawn) {
                drawBorder = false;
            } else {
                borderDrawn = true;
            }
        }

        //文字
        for (int i = 0; i < numbers.length; i++) {
            resetPaint();
            if (textColor != 1) {
                mPaint.setColor(textColor);
            } else {
                mPaint.setColor(getDarkColor(colors[i]));
            }
            mPaint.setTextSize(textSize);
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(Utils.formatNumber(percents[i]) + "%", (float) positions[i][X], (float) positions[i][Y], mPaint);
        }


    }

    private void drawGraph(boolean stroke) {
        if (stroke) {
            mPaint.setStyle(Paint.Style.STROKE);
        }
    }

    private void resetPaint() {
        mPaint.reset();
        mPaint.setAntiAlias(true);
    }

    private void resetStrokePaint() {
        resetPaint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15 / scale * radius / 180);
    }

    /**
     * @param x                   x
     * @param y                   y
     * @param r                   radius
     * @param startAngle          startAngle
     * @param sweepAngle          sweepAngle
     * @param centerCutPercentage cut center
     * @return textPosition
     */
    private double[] getTextPosition(float x, float y, float r, float startAngle, float sweepAngle, float centerCutPercentage) {
        double targetAngle = (startAngle + sweepAngle / 2) / 180 * Math.PI;
        float realRadius = r * (1 + centerCutPercentage) / 2;
        x += Math.cos(targetAngle) * realRadius;
        y += Math.sin(targetAngle) * realRadius;
        return new double[]{x, y};
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

    /**
     * @param cornerDegree cornerDegree
     * @param radius       radius
     * @return corner circle radius
     */
    private double getCornerRadius(float cornerDegree, float radius) {
        double sin = Math.sin(Math.toRadians(cornerDegree));
        return sin * radius;
    }

}
