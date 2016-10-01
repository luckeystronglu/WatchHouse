package com.qf.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.adapter.HouseNewsAdapter;
import com.qf.entity.CityEntity;
import com.qf.entity.HouseNewsEntity;
import com.qf.utils.Contants;
import com.qf.utils.JSONUtil;
import com.qf.watchhouse.CityChoiceActivity;
import com.qf.watchhouse.HomeWebActivity;
import com.qf.watchhouse.NewHouseActivity;
import com.qf.watchhouse.R;
import com.qf.widget.HomeHeadView2;
import com.qf.widget.HomeVpView;
import com.qfkf.base.BaseFragment;
import com.qfkf.util.DownUtil;
import com.qfkf.util.ShareUtil;
import com.qfkf.widget.PullandFreshView;
import com.zxing.activity.CaptureActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2016/9/25.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, DownUtil.OnDownListener, PullandFreshView.OnPullToRefreshListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    @Bind(R.id.tv_selectcity)
    TextView tv_city;
    @Bind(R.id.qr_cord)
    ImageView qrcode_img;

    public static final int REQUEST_CODE = 0x001;
    public static final int RESULT_CODE = 0x002;
    public static final int REQUEST_ZXING_CODE = 0x003;

    private int cityId = 1; //当前的城市ID

//    private ImageView qrcode_img;
//    private TextView tv_city;//城市选择的tv

    //当前新闻页需要显示的条数
    private int newsCount = 10;
    //请求连接
    private String newsUrl;
    //列表
//    private ListView newsListView;
    private PullandFreshView newsListView;
    //数据源
    private List<HouseNewsEntity> datas;

    //适配器
    private HouseNewsAdapter newsAdapter;

    //头部轮播图
    private HomeVpView homeVpView;

    //首页第二个头部控件（8个可点击图标）
    private HomeHeadView2 homeHeadView2;
    private TextView mid_xinfang;
    private String cityname;


    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(View view) {
//        tv_city = (TextView) view.findViewById(R.id.tv_selectcity);
//        tv_city.setOnClickListener(this);
//
//        qrcode_img = (ImageView) view.findViewById(R.id.qr_cord);
//        qrcode_img.setOnClickListener(this);


        cityname = ShareUtil.getString("cityname");
        int cityid = ShareUtil.getInt("cityid");
        if (cityid != -1 && cityname != null) {
            this.cityId = cityid;
            tv_city.setText(cityname);
        }

        //首页列表
        newsListView = (PullandFreshView) view.findViewById(R.id.lv_frg01);
        //设置该下拉刷新控件的头部内容
        newsListView.setHeadView(R.layout.pulltorefresh_headview);
        //设置下拉控件的监听事件
        newsListView.setOnPullToRefreshListener(this);

        newsAdapter = new HouseNewsAdapter(getActivity());
        newsListView.setAdapter(newsAdapter);
        newsListView.getListView().setOnItemClickListener(this);
        newsListView.setOnScrollListener(this);

        //首页头部的viewpager
        homeVpView = new HomeVpView(getActivity());
        newsListView.addHeaderView(homeVpView);

        //接口回调
//        homeVpView.setOnLoadEndListener(new HomeVpView.OnLoadEndListener() {
//            @Override
//            public void isHaveData() {
//                if (homeVpView.getParent() != newsListView){
//                    newsListView.addHeaderView(homeVpView);
//                }
//
//            }
//
//            @Override
//            public void isNotHaveData() {
//                newsListView.getListView().removeHeaderView(homeVpView);
//            }
//        });

        //添加第二个headerview
//        View viewmid = LayoutInflater.from(getContext()).inflate(R.layout.frag1_header_mid, null);
//        newsListView.addHeaderView(viewmid);

        homeHeadView2 = new HomeHeadView2(getActivity());
        newsListView.addHeaderView(homeHeadView2);



    }

    //加载数据
    @Override
    protected void loadDatas() {
        //加载相应的城市数据
        newsUrl = String.format(Contants.URL_NEWS, newsCount, 0, 0, cityId);
        new DownUtil().setOnDownListener(this).downJSON(newsUrl);

        //新房
        mid_xinfang = (TextView) homeHeadView2.findViewById(R.id.middle_xingfang);
        mid_xinfang.setOnClickListener(this);

        //加载城市头部图片数据
        homeVpView.setCityid(getFragmentManager(), cityId);
    }

    @Override
    @OnClick({R.id.tv_selectcity, R.id.qr_cord})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qr_cord:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_ZXING_CODE);
                break;
            case R.id.tv_selectcity:
                startActivityForResult(new Intent(getActivity(), CityChoiceActivity.class), REQUEST_CODE);
                getActivity().overridePendingTransition(R.anim.anim_city_bottom_in, R.anim.amin_city_quiet);
                break;
            case R.id.middle_xingfang:
                Intent intent1 = new Intent(getActivity(), NewHouseActivity.class);
                intent1.putExtra("cityid",cityId);
                intent1.putExtra("cityname",cityname);
                startActivity(intent1);
                break;
        }
    }

