package com.architecture.mvp.mvpproject.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.architecture.mvp.mvpproject.R;
import com.architecture.mvp.mvpproject.adapter.MainImageAdapter;
import com.architecture.mvp.mvpproject.data.MainImageData;
import com.architecture.mvp.mvpproject.data.source.MainImageRepository;
import com.architecture.mvp.mvpproject.view.presenter.MainContract;
import com.architecture.mvp.mvpproject.view.presenter.MainPresenter;

// View의 이벤트만 관여, 이벤트에 대한 처리는 Presenter가 처리
public class MainActivity extends AppCompatActivity implements MainContract.View {

    // View에서 전달된 이벤트에 대한 처리를 하는 Presenter (View와 무관한 일만 처리한다.)
    private MainPresenter presenter;

    private MainImageAdapter adapter;

    // 리스트뷰
    private ListView lvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 리스트뷰 어댑터 셋팅
        lvView = findViewById(R.id.lv_view);
        adapter = new MainImageAdapter(this);
        lvView.setAdapter(adapter);

        // 1. Presenter 생성
        presenter = new MainPresenter();
        // 2. 이벤트를 전해줄 View 셋팅
        presenter.attachView(this);
        // 3. Adapter에서 사용될 Model 인터페이스를 셋팅.
        presenter.setAdapterModel(adapter);
        // 4. Adapter에서 사용될 View 인터페이스를 셋팅.
        presenter.setAdapterView(adapter);
        // 5. View를 갱신할 데이터 저장소 셋팅 (로컬/서버)
        presenter.setMainImageRepository(MainImageRepository.getInstance());
        // 6. View 갱신
        requestApiData();
    }

    @Override
    protected void onDestroy() {
        presenter.detatchView();
        super.onDestroy();
    }

    /**
     * 서버 API를 호출한다.
     */
    private void requestApiData() {
        String data = MyApplication.getInstance().getGson().toJson(new ActivityRecordInputModel.RQ("exerciseId"));
        presenter.loadRemoteData(this, data);
    }

    /**
     * refreshToken일 경우 API를 재호출한다.
     */
    @Override
    public void callOfRefreshToken() {
        requestApiData();
    }

    /**
     * 에러 토스트를 호출한다.
     *
     * @param message
     */
    @Override
    public void showErrorToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast를 호출한다.
     *
     * @param message
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
// Google Architecture에서 정의하는 View와 Presenter의 구조는 (약간 수정된)
// View 내부에 View가 사용할 Presenter를 넣고, Contract로 콜백을 주고 받는다.
// 만약, Activity 내부에 Fragment가 있을 경우, Fragment도 View이다. 즉,
// Activity 내부에 Fragment와 Presenter가 들어있는 형태이다.

// 만약, Adapter를 사용하는 경우, 보통 View 내부에서 Adapter를 생성하므로
// Adapter는 View를 통해서 Presenter와 상호작용해야 하는데, 번거로우므로
// Adapter용 Contract를 따로 만든 후, Presenter와 직접 상호작용하게 한다.


// 참고 URL
// https://thdev.tech/androiddev/2016/12/29/Android-MVP-Four-Three/
// https://github.com/taehwandev/AndroidMVPSample/blob/master/app_java/src/main/java/tech/thdev/android_mvp_sample/view/main/MainActivity.java
// https://github.com/dongja94/SampleList_DD2/blob/master/app/src/main/java/com/begentgroup/samplelist/PersonAdapter.java
