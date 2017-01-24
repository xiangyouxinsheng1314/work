package com.myrecyclerview.services;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myrecyclerview.WeatherReporter;
import com.myrecyclerview.WeatherReporter.ResultBean;
import com.myrecyclerview.WeatherReporterManager;
import com.myrecyclerview.services.interfaces.IWeatherService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

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
                public List<WeatherReporter> getReporters(String city, List<String> time) throws
                        RemoteException {
                    Log.i("xiangkezhu ", "getReporters() in service");
                    return null;
                }

                @Override
                public void getReporter(int areaId, String startTime, String endTime,
                                        WeatherReporter weatherReporter) throws
                        RemoteException {
                    Log.i("xiangkezhu ", "getReporter() in service");

                    /*OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(WEBSERVICE_URL
                            +"areaid="+areaId +"&startTime="+startTime +"&endTime="+endTime+"&"+KEY)
                            .build();
                    Log.i("xiangkezhu ", "request" + request);
                    /*try {
                        Response response = client.newCall(request).execute();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
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
                                weatherReporter2 = JSONObject.parseObject(jsonString,
                                        WeatherReporter.class);
                                Log.i("xiangkezhu ", "jsonString = " + jsonString );
                                String str = response.networkResponse().toString();
                                Log.i(TAG, "network---" + str);

                            }

                        }
                    });*/
                    try {
                        InputStreamReader isr = new InputStreamReader(getAssets().open("json" +
                                ".json"), "UTF-8");
                        BufferedReader br = new BufferedReader(isr);
                        String line;
                        StringBuilder builder = new StringBuilder();
                        while ((line = br.readLine()) != null) {
                            builder.append(line);
                        }
                        br.close();
                        isr.close();
                        Log.i("xiangkezhu ", "JSON = " + builder.toString());
                        JSONObject jsonObject = JSONObject.parseObject(builder.toString());
                        //String message0=jsonObject.getString("result");
                        //Log.i("xiangkezhu ", "message0 = " + message0);
                        //JSONObject resultObject = JSONObject.parseObject(message0);
                        ResultBean result = jsonObject.getObject("result",ResultBean.class);
                        //String series=resultObject.getString("series");
                        //Log.i("xiangkezhu ", "series = " + series);
                        //List<ResultBean.SeriesBean> list=new ArrayList<ResultBean.SeriesBean>
                                //(JSONArray.parseArray
                                //(series,ResultBean.SeriesBean.class));
//                        List<ResultBean.SeriesBean> seriesBeenList = new ArrayList<ResultBean.SeriesBean>(
//                                JSONArray.parseArray(builder.toString(),
//                                        ResultBean
//                                                .SeriesBean.class));
//                        Log.i("xiangkezhu ", "seriesBeenList = " + seriesBeenList);
                        //result.setSeries(seriesBeenList);
                        //result.setSeries(list);
                        String reason = jsonObject.getString("reason");
                        int error_code = jsonObject.getInteger("error_code");
                        Log.i("xiangkezhu ", "result = " + result);
                        Log.i("xiangkezhu ", "reason = " + reason);
                        Log.i("xiangkezhu ", "error_code = " + error_code);
                        weatherReporter.setResult(result);
                        weatherReporter.setReason(reason);
                        weatherReporter.setError_code(error_code);
                        if(weatherReporter != null) {
                            Log.i("xiangkezhu ", "weatherReporter = " + weatherReporter);
                        }else{
                            Log.i("xiangkezhu ", "weatherReporter = null" );
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("xiangkezhu ", "Service onCreate()");
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
        Log.i("xiangkezhu ", "Service onBind()");

        return mWeatherReporterManager;
    }
}
