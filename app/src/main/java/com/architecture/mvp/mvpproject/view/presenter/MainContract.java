package com.architecture.mvp.mvpproject.view.presenter;

import android.content.Context;

import com.architecture.mvp.mvpproject.adapter.contract.MainImageAdapterContract;
import com.architecture.mvp.mvpproject.data.MainImageData;
import com.architecture.mvp.mvpproject.data.source.MainImageRepository;

public interface MainContract {
    // View에서 사용되는 메소드
    interface View {
        void callOfRefreshToken(); // refreshToken일 경우 API를 재호출한다.

        void showErrorToast(String message); // 에러 토스트를 호출한다.

        void showToast(String message); // Toast를 호출한다.
    }

    // Presenter에서 사용되는 메소드
    interface Presenter {
        void attachView(View view); // Presenter를 사용할 View를 가져온다.

        void detatchView(); // View를 제거한다.

        void setAdapterView(MainImageAdapterContract.View adapterView); // Adapter에서 사용될 View 인터페이스를 셋팅한다.

        void setAdapterModel(MainImageAdapterContract.Model adapterModel); // Adapter에서 사용될 Model 인터페이스를 셋팅한다.

        void setMainImageRepository(MainImageRepository repository); // Model역할을 하는 Repository를 셋팅한다.

        void loadRemoteData(Context context, String data); // 해당하는 Request의 Response를 요청한다. (서버 데이터를 요청한다.)

        void loadLocalData(Context context); // 로컬 데이터를 요청한다.
    }
}
// Contract는 해당 인터페이스를 사용할 컴포넌트들의 인터페이스를 각각 만들어줘야 하므로
// 하나의 인터페이스 내부에 두개의 인터페이스를 생성한다.

// 예를들어, View와 Presenter의 Contract를 생성한다면
// interface View : View에서 구현할 메소드 모음
// interface Presenter : Presenter에서 구현할 메소드 모음
//                      (View 관련 : View의 메소드를 호출하기 위해(interface View) View를 가져온다.)
//                      (Adapter 관련 : Adapter의 메소드를 호출하기 위해(interface View, interface Model) Adapter를 가져온다.)
//                      (Model 관련 : DB/서버 등 데이터를 가져오기 위해 Repository를 셋팅한다.)
