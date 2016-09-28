package com.qf.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qf.entity.CityEntity;
import com.qf.entity.HeaderViewEntity;
import com.qf.entity.HouseNewsEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/2.
 */
public class JSONUtil {

    private static String[] str_array = {"hotcities","A","B","C","D","E","F","G","H","I","J","K",
            "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    /**
     * 解析城市列表JSON
     *
     * List<CityEntity>
     *     ->CityEntity: cityname:A type:0
     *     ->CityEntity: cityname:安阳 type:1
     *     ->CityEntity: cityname:安庆 type:1
     *     ->CityEntity: cityname:安山 type:1
     *     ->CityEntity: cityname:B type:0
     *     ->CityEntity: cityname:北京 type:1
     *     ->CityEntity: cityname:北平 type:1
     *     ->CityEntity: cityname:北海 type:1
     *     ...
     *
     *
     * @param json
     * @return
     */
    public static List<CityEntity> getCitysByJson(String json){
        List<CityEntity> cityEntities = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            jsonObject = jsonObject.getJSONObject("cities");
            for (int i = 0 ;i < str_array.length;i++){
                JSONArray jsonArray = jsonObject.optJSONArray(str_array[i]);
                if (jsonArray != null){
                    if (str_array[i].equals("hotcities")){
                        cityEntities.add(new CityEntity("热门城市",0));
                    }else {
                        cityEntities.add(new CityEntity(str_array[i],0));
                    }
                    TypeToken<List<CityEntity>> tt = new TypeToken<List<CityEntity>>(){};
                    List<CityEntity> cityEntityList = new Gson().fromJson(jsonArray.toString(),tt.getType());
                    cityEntities.addAll(cityEntityList);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityEntities;
    }
    /**
     * 根据json解析得到相应的数据实体集合
     * @param json
     * @return
     */
    public static List<HouseNewsEntity> getNewsByJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            //GSON
            TypeToken<List<HouseNewsEntity>> tt = new TypeToken<List<HouseNewsEntity>>(){};
            return new Gson().fromJson(jsonArray.toString(), tt.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据json解析首页vp中的实体数据集合
    public static List<HeaderViewEntity> getHeaderByJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("data");

            TypeToken<List<HeaderViewEntity>> tt = new TypeToken<List<HeaderViewEntity>>(){};
            return new Gson().fromJson(jsonArray.toString(),tt.getType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
