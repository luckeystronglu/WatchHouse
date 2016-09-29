package com.qf.watchhouse;

import android.content.Intent;
import android.widget.ImageView;

import com.qfkf.base.BaseActivity;


/**
 * Created by lenovo on 2016/9/26.
 */
public class WelcomeActivity extends BaseActivity {
    private ImageView imageView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {
        imageView = findViewByIds(R.id.iv_welcomme);
        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivityForAnimation(new Intent(WelcomeActivity.this,MainActivity.class),
                        R.anim.anim_city_bottom_in,R.anim.amin_city_quiet);
                finish();
            }
        },1500);
    }

}
