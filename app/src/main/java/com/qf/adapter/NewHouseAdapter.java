package com.qf.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qf.entity.NewHouseEntity;
import com.qf.watchhouse.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
//public class NewHouseAdapter extends AbsBaseAdapter<NewHouseEntity.DataBean> {
//
//    public NewHouseAdapter(Context context) {
//        super(context, R.layout.item_newhouse);
//    }
//
//    @Override
//    public void bindDatas(AbsBaseAdapter<NewHouseEntity.DataBean>.ViewHodler viewHodler, NewHouseEntity.DataBean data) {
//        viewHodler
//                .setTextView(R.id.tv_title,data.getFname())
//                .setTextView(R.id.tv_fregion,data.getFregion())
//                .setTextView(R.id.tv_price,data.getFpricedisplaystr())
//                .setTextView(R.id.tv_address,data.getFaddress())
//                .setImageView(R.id.iv_pic,data.getFcover());
//    }
//
//
//}

public class NewHouseAdapter extends BaseAdapter{

    private Context context;
    private List<NewHouseEntity.DataBean> datas;
    private int[] colors = {Color.RED, Color.BLUE, Color.GREEN};

    public NewHouseAdapter(Context context) {
        this.context = context;
        this.datas = new ArrayList<>();
    }

    public void setDatas(List<NewHouseEntity.DataBean> datas){
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    public void addDatas(List<NewHouseEntity.DataBean> data){
        this.datas.addAll(data);
        this.notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView != null){
            holder = (ViewHolder) convertView.getTag();
        }else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_newhouse,null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_pic);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_fregion = (TextView) convertView.findViewById(R.id.tv_fregion);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            holder.ll = (LinearLayout) convertView.findViewById(R.id.ll_bookmark);
            convertView.setTag(holder);
        }

        //设置数据
        holder.tv_title.setText(datas.get(position).getFname());
        holder.tv_fregion.setText(datas.get(position).getFregion());
        holder.tv_price.setText(datas.get(position).getFpricedisplaystr());
        holder.tv_address.setText(datas.get(position).getFaddress());


        //添加标签
        holder.ll.removeAllViews();//移除线性布局中的所有控件
        List<NewHouseEntity.DataBean.BookmarkBean> dataList = datas.get(position).getBookmark();
        for(int i = 0; i < dataList.size(); i++){
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = 10;
            TextView tv = new TextView(context);
            tv.setText(dataList.get(i).getTag());
            tv.setBackgroundColor(colors[i]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            tv.setTextColor(Color.WHITE);
            tv.setPadding(10, 2, 10, 2);
            holder.ll.addView(tv, lp);
        }
        Glide.with(context)
                .load(datas.get(position).getFcover())
                .crossFade(500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.list_default_image)
                .thumbnail(0.1f)
                .into(holder.iv);

        return convertView;
    }


    class ViewHolder{
        ImageView iv;
        TextView tv_title,tv_fregion,tv_address,tv_price;
        LinearLayout ll;
    }

}