//    @OnClick({R.id.tv_selectcity, R.id.qr_cord, R.id.middle_ershou, R.id.middle_zufang, R.id.middle_zixun, R.id.middle_youhui, R.id.middle_kaipan, R.id.middle_jisuan, R.id.middle_more})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.qr_cord:
//                Intent intent = new Intent(getActivity(), CaptureActivity.class);
//                startActivityForResult(intent, REQUEST_ZXING_CODE);
//                break;
//            case R.id.tv_selectcity:
//                startActivityForResult(new Intent(getActivity(), CityChoiceActivity.class), REQUEST_CODE);
//                getActivity().overridePendingTransition(R.anim.anim_city_bottom_in, R.anim.amin_city_quiet);
//                break;
//            case R.id.middle_xingfang:
//                startActivity(new Intent(getActivity(), NewHouseActivity.class));
//                break;
//            case R.id.middle_ershou:
//                break;
//            case R.id.middle_zufang:
//                break;
//            case R.id.middle_zixun:
//                break;
//            case R.id.middle_youhui:
//                break;
//            case R.id.middle_kaipan:
//                break;
//            case R.id.middle_jisuan:
//                break;
//            case R.id.middle_more:
//                break;
//        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ZXING_CODE && resultCode == getActivity().RESULT_OK) {
            String result = data.getStringExtra("result");
//            Log.d("print", "扫码结果：" + result);
        }

        if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE) {
            CityEntity cityentity = (CityEntity) data.getSerializableExtra("cityentity");
            //把当前的城市ID和城市名称保存进共享参数
            ShareUtil.putString("cityname", cityentity.getCityname());
            ShareUtil.putInt("cityid", cityentity.getCityid());

            tv_city.setText(cityentity.getCityname());
            cityId = cityentity.getCityid();

            loadDatas();//选择城市后重新加载数据
        }
    }

    @Override
    public Object paresJson(String json) {

        if (json != null) {
            return JSONUtil.getNewsByJson(json);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            datas = (List<HouseNewsEntity>) object;
            newsAdapter.setDatas(datas);
        }
    }


    //---------------下拉刷新的三个监听事件-------------------
    //正在下拉
    @Override
    public void pull(View headView, int movey) {
        //修改头部ImageView的图片，根据下拉的偏移量
        ImageView imageView = (ImageView) headView.findViewById(R.id.iv_pull);
        movey = movey / 3;

        String resName;
        if (movey < 9) {
            resName = "refresh_00";
        } else {
            resName = "refresh_0";
        }

        if (movey <= 0) {
            movey = 1;
        }
        if (movey > 48) {
            movey = 48;
        }
        int resid = getResources().getIdentifier(resName + movey, "drawable", getActivity().getPackageName());
        imageView.setImageResource(resid);

    }

    //正在刷新
    @Override
    public void refreshing(View headView) {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_scroll_footer);
        ImageView imageView = (ImageView) headView.findViewById(R.id.iv_pull);
        imageView.setImageResource(R.drawable.icon_black_progressbar);
        imageView.startAnimation(animation);
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            newsListView.refreshCompelet();
                            Log.d("print", "run: " + Thread.currentThread().getName());
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //刷新完成
    @Override
    public void refreshCompelet(View headView) {
        ImageView iv = (ImageView) headView.findViewById(R.id.iv_pull);
        iv.clearAnimation();//清除动画
    }


    //判断是否滚动到最下面
    private boolean isScrollBottom;

    //ListView滚动监听 的两个方法
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && isScrollBottom) {
            newsCount += 10;
            //重新加载数据
            loadDatas();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            isScrollBottom = true;
        } else {
            isScrollBottom = false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position != 1) {
            Intent intent = new Intent(getActivity(), HomeWebActivity.class);
            intent.putExtra("id", datas.get(position - 2).getId());
            intent.putExtra("commentcount", datas.get(position - 2).getCommentcount() + "");
//        Log.d("print", "onItemClick:---------> " + datas.get(position).getId());
            startActivity(intent);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
