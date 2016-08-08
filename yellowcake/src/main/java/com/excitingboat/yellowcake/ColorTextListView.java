package com.excitingboat.yellowcake;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by PinkD on 2016/8/8.
 * ColorTextListView
 */
public class ColorTextListView extends FrameLayout {

    private final String TAG = "ColorTextListView";

    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_LEFT = 2;
    public static final int GRAVITY_RIGHT = 3;


    private ColorTextAdapter colorTextAdapter;
    private int max;
    private int gravity;
    private float margin;
    private MyObserver myObserver;


    public ColorTextListView(Context context) {
        this(context, null);
    }

    public ColorTextListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ColorTextListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.ColorTextListView);
        max = typedArray.getInt(R.styleable.ColorTextListView_max, 3);
        gravity = typedArray.getInt(R.styleable.ColorTextListView_gravity, 1);
        margin = typedArray.getDimension(R.styleable.ColorTextListView_marginTop, 10);
        Log.d(TAG, "ColorTextListView: gravity:" + gravity);
        Log.d(TAG, "ColorTextListView: max:" + max);

        typedArray.recycle();

        myObserver = new MyObserver();
    }

    public void setAdapter(ColorTextAdapter colorTextAdapter) {
        this.colorTextAdapter = colorTextAdapter;
        colorTextAdapter.registerDataSetObserver(myObserver);
        refreshView();
    }

    private void refreshView() {
        int count = colorTextAdapter.getCount();
        for (int i = 0; i < count; i++) {
            ColorTextView child = new ColorTextView(getContext());
            addView(child, -1);
            colorTextAdapter.setData(new ViewHolder(child), i);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        int tmp = 1;
        int height = getMeasuredHeight();
        if (childCount != 0) {
            tmp = childCount / max;
            if (childCount % max != 0) {
                tmp++;
            }
            height = getChildAt(tmp - 1).getMeasuredHeight();
        }
        Log.d(TAG, "onMeasure: " + tmp + "*" + height);
        setMeasuredDimension(getMeasuredWidth(), (int) (tmp * (height + margin)));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        layoutChildren(left, top, right, bottom);
    }

    private void layoutChildren(int left, int top, int right, int bottom) {
        final int parentLeft = getPaddingLeft();
        final int parentRight = right - left - getPaddingRight();
        final int parentTop = getPaddingTop();
        final int parentBottom = bottom - top - getPaddingBottom();
        int childCount = getChildCount();
        Log.d(TAG, "layoutChildren:parentLeft: " + parentLeft);
        Log.d(TAG, "layoutChildren:parentRight: " + parentRight);
        Log.d(TAG, "layoutChildren:parentTop: " + parentTop);
        Log.d(TAG, "layoutChildren:parentBottom: " + parentBottom);
        int remain = max;

        left = parentLeft;
        top = parentTop;
        boolean newLine = true;
        float len = (parentRight - parentLeft) / max;
        float marginStart = 0;
        for (int i = 0; i < childCount; i++) {
            Log.d(TAG, "layoutChildren:left: " + left);
            Log.d(TAG, "layoutChildren:top: " + top);
            Log.d(TAG, "layoutChildren:remain: " + remain);
            View child = getChildAt(i);
            Log.d(TAG, "child.getMeasuredWidth(): " + child.getMeasuredWidth());
            if (newLine) {
                switch (gravity) {
                    case GRAVITY_CENTER:
                        marginStart = (len - child.getMeasuredWidth()) / 2;
                        break;
                    case GRAVITY_LEFT:

                        break;
                    case GRAVITY_RIGHT:
                        marginStart = len - child.getMeasuredWidth() - margin;
                        break;
                }
                left += marginStart;
            }
            child.layout(left, top, (int) (left + len), parentBottom);
            if (remain > 1) {
                left += len;
                remain--;
                newLine = false;
            } else {
                remain = max;
                left = (int) (parentLeft + marginStart);
                top += child.getMeasuredHeight() + margin;
                Log.d(TAG, "layoutChildren:top----->" + top);
            }
        }
    }


    class MyObserver extends DataSetObserver {

        @Override
        public void onChanged() {
            int count = colorTextAdapter.getCount();
            if (count == getChildCount()) {
                for (int i = 0; i < count; i++) {
                    if (colorTextAdapter.getItem(i).getColor() == colorTextAdapter.getViewHolders().get(i).getRoundedRectangleView().getColor()
                            || !colorTextAdapter.getItem(i).getText().equals(colorTextAdapter.getViewHolders().get(i).getTextView().getText().toString())) {
                        colorTextAdapter.getViewHolders().get(i).getTextView().setText(colorTextAdapter.getItem(i).getText());
                        colorTextAdapter.getViewHolders().get(i).getRoundedRectangleView().setColor(colorTextAdapter.getItem(i).getColor());
                    }
                }
                requestLayout();
            } else {
                removeAllViews();
                refreshView();
            }
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public class ViewHolder {
        private ColorTextView colorTextView;

        public ViewHolder(ColorTextView colorTextView) {
            this.colorTextView = colorTextView;
        }

        public RoundedRectangleView getRoundedRectangleView() {
            return colorTextView.getRoundedRectangleView();
        }

        public TextView getTextView() {
            return colorTextView.getTextView();
        }

    }
}
