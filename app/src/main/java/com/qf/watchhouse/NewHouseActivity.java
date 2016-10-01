package com.qf.watchhouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qf.adapter.NewHouseAdapter;
import com.qf.entity.NewHouseEntity;
import com.qf.utils.Contants;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2016/9/27.
 */
public class NewHouseActivity extends BaseActivity implements DownUtil.OnDownListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private NewHouseAdapter houseAdapter;
    private List<NewHouseEntity.DataBean> datas;

//    private Spinner spinner;
//    private ArrayAdapter<String> arrayAdapter;
//    private String[][] citydatas = {{"1","北京"}, {"2", "上海"}, {"3", "广州"},{"4", "深圳"},{"5", "成都"}};
    /**
     * ---分页----
     */
    private int page = 1;
    private int cityid = 1;
    private String cityname = "北京";

    private boolean isBottom = false;
    private boolean isLoad = false;

    private int type = 0;//0表示分页 1表示更换城市

    //ActionBar控件
//    private ImageView backimg,findimg;

    @Override
    public int getContentViewId() {
        return R.layout.activity_newhouse;
    }

    @Override
    protected void init() {
        //初始化spinner
//        spinner = (Spinner) findViewById(R.id.sp);
//        //加载spinner的数据
//        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
//        for (int i = 0; i < citydatas.length; i++) {
//            arrayAdapter.add(citydatas[i][1]);
//        }
//        spinner.setAdapter(arrayAdapter);
//        spinner.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        cityid = intent.getIntExtra("cityid", 1);
        cityname = intent.getStringExtra("cityname");

        listView = (ListView) findViewById(R.id.lv);

        houseAdapter = new NewHouseAdapter(this);
        listView.setAdapter(houseAdapter);

        listView.setOnScrollListener(this);
        listView.setOnItemClickListener(this);
    }

    /**
     * 加载数据
     */
    protected void loadDatas() {
        String url = String.format(Contants.HOME_NEWHOUSE, page, cityid);
        new DownUtil().setOnDownListener(this).downJSON(url);

    }


//    /**
//     * Spinner的监听方法
//     */
//    @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//
//        cityid = Integer.parseInt(citydatas[position][0]);
//        type = 1;
//        loadDatas();
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                if (isBottom && !isLoad) {
                    isLoad = true;
                    page++;
                    type = 0;
                    loadDatas();
                }
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            isBottom = true;
        } else {
            isBottom = false;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewHouseEntity.DataBean houseEntity = (NewHouseEntity.DataBean) houseAdapter.getItem(position);
        String name = houseEntity.getFname();
        Intent intent = new Intent(this, HomeWebActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }

    @Override
    public Object paresJson(String json) {
        if (json != null) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray ja = jsonObject.getJSONArray("data");

                //GSON
                TypeToken<List<NewHouseEntity.DataBean>> tt = new TypeToken<List<NewHouseEntity.DataBean>>() {
                };
                return new Gson().fromJson(ja.toString(), tt.getType());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            datas = (List<NewHouseEntity.DataBean>) object;
            if (type == 0) {
                //分页
                houseAdapter.addDatas(datas);
            } else {
                //更新城市
                houseAdapter.setDatas(datas);
            }
            //表示数据加载完成
            isLoad = false;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.web_back, R.id.img_xingfang_find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.web_back:
                finish();
                break;
            case R.id.img_xingfang_find:
                break;
        }
    }
}
