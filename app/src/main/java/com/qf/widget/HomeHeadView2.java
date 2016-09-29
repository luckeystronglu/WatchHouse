package com.qf.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.qf.watchhouse.R;

/**
 * Created by lenovo on 2016/9/29.
 */
public class HomeHeadView2 extends LinearLayout {

    public HomeHeadView2(Context context) {
        this(context,null);
    }

    public HomeHeadView2(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomeHeadView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.frag1_header_mid,this,true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return true;
    }
}
