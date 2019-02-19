package com.architecture.mvp.mvpproject.util;

// DB 매니저
public class DBManager {
    private static DBManager INSTANCE;

    public static DBManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DBManager();
        }
        return INSTANCE;
    }
}
