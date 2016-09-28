package com.qf.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.qf.entity.HeaderViewEntity;
import com.qf.fragment.HeaderViewFrag;
import com.qf.utils.Contants;
import com.qf.utils.JSONUtil;
import com.qf.watchhouse.R;
import com.qfkf.util.DownUtil;
import com.qfkf.widget.NavView;
import com.qfkf.widget.loopviewpager.LoopViewPager;

import java.util.List;


/**
 * Created by Administrator on 2016/8/4.
 */
public class HomeVpView extends FrameLayout implements DownUtil.OnDownListener {
    private ViewPager viewpager;
    private int cityId;
    private FragmentManager fragmentmanager;


    private OnLoadEndListener onLoadEndListener;
    public void setOnLoadEndListener(OnLoadEndListener onLoadEndListener) {
        this.onLoadEndListener = onLoadEndListener;
    }

    //数据源
    private List<HeaderViewEntity> datas;

    //适配器
    private HeaderAdapter headeradapter;

    //导航控件
    private NavView navView;



    public HomeVpView(Context context) {
        this(context,null);
    }

    public HomeVpView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomeVpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }


    //初始化
    private void init() {
        //this,true,相应布局所有的子控件都可以在当前布局findViewById
        LayoutInflater.from(getContext()).inflate(R.layout.causoul_home_frag,this,true);
        viewpager = (ViewPager) findViewById(R.id.hfrag_headvpager);

        navView = (NavView) findViewById(R.id.navView);
        navView.setViewPager(viewpager);
    }



    //设置城市id
    public void setCityid(FragmentManager fragmentmanager, int cityId){
        this.cityId = cityId;
        this.fragmentmanager = fragmentmanager;
        loadDatas();
    }

    private void loadDatas() {
        String url = String.format(Contants.URL_WEBVIEW,cityId);
        new DownUtil().setOnDownListener(this).downJSON(url);
    }


    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return JSONUtil.getHeaderByJson(json);

            }
            return null;
    }

    @Override
    public void downSucc(Object object) {
        datas = (List<HeaderViewEntity>) object;
        if (datas != null && datas.size() > 0) {
            //设置导航控件的个数
            //调用回调接口

            navView.setCount(datas.size());
            headeradapter = new HeaderAdapter(fragmentmanager, datas);
            viewpager.setAdapter(headeradapter);


            if (onLoadEndListener != null){
                onLoadEndListener.isHaveData();
            }
        }else {
            //调用回调接口
            if (onLoadEndListener != null){
                onLoadEndListener.isNotHaveData();
            }
//               this.setVisibility(GONE);
        }
    }

    class HeaderAdapter extends FragmentStatePagerAdapter {
            List<HeaderViewEntity> list;

            public HeaderAdapter(FragmentManager fm, List<HeaderViewEntity> list) {
                super(fm);
                this.list = list;
            }

            @Override
            public Fragment getItem(int position) {
                position = LoopViewPager.toRealPosition(position,getCount());


                return HeaderViewFrag.getInstance(datas.get(position % getCount()));
            }

            @Override
            public int getCount() {
                return list.size();
            }
        }


    public interface OnLoadEndListener{
        void isHaveData();
        void isNotHaveData();
    }

}
