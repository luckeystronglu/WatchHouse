package com.qf.watchhouse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.qf.entity.Home_lv_webitem_Entity;
import com.qf.utils.Contants;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2016/9/25.
 */
public class NewHouseWebActivity extends BaseActivity implements DownUtil.OnDownListener {

    @Bind(R.id.web_back)
    ImageView webBack;
    @Bind(R.id.web_share)
    ImageView webShare;

    private WebView wb;
    private RadioButton publish,pinglun;

    private WebSettings webSettings;
    private String url;
    private String cityid,commentcount;//详情新闻id、评论数量
    private String wap_content;

    @Override
    public int getContentViewId() {
        return R.layout.home_web_details;
    }

//    @Override
//    public boolean isOpenStatus() {
//        return false;
//    }

    @Override
    protected void init() {

        publish = findViewByIds(R.id.details_rb_publicping);
        pinglun = findViewByIds(R.id.details_rb_lookpinglun);

        Intent intent = getIntent();
        cityid = intent.getStringExtra("id");
        Log.d("print", "cityid: "+cityid);
        commentcount = intent.getStringExtra("commentcount");
        if (commentcount != null){
            pinglun.setText(commentcount);
        }

        wb = (WebView) findViewById(R.id.wb);
        webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");//设置字符集
        webSettings.setLoadWithOverviewMode(true);//设置网页缩放至屏幕大小
        webSettings.setUseWideViewPort(true);
        wb.setWebViewClient(new WebViewClient(){


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                wb.loadUrl(url);
                return true;
            }

        });
    }


    @Override
    protected void loadDatas() {
        url = String.format(Contants.FIRST_PAGE_WEBVIEW, cityid);
//        Log.d("print", "url: " + url);
        new DownUtil().setOnDownListener(this).downJSON(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //按下返回键
            if (wb.canGoBack()){
                //返回webview
                wb.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.details_rb_publicping, R.id.details_rb_lookpinglun,R.id.web_back, R.id.web_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.details_rb_publicping:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.details_rb_lookpinglun:

                break;
            case R.id.web_back:
                this.finish();
                break;
            case R.id.web_share:
                break;

        }
    }

    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return new Gson().fromJson(json, Home_lv_webitem_Entity.class);
        }

        return null;
    }

    @Override
    public void downSucc(Object object) {

        if (object != null) {
            Home_lv_webitem_Entity coEntity = (Home_lv_webitem_Entity) object;
            wap_content = coEntity.getUrl();
//            Log.d("print", "downSucc: "+ coEntity.getUrl());
            wb.loadUrl(wap_content);
        }

//        wb.loadData(wap_content, "text/html;charset=utf-8", null);

    }

}
