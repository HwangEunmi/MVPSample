package com.architecture.mvp.mvpproject.data.source;

import android.content.Context;

import java.lang.reflect.Type;

import retrofit2.Call;

// 로컬/서버 중 어떤 데이터를 불러올지 정의 (+ 필요시 메모리 캐시 포함)
public class MainImageRepository implements MainImageRepositoryContract {

    private static MainImageRepository INSTANCE;

    // 서버 데이터 관리하는 곳
    private MainImageRemoteDataSource serverSource;

    // 로컬 데이터 관리하는 곳
    private MainImageLocalDataSource localSource;

    public static MainImageRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MainImageRepository();
        }
        return INSTANCE;
    }

    private MainImageRepository() {
        serverSource = new MainImageRemoteDataSource();
        localSource = new MainImageLocalDataSource();
    }

    /**
     * 서버 데이터 요청할때 호출하는 메소드
     *
     * @param context
     * @param type
     * @param request
     * @param listener
     */
    @Override
    public void requestServerData(Context context, Type type, Call<String> request, final LoadDataCallback listener) {
        serverSource.requestServerData(context, type, request, new LoadDataCallback() {
            @Override
            public void onSuccess(Object result, boolean refreshToken) {
                if(listener != null) {
                    listener.onSuccess(result, refreshToken);
                }
            }

            @Override
            public void onFail(String code, String message, Object result) {
                if(listener != null) {
                    listener.onFail(code, message, result);
                }
            }
        });
    }

    /**
     * 로컬 데이터 요청할때 호출하는 메소드
     *
     * @param context
     * @param listener
     */
    @Override
    public void requestLocalData(Context context, LoadDataCallback listener) {
        // ex. DB에서 데이터 가져오기
    }
}
// Google Architecture Model 즉, 구글에서 설명하고 있는 Model의 정의는
// 1. Repository : 로컬/서버 중 어떤 데이터를 불러올지 정의하고, 메모리 캐시를 포함한다.
// 2. Remote data source : 서버에서 데이터를 받아온다.
// 3. Local data source : 로컬에서 데이터를 받아온다.
// -> 로컬/서버에서 받아오는 데이터는 Repository에서 캐시 처리할 수 있다.
// -> 필요에 따라서 캐시를 하고, 이를 Presenter에 다시 콜백을 해준다.

// 즉, Model의 구조가
// Repository안에 Remote data source와 Local data source가 들어있다.
// 그리고 Contract를 이용하여 Presenter에 다시 콜백을 해준다.
