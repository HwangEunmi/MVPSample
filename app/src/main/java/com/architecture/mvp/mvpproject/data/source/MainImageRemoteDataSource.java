package com.architecture.mvp.mvpproject.data.source;

import android.content.Context;

import java.lang.reflect.Type;

import retrofit2.Call;

// Remote data source : 서버에서 데이터를 받아온다.
public class MainImageRemoteDataSource implements MainImageRepositoryContract {
    // TODO : 이렇게 서버/로컬 메소드 두개 나누는것보다 공통으로 쓸 수 있는 좋은 방법 있는지 생각해보기

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
        // Retrofit-Sample 코드 참고 (서버통신후 결과받는곳)
        // https://github.com/HwangEunmi/Retrofit-Sample
        MyApplication.getInstance().getApiManager().getApiResponse(type, request, new LoadDataCallback() {
            @Override
            public void onSuccess(Object result, boolean refreshToken) {
                if (listener != null) {
                    listener.onSuccess(result, refreshToken);
                }
            }

            @Override
            public void onFail(String code, String message, Object result) {
                if (listener != null) {
                    listener.onFail(code, message, result);
                }
            }
        });
    }

    @Override
    public void requestLocalData(Context context, LoadDataCallback listener) {

    }

}
