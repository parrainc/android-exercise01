package com.example.carlosparra.exercise01.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.carlosparra.exercise01.models.User;

import java.io.Serializable;

public class SharedPreferencesUtil {

    private static final String SHARED_PREFERENCE_NAME = "Settings";
    private static final String SHARED_PREFERENCE_USER_LOGIN = "LOGGED_IN";

    private SharedPreferences mSettings;
    private SharedPreferences.Editor mEditor;

    public SharedPreferencesUtil(Context context) {
        mSettings = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void saveLogIn(boolean isLoggedIn) {
        mEditor = mSettings.edit();
        mEditor.putBoolean(SHARED_PREFERENCE_USER_LOGIN, isLoggedIn);
        mEditor.apply();
    }

    public boolean isLogedIn() {
        return mSettings.getBoolean(SHARED_PREFERENCE_USER_LOGIN, false);
    }

    public Serializable getUserData() {
        return new User();
    }

    public void clearAll() {
        mEditor = mSettings.edit();
        mEditor.clear();
        mEditor.apply();
    }
}
