package com.architecture.mvp.mvpproject.data.source;

import android.content.Context;

import java.lang.reflect.Type;

import retrofit2.Call;

// DB 또는 서버등에서 데이터를 가져올때 사용하는 인터페이스
public interface MainImageRepositoryContract {
    // 데이터를 가져오는 콜백 리스너
    interface LoadDataCallback {
        void onSuccess(Object result, boolean refreshToken); // 성공할 경우

        void onFail(String code, String message, Object result); // 실패할 경우
    }

    void requestServerData(Context context, Type type, Call<String> request, LoadDataCallback listener); // 서버 데이터 요청할때 호출하는 메소드

    void requestLocalData(Context context, LoadDataCallback listener); // 로컬 데이터 요청할때 호출하는 메소드

}
