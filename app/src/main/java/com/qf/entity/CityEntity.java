package com.qf.entity;

import java.io.Serializable;

/**
 *
 * 城市实体类
 *
 * List<CityEntity>
 *     CityEntity -> cityname:A type:0
 *     CityEntity -> cityname:鞍山 type:1
 *     CityEntity -> cityname:安阳 type:1
 *     CityEntity -> cityname:B type:0
 *     CityEntity -> cityname:北京 type:1
 *     CityEntity -> cityname:北海 type:1
 *
 * Created by Administrator on 2016/8/2.
 */
public class CityEntity implements Serializable {
    private int cityid;
    private String cityname;
    private int type = 1; //当前类型

    public CityEntity(String cityname,int type) {
        this.cityname = cityname;
        this.type = type;
    }

    public CityEntity() {
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
