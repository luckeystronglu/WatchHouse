package com.qf.watchhouse;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;

import com.qf.adapter.CityAdapter;
import com.qf.db.CityNameSQLiteOpenHelper;
import com.qf.entity.CityEntity;
import com.qf.fragment.HomeFragment;
import com.qf.utils.Contants;
import com.qf.utils.JSONUtil;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;
import com.qfkf.widget.citylabel.LableCompareView;

import java.util.List;

import de.halfbit.pinnedsection.PinnedSectionListView;

/**
 * Created by lenovo on 2016/9/25.
 */
public class CityChoiceActivity extends BaseActivity implements AdapterView.OnItemClickListener,DownUtil.OnDownListener, LableCompareView.OnLabelSelectorListener {
    private PinnedSectionListView listView_citys;
    private CityAdapter cityAdapter;

    private LableCompareView lableCompareView;
//    private SlidBarView slideView;
//    private LabelView libelView;

    //数据库相关
    private CityNameSQLiteOpenHelper helper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    public int getContentViewId() {
        helper = new CityNameSQLiteOpenHelper(this);
        sqLiteDatabase = helper.getReadableDatabase();
        return R.layout.activity_citychoice;
    }


    //初始化

    @Override
    protected void init() {
        listView_citys = findViewByIds(R.id.lv_city);
        cityAdapter = new CityAdapter(this);
        listView_citys.setAdapter(cityAdapter);
        listView_citys.setOnItemClickListener(this);

//        slideView = findViewByIds(R.id.slideview);
//        libelView = findViewByIds(R.id.labelview);
//        slideView.setOnTouchSlidBarListener(this);
        lableCompareView = findViewByIds(R.id.label_compare_view);
        lableCompareView.setOnLabelSelectorListener(this);
    }

    @Override
    protected void loadDatas() {
        new DownUtil().setOnDownListener(this).downJSON(Contants.URL_SELECT_CITY);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CityEntity cityEntity = (CityEntity) cityAdapter.getItem(position);
        Intent intent = getIntent();
        intent.putExtra("cityentity",cityEntity);
        setResult(HomeFragment.RESULT_CODE,intent);
        finish();

    }


    @Override
    public Object paresJson(String json) {
        if (json != null){
            return JSONUtil.getCitysByJson(json);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            List<CityEntity> list = (List<CityEntity>) object;
            cityAdapter.setDatas(list);
        }
    }

    @Override
    public void showLabel(String word, int position) {
        int index = cityAdapter.queryLabelIndex(word);
        if (index != -1) {
            listView_citys.setSelection(index);
//            listView_citys.smoothScrollToPositionFromTop(index,0);//滑到坐标位置
        }
    }
}
