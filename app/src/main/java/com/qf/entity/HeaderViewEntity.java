package com.qf.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/4.
 */
public class HeaderViewEntity implements Serializable{

    /**
     * type : 3
     * picurl : http://p.qpic.cn/estate/0/021ea4a42f1bd93a3f9fe8ba99b7b1f1.jpg/0
     * title : 百米楼间距 尊享人生尺度
     * houseid : 168694
     */

    private String type;
    private String picurl;
    private String title;
    private String houseid;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHouseid() {
        return houseid;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }
}
