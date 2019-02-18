package com.architecture.mvp.mvpproject.util;

// DB 매니저
public class DBManager {
    private static DBManager manager;

    public static DBManager getInstance() {
        if(manager == null) {
            manager = new DBManager();
        }
        return manager;
    }
}
