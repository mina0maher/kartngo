package com.mina.kartngo.data.remote.auth;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "app_prefs";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_SESSION_ID = "sessionId";

    private final SharedPreferences prefs;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    public void saveToken(String token) {
        prefs.edit()
                .putString(KEY_TOKEN, token)
                .apply();
    }
    public void saveSessionId(String sessionId){
        prefs.edit()
                .putString(KEY_SESSION_ID, sessionId)
                .apply();
    }


    public String getToken() {
        return prefs.getString(KEY_TOKEN, null);
    }

    public String getSessionId() {
        return prefs.getString(KEY_SESSION_ID, null);
    }
}

