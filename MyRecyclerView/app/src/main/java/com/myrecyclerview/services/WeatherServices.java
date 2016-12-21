package com.myrecyclerview.services;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.myrecyclerview.WeatherReporter;
import com.myrecyclerview.WeatherReporterManager;
import com.myrecyclerview.services.interfaces.IWeatherService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by c_qiangs on 16-11-28.
 */

public class WeatherServices extends Service {
    private static final String WEBSERVICE_URL = "http://v.juhe" +
            ".cn/xiangji_weather/weather_byHour_areaid.php?";
    private static final String KEY = "&key=3e40fb2779b7005d8ec4055391a886e9";
    private String startTime;
    private String endTime;
    private int areaid;
    private String jsonString;
    private static final String TAG = "WeatherServices";
    private final List<WeatherReporter> weatherReporters = new ArrayList<WeatherReporter>();
    private int count;
    private final WeatherReporter mWeatherReporter = null;
    private ServiceBinder serviceBinder = new ServiceBinder();
    private boolean threadDisable;
    private List<WeatherReporter> mWeatherReporters = new ArrayList<>();
    private final WeatherReporterManager.Stub mWeatherReporterManager = new
            WeatherReporterManager.Stub() {
                WeatherReporter weatherReporter2 = null;

                @Override
                public List<WeatherReporter> getReporters(String city, String time) throws
                        RemoteException {
                    Log.i("xiangkezhu ", "getReporters() in service");
                    return null;
                }

                @Override
                public void getReporter(int areaId, String startTime, String endTime,
                                        WeatherReporter weatherReporter) throws
                        RemoteException {
                    Log.i("xiangkezhu ", "getReporter() in service");

                    Log.i("xiangkezhu ", "Service onCreate()");
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(WEBSERVICE_URL
                            +"areaid="+areaId +"&startTime="+startTime +"&endTime="+endTime+"&"+KEY)
                            .build();
                    Log.i("xiangkezhu ", "request" + request);
                    /*try {
                        Response response = client.newCall(request).execute();
                    }catch (IOException e){
                        e.printStackTrace();
                    }*/
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i(TAG, "HTTP FAILED!!!");

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.i("xiangkezhu ", "onResponse" );
                            if (null != response.cacheResponse()) {
                                String str = response.cacheResponse().toString();
                                Log.i(TAG, "cache---" + str);
                            } else {
                                jsonString = response.body().string();
                                Log.i("xiangkezhu ", "jsonString = " + jsonString );
                                String str = response.networkResponse().toString();
                                weatherReporter2 = JSON.parseObject
                                        (jsonString,
                                        WeatherReporter
                                        .class);
                                Log.i(TAG, "network---" + str);

                            }

                        }
                    });
                    weatherReporter = weatherReporter2;
                }

            };

    @Override
    public void onCreate() {
        super.onCreate();

        /*new Thread(new Runnable() {
            // @Override
            public void run() {
                while (!threadDisable) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    count++;
                    if (count == 100){
                        threadDisable = true;
                    }
                    System.out.println("CountService Count is " + count);
                }
            }
        }).start();*/
    }

    public class ServiceBinder extends Binder implements IWeatherService {
        // @Override
        public int getCount() {
            return count;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.threadDisable = true;
        Log.v(" CountService ", " on destroy ");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("xiangkezhu ", "onBind()");

        return mWeatherReporterManager;
    }
}
