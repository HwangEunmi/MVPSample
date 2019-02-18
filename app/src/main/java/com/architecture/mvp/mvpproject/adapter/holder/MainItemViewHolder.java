package com.architecture.mvp.mvpproject.adapter.holder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.architecture.mvp.mvpproject.R;
import com.architecture.mvp.mvpproject.data.MainImageData;
import com.architecture.mvp.mvpproject.listener.OnItemClickListener;

// FrameLayout을 사용하는 이유 : http://blog.unsignedusb.com/2017/05/android-layout-measure-linearlayout-vs.html
public class MainItemViewHolder extends FrameLayout {

    private Context context;

    private TextView tvTitle;

    private ImageView ivPhoto;

    private OnItemClickListener listener;

    public MainItemViewHolder(Context context, OnItemClickListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
        init();
    }

    private void init() {
        inflate(context, R.layout.view_item_main, this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivPhoto = (ImageView) findViewById(R.id.iv_photo);
    }

    public void setData(MainImageData data, final int position) {
        tvTitle.setText(data.getTitle());
        ivPhoto.setImageDrawable(ContextCompat.getDrawable(context, data.getPhotoId()));
        ivPhoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
    }

}
