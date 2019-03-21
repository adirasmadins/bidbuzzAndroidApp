package com.bidbuzz.utils;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bidbuzz.androidApp.AppController;
import com.bidbuzz.utils.interfaces.ServerCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class VolleyService {

    ServerCallback mServerCallback = null;
    Context mContext;

    public VolleyService(ServerCallback serverCallback, Context context){
        mServerCallback = serverCallback;
        mContext = context;
    }


    public void postDataVolley(final String requestType, String url, final HashMap<String,String> body, final HashMap<String,String> header){
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                if(mServerCallback != null)
                    mServerCallback.onSuccess(requestType,response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(mServerCallback != null)
                    mServerCallback.onError(requestType,error);
            }
        }) {
            @Override
            public byte[] getBody() {
                return new JSONObject(body).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    public void getDataVolley(final String requestType, String url){
        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(mServerCallback != null)
                        mServerCallback.onSuccess(requestType, response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mServerCallback != null)
                        mServerCallback.onError(requestType, error);
                }
            });

            queue.add(jsonObj);

        }catch(Exception e){

        }
    }
}