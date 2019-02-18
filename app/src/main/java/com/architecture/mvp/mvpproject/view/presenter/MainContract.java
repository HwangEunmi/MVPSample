package com.architecture.mvp.mvpproject.view.presenter;

import android.content.Context;

import com.architecture.mvp.mvpproject.adapter.contract.MainImageAdapterContract;
import com.architecture.mvp.mvpproject.util.DBManager;

public interface MainContract {
    // View에서 사용되는 메소드
    interface View {
        void showToast(String message); // Toast를 호출한다.
    }

    // Presenter에서 사용되는 메소드
    interface Presenter {
        void attachView(View view); // Presenter를 사용할 View를 가져온다.

        void detatchView(); // View를 제거한다.

        void setAdapterView(MainImageAdapterContract.View adapterView); // Adapter에서 사용될 View 인터페이스를 셋팅한다.

        void setAdapterModel(MainImageAdapterContract.Model adapterModel); // Adapter에서 사용될 Model 인터페이스를 셋팅한다.

        void setDBManager(DBManager manager); // View를 갱신할 데이터 객체를 셋팅한다. (ex. DB, 서버 등..)

        void loadItems(Context context, boolean isClear);
    }
}
