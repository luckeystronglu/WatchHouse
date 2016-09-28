package com.qf.adapter;

import android.content.Context;

import com.qf.entity.CityEntity;
import com.qf.watchhouse.R;
import com.qfkf.base.AbsMoreItemBaseAdapter;

import de.halfbit.pinnedsection.PinnedSectionListView;

/**
 * Created by Administrator on 2016/8/2.
 */
public class CityAdapter extends AbsMoreItemBaseAdapter<CityEntity> implements PinnedSectionListView.PinnedSectionListAdapter {
    public CityAdapter(Context context) {
        super(context, R.layout.item_label,R.layout.item_city);
    }


    @Override
    public void bindDatas(ViewHodler viewHodler, CityEntity data, int position) {
        if (data.getType() == 0){
            viewHodler.setTextView(R.id.tv_label,data.getCityname());
        }else {
            viewHodler.setTextView(R.id.tv_city,data.getCityname());
        }
    }

    @Override
    public boolean isEnabled(int position) {
        return datas.get(position).getType() == 1;
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    /**
     * 查询label标签在数据源中的下标位置
     * @return
     */
    public int queryLabelIndex(String lable){
        for (int i = 0;i < datas.size();i++){
            if (datas.get(i).getCityname().equals(lable)){
                return i;
            }
        }
        return -1;
    }


    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType == 0;
    }
}
