package com.qf.watchhouse;

import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qf.fragment.DisCoverFragment;
import com.qf.fragment.HomeFragment;
import com.qf.fragment.MessageFragment;
import com.qf.fragment.MineFragment;
import com.qfkf.base.BaseActivity;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        radioGroup = findViewByIds(R.id.rg_tab);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.getChildAt(0).performClick();

    }

    //底部RadioGroup的点击事件（点击显示对应fragment）
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                fragmentManager(R.id.fl_fragment, new HomeFragment(), "home");
                break;
            case R.id.rb_discover:
                fragmentManager(R.id.fl_fragment, new DisCoverFragment(), "discover");
                break;
            case R.id.rb_message:
                fragmentManager(R.id.fl_fragment, new MessageFragment(), "message");
                break;
            case R.id.rb_mine:
                fragmentManager(R.id.fl_fragment, new MineFragment(), "mine");
                break;
        }
    }

    private long presstime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /**
         * web.canGoBack()判断webview是否有可以返回的页面
         */
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - presstime < 2000) {
                this.finish();
            } else {
                Toast.makeText(MainActivity.this, "再按一次退出本应用", Toast.LENGTH_SHORT).show();
                presstime = System.currentTimeMillis();
            }

        }
        return true;
    }
}
