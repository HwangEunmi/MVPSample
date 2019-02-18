package com.architecture.mvp.mvpproject.view.presenter;

import android.content.Context;

import com.architecture.mvp.mvpproject.R;
import com.architecture.mvp.mvpproject.adapter.contract.MainImageAdapterContract;
import com.architecture.mvp.mvpproject.data.MainImageData;
import com.architecture.mvp.mvpproject.listener.OnItemClickListener;
import com.architecture.mvp.mvpproject.util.DBManager;

// View에서 필요한 Presenter 생성
public class MainPresenter implements MainContract.Presenter, OnItemClickListener{

    // Presenter를 사용할 뷰
    private MainContract.View view;

    // 직접 Adapter에 접근할 수 있는 인터페이스 (이를 이용하면 adapter 사용할때 View를 통해서 접근하지 않아도 됨)
    private MainImageAdapterContract.View adapterView;

    private MainImageAdapterContract.Model adapterModel;

    // 뷰 갱신에 사용되는 데이터 객체 (ex. DB, 서버 등...)
    private DBManager manager;

    /**
     * Presenter를 사용할 View를 가져온다.
     *
     * @param view
     */
    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    /**
     * View를 제거한다.
     */
    @Override
    public void detatchView() {
        this.view = null;
    }

    /**
     * Adapter에서 사용될 View 인터페이스를 셋팅한다.
     *
     * @param adapterView
     */
    @Override
    public void setAdapterView(MainImageAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(this);
    }

    /**
     * Adapter에서 사용될 Model 인터페이스를 셋팅한다.
     *
     * @param adapterModel
     */
    @Override
    public void setAdapterModel(MainImageAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    /**
     * View를 갱신할 데이터 객체를 셋팅한다. (ex. DB, 서버 등..)
     *
     * @param manager
     */
    @Override
    public void setDBManager(DBManager manager) {
        this.manager = manager;
    }

    /**
     * DB에서 데이터를 가져오거나 서버통신해서 값을 가져온 후 View를 갱신한다.
     *
     * @param context
     * @param isClear
     */
    @Override
    public void loadItems(Context context, boolean isClear) {
        // TODO : DB나 서버등에서 데이터 가져오기 (또는 데이터 가져올 수 있는건 뭐든)
        // manager.~~~
        adapterModel.addItems(new MainImageData("데이터", R.mipmap.ic_launcher_round));
        adapterView.notifyData();
    }

    /**
     * ViewHolder에 연결한 클릭 리스너
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        view.showToast(adapterModel.getItem(position).getTitle());
    }
}
