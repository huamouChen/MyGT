/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.huamouchen.mygt.widgets;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * Subclass of {@link RelativeLayout} that supports percentage based dimensions and
 * margins.
 *
 * You can specify dimension or a margin of child by using attributes with "Percent" suffix. Follow
 * this example:
 *
 * <pre class="prettyprint">
 * &lt;android.support.percent.PercentRelativeLayout
 *         xmlns:android="http://schemas.android.com/apk/res/android"
 *         xmlns:app="http://schemas.android.com/apk/res-auto"
 *         android:layout_width="match_parent"
 *         android:layout_height="match_parent"/&gt
 *     &lt;ImageView
 *         app:layout_widthPercent="50%"
 *         app:layout_heightPercent="50%"
 *         app:layout_marginTopPercent="25%"
 *         app:layout_marginLeftPercent="25%"/&gt
 * &lt;/android.support.percent.PercentFrameLayout/&gt
 * </pre>
 *
 * The attributes that you can use are:
 * <ul>
 *     <li>{@code layout_widthPercent}
 *     <li>{@code layout_heightPercent}
 *     <li>{@code layout_marginPercent}
 *     <li>{@code layout_marginLeftPercent}
 *     <li>{@code layout_marginTopPercent}
 *     <li>{@code layout_marginRightPercent}
 *     <li>{@code layout_marginBottomPercent}
 *     <li>{@code layout_marginStartPercent}
 *     <li>{@code layout_marginEndPercent}
 * </ul>
 *
 * It is not necessary to specify {@code layout_width/height} if you specify {@code
 * layout_widthPercent.} However, if you want the view to be able to take up more space than what
 * percentage value permits, you can add {@code layout_width/height="wrap_content"}. In that case
 * if the percentage size is too small for the View's content, it will be resized using
 * {@code wrap_content} rule.
 */
public class PercentRelativeLayout extends RelativeLayout {
    private static final String TAG = "PercentRelativeLayout";
    private final PercentLayoutHelper mHelper = new PercentLayoutHelper(this);

    public PercentRelativeLayout(Context context) {
        super(context);
        mHelper.setmContext(context);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHelper.setmContext(context);
    }

    public PercentRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mHelper.setmContext(context);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int tmpHeightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, heightMode);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int tmpWidthMeasureSpec = MeasureSpec.makeMeasureSpec(widthSize, widthMode);

        Log.d(TAG, MeasureSpec.toString(heightMeasureSpec));
        //fixed scrollview height problems
        if (heightMode == MeasureSpec.UNSPECIFIED && getParent() != null && (getParent() instanceof ScrollView))
        {
            int baseHeight = 0;
            Context context = getContext();
            if (context instanceof Activity)
            {
                Activity act = (Activity) context;
                int measuredHeight = act.findViewById(android.R.id.content).getMeasuredHeight();
                baseHeight = measuredHeight;
                Log.d(TAG, "measuredHeight = " + measuredHeight);
            } else
            {
                baseHeight = getScreenHeight();
                Log.d(TAG, "scHeight = " + baseHeight);
            }
            tmpHeightMeasureSpec = MeasureSpec.makeMeasureSpec(baseHeight, heightMode);
        }

        if(widthMode == MeasureSpec.UNSPECIFIED && getParent() != null && (getParent() instanceof HorizontalScrollView)){
            int baseWidth = 0;
            Context context = getContext();
            if (context instanceof Activity)
            {
                Activity act = (Activity) context;
                int measuredWidth = act.findViewById(android.R.id.content).getMeasuredWidth();
                baseWidth = measuredWidth;
                Log.d(TAG, "measuredWidth = " + measuredWidth);
            } else
            {
                baseWidth = getScreenWidth();
                Log.d(TAG, "scWidth = " + baseWidth);
            }
            tmpWidthMeasureSpec = MeasureSpec.makeMeasureSpec(baseWidth, widthMode);
        }

        mHelper.adjustChildren(tmpWidthMeasureSpec, tmpHeightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHelper.handleMeasuredStateTooSmall()) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private int getScreenHeight()
    {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        if(wm != null)
            wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    private int getScreenWidth(){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        if(wm != null)
            wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mHelper.restoreOriginalParams();
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams
            implements PercentLayoutHelper.PercentLayoutParams {
        private PercentLayoutHelper.PercentLayoutInfo mPercentLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mPercentLayoutInfo = PercentLayoutHelper.getPercentLayoutInfo(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        @Override
        public PercentLayoutHelper.PercentLayoutInfo getPercentLayoutInfo() {
            return mPercentLayoutInfo;
        }

        @Override
        protected void setBaseAttributes(TypedArray a, int widthAttr, int heightAttr) {
            PercentLayoutHelper.fetchWidthAndHeight(this, a, widthAttr, heightAttr);
        }
    }
}
