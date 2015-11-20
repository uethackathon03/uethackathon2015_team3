package com.unblievable.uetsupport.service;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by DucAnhZ on 20/11/2015.
 */
public class CustomAsyncHttpClient extends AsyncHttpClient {
    private final String USERNAME = "unblievable";
    private final String PASSWORD = "123456";

    public CustomAsyncHttpClient(Context context, String tokenHeader) {
        this.addHeader("username", USERNAME);
        this.addHeader("password", PASSWORD);
        this.addHeader("token", tokenHeader);
    }
}
