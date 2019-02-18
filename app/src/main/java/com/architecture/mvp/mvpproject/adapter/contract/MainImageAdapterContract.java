package com.architecture.mvp.mvpproject.adapter.contract;

import com.architecture.mvp.mvpproject.data.MainImageData;
import com.architecture.mvp.mvpproject.listener.OnItemClickListener;

import java.util.List;

// Adapter에 대한 메소드
public interface MainImageAdapterContract {
    // View에서 사용되는 메소드
    public interface View {
        void setOnItemClickListener(OnItemClickListener listener); // ViewHolder에 넘겨줄 클릭 리스너를 셋팅한다. (Presenter에서 구현한)

        void notifyData(); // 데이터를 갱신한다.
    }

    // Model에서 사용되는 메소드
    public interface Model {
        void addList(List<MainImageData> list);

        void addItems(MainImageData data); // 데이터를 추가한다.

        void clearItems(); // 데이터를 지운다.

        MainImageData getItem(int position); // 해당 position의 데이터를 리턴한다.
    }
}
// Adapter 인터페이스와 View 인터페이스를 분리한 이유 :
// 일반적으로 구현하면 Presenter에서 Adapter의 데이터를 저장하고, 불러오는 모든 부분에 항상 View가 함께해야 한다.
// -> Adapter에 관한 처리를 모두 View에서 대신 받아서 전달해야 하므로 복잡도가 높아지고 귀찮아진다.

// 즉,
// View -> Presenter -> Model -> Presenter -> View -> Adapter 에서
// View -> Presenter -> Model -> Presenter -> Adapter 로 흐름이 변경된다.
