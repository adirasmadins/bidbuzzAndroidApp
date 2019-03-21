package com.bidbuzz.utils;


import android.app.Service;
import android.content.Intent;

import com.bidbuzz.androidApp.AppController;
import com.bidbuzz.androidApp.MainActivity;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class AuthUser {
    private static String token = null;
    private static String username;
    private static int coins;

    private static AuthUser ourInstance = null;

    public static AuthUser getInstance() {
        return ourInstance != null ? ourInstance : new AuthUser();
    }

    public void setToken(String token){
        this.token = token;
        Intent i = new Intent("com.mycompany.myapp.SOME_MESSAGE");
        LocalBroadcastManager.getInstance(AppController.getContext()).sendBroadcast(i);

    }

    public String getToken(){
        return this.token;
    }

}
