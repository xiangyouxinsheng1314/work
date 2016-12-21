// WeatherReporterManager.aidl
package com.myrecyclerview;
import com.myrecyclerview.WeatherReporter;
// Declare any non-default types here with import statements

interface WeatherReporterManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    List<WeatherReporter> getReporters(String city,String time);
    void getReporter(int areaId,String startTime,String endTime,out WeatherReporter
    weatherReporter);


}
