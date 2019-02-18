package com.architecture.mvp.mvpproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.architecture.mvp.mvpproject.R;
import com.architecture.mvp.mvpproject.adapter.contract.MainImageAdapterContract;
import com.architecture.mvp.mvpproject.adapter.holder.MainItemViewHolder;
import com.architecture.mvp.mvpproject.data.MainImageData;
import com.architecture.mvp.mvpproject.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainImageAdapter extends BaseAdapter implements MainImageAdapterContract.View, MainImageAdapterContract.Model {

    private Context context;

    private List<MainImageData> list;

    // 클릭 리스너 (이미지뷰)
    private OnItemClickListener listener;

    public MainImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public MainImageData getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MainImageData data = list.get(position);
        MainItemViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_item_main, parent, false);
            viewHolder = new MainItemViewHolder(context, listener);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MainItemViewHolder) convertView.getTag();
        }

        // Set Data ...
        viewHolder.setData(data, position);
        return convertView;
    }


    /**
     * ViewHolder에 넘겨줄 클릭 리스너 셋팅하기 (Presenter에서 구현한)
     *
     * @param listener
     */
    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 데이터를 갱신한다.
     */
    @Override
    public void notifyData() {
        notifyDataSetChanged();
    }

    /**
     * 데이터 추가하기
     *
     * @param list
     */
    @Override
    public void addList(List<MainImageData> list) {
        this.list = list;
    }

    @Override
    public void addItems(MainImageData data) {
        if(list == null) {
            list = new ArrayList<>();
        }
        list.add(data);
    }

    /**
     * 데이터 지우기
     */
    @Override
    public void clearItems() {
        if (list != null) {
            list.clear();
            list = null;
        }
    }
}
