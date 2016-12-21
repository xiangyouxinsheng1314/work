package com.myrecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Map;

/**
 * Created by c_qiangs on 16-12-1.
 */

public class WeatherReporter implements Parcelable {
    //开始时间
    private String startTime;
    //发布时间
    private String pubTime;
    //请求收到时间
    private String reqTime;
    //数据
    private Map<String, String> series;
    private String city;
    private Date mDate;

    public WeatherReporter() {
    }

    public WeatherReporter(String startTime, String pubTime, String reqTime, Map<String, String>
            series, String city, Date mDate, String mMaxTemperature, String mMinTemperature,
                           String status, String count, String endTime) {
        this.startTime = startTime;
        this.pubTime = pubTime;
        this.reqTime = reqTime;
        this.series = series;
        this.city = city;
        this.mDate = mDate;
        this.mMaxTemperature = mMaxTemperature;
        this.mMinTemperature = mMinTemperature;
        this.status = status;
        this.count = count;
        this.endTime = endTime;
    }

    protected WeatherReporter(Parcel in) {
        startTime = in.readString();
        pubTime = in.readString();
        reqTime = in.readString();
        city = in.readString();
        mMaxTemperature = in.readString();
        mMinTemperature = in.readString();
        status = in.readString();
        count = in.readString();
        endTime = in.readString();
    }

    public static final Creator<WeatherReporter> CREATOR = new Creator<WeatherReporter>() {
        @Override
        public WeatherReporter createFromParcel(Parcel in) {
            return new WeatherReporter(in);
        }

        @Override
        public WeatherReporter[] newArray(int size) {
            return new WeatherReporter[size];
        }
    };

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setmMaxTemperature(String mMaxTemperature) {
        this.mMaxTemperature = mMaxTemperature;
    }

    public void setmMinTemperature(String mMinTemperature) {
        this.mMinTemperature = mMinTemperature;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getmDate() {
        return mDate;
    }

    public String getmMaxTemperature() {
        return mMaxTemperature;
    }

    public String getmMinTemperature() {
        return mMinTemperature;
    }

    public String getStatus() {
        return status;
    }

    private String mMaxTemperature;
    private String mMinTemperature;
    private String status;

    //条数
    private String count;
    //结束时间
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public String getPubTime() {
        return pubTime;
    }

    public String getReqTime() {
        return reqTime;
    }

    public Map<String, String> getSeries() {
        return series;
    }

    public String getCount() {
        return count;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public void setSeries(Map<String, String> series) {
        this.series = series;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        startTime = dest.readString();
        pubTime = dest.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(startTime);
        dest.writeString(pubTime);
        dest.writeString(reqTime);
        dest.writeString(city);
        dest.writeString(mMaxTemperature);
        dest.writeString(mMinTemperature);
        dest.writeString(status);
        dest.writeString(count);
        dest.writeString(endTime);
    }
}
