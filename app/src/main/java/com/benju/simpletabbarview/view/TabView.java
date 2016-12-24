package com.benju.simpletabbarview.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benju.simpletabbarview.R;

/**
 * Custom TabView for the actionbar with Selector
 * https://github.com/Mirkoddd/TabBarView
 */
public class TabView extends LinearLayout {

    private ImageView mImageView;
    private TextView mTextView;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.actionBarTabStyle);
    }

    public TabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionBarTabTextStyle, outValue, true);

        int textStyle = outValue.data;
        int pad = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());

        int tabWidthInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64, getResources().getDisplayMetrics()); // 64dp

        mImageView = new ImageView(context);
        mImageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
        mImageView.setScaleType(ScaleType.CENTER_INSIDE);

        mTextView = new TextView(context);
        mTextView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setCompoundDrawablePadding(pad);
        mTextView.setTextAppearance(context, textStyle);
        mTextView.setTextColor(ContextCompat.getColor(context, android.R.color.white));

        this.addView(mImageView);
        this.addView(mTextView);
        this.setLayoutParams(new LayoutParams(tabWidthInPixels, LayoutParams.MATCH_PARENT));
        this.setBackgroundResource(R.drawable.list_selector_white);
    }

    public void setIcon(int resId) {
        setIcon(ContextCompat.getDrawable(getContext(), resId));
    }

    public void setIcon(Drawable icon) {
        if (icon != null) {
            mImageView.setVisibility(View.VISIBLE);
            mImageView.setImageDrawable(icon);
        } else {
            mImageView.setVisibility(View.GONE);
        }
    }

    public void setText(int resId, int resIcon) {
        setText(getContext().getString(resId), resIcon);
    }

    // Tab with icon and text
    public void setText(CharSequence text, int resIcon) {
        mTextView.setText(text);
        mTextView.setCompoundDrawablesWithIntrinsicBounds(resIcon, 0, 0, 0);
    }
    
}