package com.qf.adapter;

import android.content.Context;

import com.qf.entity.HouseNewsEntity;
import com.qf.watchhouse.R;
import com.qfkf.base.AbsMoreItemBaseAdapter;


/**
 * Created by Administrator on 2016/8/4.
 */
public class HouseNewsAdapter extends AbsMoreItemBaseAdapter<HouseNewsEntity> {
    public HouseNewsAdapter(Context context) {
        super(context, R.layout.item_news_pic,R.layout.item_news_pics);
    }

    @Override
    public void bindDatas(ViewHodler viewHodler, HouseNewsEntity data, int position) {
        if (getItemViewType(position) == 0){
            viewHodler
                    .setTextView(R.id.tv_news_title,data.getTitle())
                    .setTextView(R.id.tv_news_summy,data.getSummary())
                    .setTextView(R.id.tv_news_comment,data.getCommentcount()+"")
                    .setImageView(R.id.iv_news_pic,data.getThumbnail());
        }else {
            viewHodler
                    .setTextView(R.id.tv_news_title,data.getTitle())
                    .setTextView(R.id.tv_news_comment,data.getCommentcount()+"")
                    .setImageView(R.id.iv_news_pics,data.getGroupthumbnail());

        }
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }
}
