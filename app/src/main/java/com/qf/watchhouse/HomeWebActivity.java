package com.qf.watchhouse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.qf.utils.Contants;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.DownUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2016/9/25.
 */
public class HomeWebActivity extends BaseActivity implements DownUtil.OnDownListener {

    @Bind(R.id.web_back)
    ImageView webBack;
    @Bind(R.id.web_share)
    ImageView webShare;
//    @Bind(R.id.details_title)
//    TextView detailsTitle;

    //    @Bind(R.id.wb)
//    WebView wb;
    private WebView wb;


    @Bind(R.id.details_rb_publicping)
    RadioButton detailsRbPublicping;
    @Bind(R.id.details_rb_lookpinglun)
    RadioButton detailsRbLookpinglun;
//    @Bind(R.id.rg_pinglun)
//    RadioGroup rgPinglun;

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
        Intent intent = getIntent();
        wb = (WebView) findViewById(R.id.wb);
        webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");//设置字符集
        webSettings.setLoadWithOverviewMode(true);//设置网页缩放至屏幕大小
        webSettings.setUseWideViewPort(true);

        cityid = intent.getStringExtra("id");
        commentcount = intent.getStringExtra("commentcount");
        Log.d("print", "commentcount: "+commentcount);
        url = String.format(Contants.FIRST_PAGE_WEBVIEW, cityid);
        new DownUtil().setOnDownListener(this).downJSON(url);

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
                if (commentcount != null){
                    detailsRbLookpinglun.setText(commentcount);
                }

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
        try {

            Log.d("print", "paresJson--->:" + new JSONObject(json).get("url"));
            return new JSONObject(json).get("url");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        wap_content = (String) object;
//        wb.loadData(wap_content, "text/html;charset=utf-8", null);
        wb.loadUrl(wap_content);
    }

}
