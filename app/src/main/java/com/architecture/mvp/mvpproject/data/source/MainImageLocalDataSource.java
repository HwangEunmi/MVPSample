package com.architecture.mvp.mvpproject.data.source;

import android.content.Context;

import java.lang.reflect.Type;

import retrofit2.Call;

// Local data source : 로컬에서 데이터를 받아온다. (DB 등)
public class MainImageLocalDataSource implements MainImageRepositoryContract{
    // TODO : 이렇게 서버/로컬 메소드 두개 나누는것보다 공통으로 쓸 수 있는 좋은 방법 있는지 생각해보기

    @Override
    public void requestServerData(Context context, Type type, Call<String> request, LoadDataCallback listener) {

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
