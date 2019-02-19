package com.architecture.mvp.mvpproject.view.presenter;

import android.content.Context;

import com.architecture.mvp.mvpproject.adapter.contract.MainImageAdapterContract;
import com.architecture.mvp.mvpproject.data.MainImageData;
import com.architecture.mvp.mvpproject.data.source.MainImageRepository;
import com.architecture.mvp.mvpproject.data.source.MainImageRepositoryContract;
import com.architecture.mvp.mvpproject.listener.OnItemClickListener;

import okhttp3.MediaType;
import okhttp3.RequestBody;

// View에서 필요한 Presenter 생성
public class MainPresenter implements MainContract.Presenter, OnItemClickListener {

    // Presenter를 사용할 뷰
    private MainContract.View view;

    // 직접 Adapter에 접근할 수 있는 인터페이스 (이를 이용하면 adapter 사용할때 View를 통해서 접근하지 않아도 됨)
    private MainImageAdapterContract.View adapterView;

    private MainImageAdapterContract.Model adapterModel;

    // Model역할을 하는 Repository를 셋팅한다.
    private MainImageRepository repository;

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
     * Model역할을 하는 Repository를 셋팅한다.
     *
     * @param repository
     */
    @Override
    public void setMainImageRepository(MainImageRepository repository) {
        this.repository = repository;
    }

    /**
     * 해당하는 Request의 Response를 요청한다. (서버 데이터를 요청한다.)
     *
     * @param context
     * @param data
     */
    @Override
    public void loadRemoteData(Context context, String data) {
        // Retrofit-Sample 코드 참고
        // https://github.com/HwangEunmi/Retrofit-Sample
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), data);
        repository.requestServerData(context, ActivityRecordInputModel.RS.class,
                MyApplication.getInstance().getApiManager().getApiService(context).inputExerciseRecord(body),
                new MainImageRepositoryContract.LoadDataCallback() {
                    @Override
                    public void onSuccess(Object result, boolean refreshToken) {
                        if (refreshToken) {
                            view.callOfRefreshToken();
                        } else {
                            adapterModel.addItems((MainImageData) result);
                            adapterView.notifyData();
                        }
                    }

                    @Override
                    public void onFail(String code, String message, Object result) {
                        view.showErrorToast(message);
                    }
                });
    }

    /**
     * 로컬 데이터를 요청해서 뷰를 갱신한다. (DB 데이터를 가져온다.)
     *
     * @param context
     */
    @Override
    public void loadLocalData(Context context) {
        repository.requestLocalData(context, new MainImageRepositoryContract.LoadDataCallback() {
            @Override
            public void onSuccess(Object result, boolean refreshToken) {
                adapterModel.addItems((MainImageData) result);
                adapterView.notifyData();
            }

            @Override
            public void onFail(String code, String message, Object result) {
                view.showErrorToast(message);
            }
        });
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

// Presenter는 자신을 호출한 View와 Model, Adapter를 이어주는 역할을 한다.
// 먼저, View에서 Presenter를 생성한 후, 자기 자신의 View를 넘겨준다. -> 이를 통해서 View가 구현한 메소드를 호출할 수 있다.
// 만약 Adapter를 사용할 경우, Adapter의 Contract(View/Model)를 Presenter에 넘겨준다. -> 이를 통해서 Adapter가 구현한 메소드를 호출할 수 있다.
// (Presenter -> View -> Adapter가 아닌 Presenter -> Adapter 즉, View를 거치지않고 Presenter와 Adapter가 직접 상호작용한다.)
// 마지막으로, Presenter 내부에 Model Repository를 셋팅해준다.