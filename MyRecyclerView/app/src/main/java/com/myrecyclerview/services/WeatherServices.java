package com.myrecyclerview.services;


import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

/**
 * Created by c_qiangs on 16-11-28.
 */

public class WeatherServices extends Service {
    private static final String WEBSERVICE_URL = "http://www.google.com/ig/api?weather=%s&hl=zh-cn";
    private static final String TAG = "WeatherServices";

    @Override
    public void onCreate() {
        super.onCreate();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(String.format(WEBSERVICE_URL));
        try {
            Log.d(TAG, "get google's weather infomation");
            HttpResponse response = client.execute(request);

            StatusLine status = response.getStatusLine();
            Log.d(TAG, "Request returned status " + status);

            HttpEntity entity = response.getEntity();
            responseReader = new InputStreamReader(entity.getContent(), "GB2312");

        } catch (IOException e) {

        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        super.dump(fd, writer, args);
    }
}
