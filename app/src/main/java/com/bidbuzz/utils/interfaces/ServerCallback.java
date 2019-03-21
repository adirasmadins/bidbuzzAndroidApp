package com.bidbuzz.utils.interfaces;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface ServerCallback {
    public void onSuccess(String requestType,JSONObject response);
    public void onError(String requestType, VolleyError error);
}
