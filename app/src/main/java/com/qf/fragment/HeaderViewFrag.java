package com.qf.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qf.entity.HeaderViewEntity;
import com.qf.watchhouse.R;
import com.qfkf.base.BaseFragment;

/**
 * Created by Administrator on 2016/8/4.
 */
public class HeaderViewFrag extends BaseFragment {
    private ImageView header_iv;
    private TextView header_tv;

    //静态工厂方法
    public static Fragment getInstance(HeaderViewEntity headerViewEntity){
        HeaderViewFrag headerViewFrag = new HeaderViewFrag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("headerview",headerViewEntity);
        headerViewFrag.setArguments(bundle);
        return headerViewFrag;
    }

    @Override
    protected int getContentView() {
        return R.layout.frag_headerview;
    }

    @Override
    protected void init(View view) {
        header_iv = (ImageView) view.findViewById(R.id.img_header_vp);
        header_tv = (TextView) view.findViewById(R.id.header_img_tv);
    }

    @Override
    protected void getBundle(Bundle bundle) {
        HeaderViewEntity headerViewEntity = (HeaderViewEntity) bundle.getSerializable("headerview");
        header_tv.setText(headerViewEntity.getTitle());
        Glide.with(getActivity())
                .load(headerViewEntity.getPicurl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .placeholder(R.drawable.list_default_image)
                .crossFade(500)
                .into(header_iv);

    }
}
