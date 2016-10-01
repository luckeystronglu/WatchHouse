package com.qf.utils;

/**
 * Created by Administrator on 2016/8/2.
 */
public interface Contants {
    //城市列表接口
    String URL_SELECT_CITY = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&act=kftcitylistnew&channel=71&devid=866500021200250&appname=QQHouse&mod=appkft";

    //城市首页WebView的接口
    String URL_WEBVIEW = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=homepage&channel=71&cityid=%d";

    //城市首页新闻列表接口
    String URL_NEWS = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&reqnum=%d&pageflag=%d&act=newslist&channel=71&buttonmore=%d&cityid=%d";

    /**
     * 首页WebView内容
     */
    String FIRST_PAGE_WEBVIEW = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&devid=866500021200250&appname=QQHouse&mod=appkft&act=newsdetail&channel=71&newsid=%s";

    //新房
    String HOME_NEWHOUSE = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&rn=10&order=0&searchtype=normal&devid=866500021200250&page=%d&appname=QQHouse&mod=appkft&act=searchhouse&channel=71&cityid=%s";

    //新房详情
    String NEWHOUSE_DETAIL = "http://ikft.house.qq.com/index.php?guid=866500021200250&devua=appkft_1080_1920_XiaomiMI4LTE_1.8.3_Android19&hid=%d&devid=866500021200250&appname=QQHouse&mod=appkft&act=houseinfo&channel=71";
}

