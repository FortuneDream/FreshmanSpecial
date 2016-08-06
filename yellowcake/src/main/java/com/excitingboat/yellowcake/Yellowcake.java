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
    private float scale;
    private float cornerDegree;
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

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Yellowcake);
        divide = typedArray.getBoolean(R.styleable.Yellowcake_divide, true);
        border = typedArray.getBoolean(R.styleable.Yellowcake_border, true);
        emptyCenter = typedArray.getBoolean(R.styleable.Yellowcake_emptyCenter, true);
        textColor = typedArray.getColor(R.styleable.Yellowcake_textColor, 1);
        scale = typedArray.getFloat(R.styleable.Yellowcake_scale, 4);
        //TODO 需要巨量的计算来适配
//        cornerDegree = typedArray.getFloat(R.styleable.Yellowcake_cornerDegree, 5);
        cornerDegree = 5;
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
                Log.d(TAG, "widthMode: EXACTLY");
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.d(TAG, "widthMode: UNSPECIFIED");
            case MeasureSpec.AT_MOST:
                Log.d(TAG, "widthMode: AT_MOST");
                width = Utils.dp2px(getContext(), 128);
                break;

        }
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                Log.d(TAG, "heightMode: EXACTLY");
                height = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.d(TAG, "heightMode: UNSPECIFIED");
            case MeasureSpec.AT_MOST:
                Log.d(TAG, "heightMode: AT_MOST");
                height = Utils.dp2px(getContext(), 128);
                break;

        }
        Log.d(TAG, "onMeasure: width:" + width + "height:" + height);
        radius = Math.min(height, width) / 2 - Utils.dp2px(getContext(), 2 * scale);
        textSize = Utils.px2sp(getContext(), Utils.dp2px(getContext(), radius / scale / 8 * 7));
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boolean drawBorder = true;
        boolean borderDrawn = false;
        if (emptyCenter) {
//        Cut canvas
//        DIFFERENCE/XOR   ->  cut inner
//        null/others          ->  cut outer
            path.addCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radius / 3 - 2, Path.Direction.CW);
            canvas.clipPath(path, Region.Op.DIFFERENCE);
        }

//TODO 多次画
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
                    paint.setColor(colors[i]);
                } else {
                    resetStrokePaint();
                    paint.setColor(getDarkColor(colors[i]));
                }
                final float tmpScale = 15 / scale * radius / 180;
                double[] triFunctions = getTriFunctions((float) startAngle, (float) sweepAngle);
                Log.d(TAG, "sin: " + triFunctions[SIN] + "cos: " + triFunctions[COS]);
                rectF.set((float) (getMeasuredWidth() / 2 - radius + triFunctions[COS] * tmpScale),
                        (float) (getMeasuredHeight() / 2 - radius + triFunctions[SIN] * tmpScale),
                        (float) (getMeasuredWidth() / 2 + radius + triFunctions[COS] * tmpScale),
                        (float) (getMeasuredHeight() / 2 + radius + triFunctions[SIN] * tmpScale));
                canvas.drawArc(rectF, (float) startAngle + cornerDegree, (float) sweepAngle - 2 * cornerDegree, true, paint);
                //小扇形
                double cornerRadius = getCornerRadius(cornerDegree, radius);
                Log.d(TAG, "cornerRadius: " + cornerRadius);
                rectF.set((float) (getMeasuredWidth() / 2 - radius + triFunctions[COS] * tmpScale + cornerRadius),
                        (float) (getMeasuredHeight() / 2 - radius + triFunctions[SIN] * tmpScale + cornerRadius),
                        (float) (getMeasuredWidth() / 2 + radius + triFunctions[COS] * tmpScale - cornerRadius),
                        (float) (getMeasuredHeight() / 2 + radius + triFunctions[SIN] * tmpScale - cornerRadius));
                //误差导致出现间隙，所以角度要稍微调整
                canvas.drawArc(rectF, (float) startAngle, (float) (cornerDegree + 0.1), true, paint);
                canvas.drawArc(rectF, (float) (startAngle + sweepAngle - cornerDegree - 0.1), cornerDegree, true, paint);

                //角圆
                double[] tmp = getTriFunctions(startAngle, 2.3 * 5);
                Log.d(TAG, "X: " + (getMeasuredWidth() / 2 + (float) (tmp[COS] * (radius - cornerRadius) + triFunctions[COS] * tmpScale)) + "  Y: " + (getMeasuredHeight() / 2 + (float) (tmp[SIN] * (radius - cornerRadius) + triFunctions[SIN] * tmpScale)));
                if (borderDrawn) {
                    paint.setColor(colors[i]);
                } else {
                    paint.setColor(getDarkColor(colors[i]));
                }
                canvas.drawCircle(
                        getMeasuredWidth() / 2 + (float) (tmp[COS] * (radius - cornerRadius) + triFunctions[COS] * tmpScale),
                        getMeasuredHeight() / 2 + (float) (tmp[SIN] * (radius - cornerRadius) + triFunctions[SIN] * tmpScale),
                        (float) cornerRadius, paint);

                tmp = getTriFunctions(startAngle + sweepAngle - 2.3 * 5, 2.3 * 5);
                canvas.drawCircle(
                        getMeasuredWidth() / 2 + (float) (tmp[COS] * (radius - cornerRadius) + triFunctions[COS] * tmpScale),
                        getMeasuredHeight() / 2 + (float) (tmp[SIN] * (radius - cornerRadius) + triFunctions[SIN] * tmpScale), (
                                float) cornerRadius, paint);

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
                paint.setColor(textColor);
            } else {
                paint.setColor(getDarkColor(colors[i]));
            }
            paint.setTextSize(textSize);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(Utils.formatNumber(percents[i]) + "%", (float) positions[i][X], (float) positions[i][Y], paint);
        }


    }

    private void drawGraph(boolean stroke) {
        if (stroke) {
            paint.setStyle(Paint.Style.STROKE);
        }
    }

    private void resetPaint() {
        paint.reset();
        paint.setAntiAlias(true);
    }

    private void resetStrokePaint() {
        resetPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15 / scale * radius / 180);
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
     * Function 自动改变颜色
     *
     * @param color input color
     * @return changed color
     */
    private int getDarkColor(int color) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        red *= (float) red / 0xFF * 0.8;
        green *= (float) green / 0xFF * 0.8;
        blue *= (float) blue / 0xFF * 0.8;
        color = Color.rgb(red, green, blue);
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
