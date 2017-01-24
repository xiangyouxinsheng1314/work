package com.myrecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by c_qiangs on 16-12-1.
 */

public class WeatherReporter implements Parcelable {

    private String reason;
    private ResultBean result;
    private int error_code;

    public WeatherReporter() {
    }

    public WeatherReporter(String reason, ResultBean result, int error_code) {
        this.reason = reason;
        this.result = result;
        this.error_code = error_code;
    }

    protected WeatherReporter(Parcel in) {
        readFromParcel(in);
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

    public void readFromParcel(Parcel dest) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        reason = dest.readString();
        result = dest.readParcelable(ResultBean.class.getClassLoader());
        error_code = dest.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reason);
        dest.writeParcelable(result, flags);
        dest.writeInt(error_code);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean implements Parcelable {
        /**
         * series : [{"cw":"53","w":"霾","rh":27,"cwd":"02","wd":"东风","wdg":0,"tmp":3,
         * "airp":102215,"st":3,"aqi":0,"tip_aqi":""},{"cw":"53","w":"霾","rh":27,"cwd":"02",
         * "wd":"东风","wdg":0,"tmp":2,"airp":102215,"st":2,"aqi":0,"tip_aqi":""},{"cw":"53",
         * "w":"霾","rh":41,"cwd":"03","wd":"东南风","wdg":0,"tmp":1,"airp":102184,"st":1,"aqi":0,
         * "tip_aqi":""},{"cw":"53","w":"霾","rh":41,"cwd":"03","wd":"东南风","wdg":0,"tmp":1,
         * "airp":102184,"st":1,"aqi":0,"tip_aqi":""},{"cw":"53","w":"霾","rh":41,"cwd":"03",
         * "wd":"东南风","wdg":0,"tmp":0,"airp":102184,"st":0,"aqi":0,"tip_aqi":""},{"cw":"53",
         * "w":"霾","rh":41,"cwd":"08","wd":"北风","wdg":0,"tmp":0,"airp":102203,"st":0,"aqi":0,
         * "tip_aqi":""},{"cw":"53","w":"霾","rh":41,"cwd":"08","wd":"北风","wdg":0,"tmp":0,
         * "airp":102203,"st":0,"aqi":0,"tip_aqi":""},{"cw":"18","w":"雾","rh":41,"cwd":"08",
         * "wd":"北风","wdg":0,"tmp":0,"airp":102203,"st":0,"aqi":0,"tip_aqi":""},{"cw":"18",
         * "w":"雾","rh":42,"cwd":"08","wd":"北风","wdg":0,"tmp":0,"airp":102052,"st":0,"aqi":0,
         * "tip_aqi":""},{"cw":"18","w":"雾","rh":42,"cwd":"08","wd":"北风","wdg":0,"tmp":-1,
         * "airp":102052,"st":-1,"aqi":0,"tip_aqi":""},{"cw":"18","w":"雾","rh":42,"cwd":"08",
         * "wd":"北风","wdg":0,"tmp":-1,"airp":102052,"st":-1,"aqi":0,"tip_aqi":""},{"cw":"18",
         * "w":"雾","rh":50,"cwd":"06","wd":"西风","wdg":0,"tmp":-1,"airp":101954,"st":-1,"aqi":0,
         * "tip_aqi":""},{"cw":"18","w":"雾","rh":50,"cwd":"06","wd":"西风","wdg":0,"tmp":-2,
         * "airp":101954,"st":-2,"aqi":0,"tip_aqi":""},{"cw":"18","w":"雾","rh":50,"cwd":"06",
         * "wd":"西风","wdg":0,"tmp":-2,"airp":101954,"st":-2,"aqi":0,"tip_aqi":""},{"cw":"18",
         * "w":"雾","rh":48,"cwd":"07","wd":"西北风","wdg":3,"tmp":-2,"airp":101890,"st":-2,"aqi":0,
         * "tip_aqi":""},{"cw":"18","w":"雾","rh":48,"cwd":"07","wd":"西北风","wdg":3,"tmp":-2,
         * "airp":101890,"st":-2,"aqi":0,"tip_aqi":""},{"cw":"18","w":"雾","rh":48,"cwd":"07",
         * "wd":"西北风","wdg":3,"tmp":-2,"airp":101890,"st":-2,"aqi":0,"tip_aqi":""},{"cw":"18",
         * "w":"雾","rh":50,"cwd":"07","wd":"西北风","wdg":3,"tmp":-2,"airp":102009,"st":-2,"aqi":0,
         * "tip_aqi":""},{"cw":"18","w":"雾","rh":50,"cwd":"07","wd":"西北风","wdg":3,"tmp":-2,
         * "airp":102009,"st":-2,"aqi":0,"tip_aqi":""},{"cw":"01","w":"多云","rh":50,"cwd":"07",
         * "wd":"西北风","wdg":3,"tmp":-2,"airp":102009,"st":-2,"aqi":0,"tip_aqi":""},{"cw":"01",
         * "w":"多云","rh":31,"cwd":"07","wd":"西北风","wdg":4,"tmp":0,"airp":102007,"st":0,"aqi":0,
         * "tip_aqi":""},{"cw":"01","w":"多云","rh":31,"cwd":"07","wd":"西北风","wdg":4,"tmp":3,
         * "airp":102007,"st":3,"aqi":0,"tip_aqi":""},{"cw":"01","w":"多云","rh":31,"cwd":"07",
         * "wd":"西北风","wdg":4,"tmp":5,"airp":102007,"st":5,"aqi":0,"tip_aqi":""},{"cw":"01",
         * "w":"多云","rh":25,"cwd":"07","wd":"西北风","wdg":4,"tmp":5,"airp":101770,"st":5,"aqi":0,
         * "tip_aqi":""},{"cw":"01","w":"多云","rh":25,"cwd":"07","wd":"西北风","wdg":4,"tmp":5,
         * "airp":101770,"st":5,"aqi":0,"tip_aqi":""}]
         * pubTime : 20161221135642
         * count : 25
         * startTime : 2016122113
         * endTime : 2016122213
         */

        private String pubTime;
        private int count;
        private String startTime;
        private String endTime;
        private List<SeriesBean> series = new ArrayList<SeriesBean>();

        public ResultBean() {

        }

        protected ResultBean(Parcel in) {
            readFromParcel(in);
        }

        public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel in) {
                return new ResultBean(in);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
            }
        };

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public List<SeriesBean> getSeries() {
            return series;
        }

        public void setSeries(List<SeriesBean> series) {
            this.series = series;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public void readFromParcel(Parcel dest) {
            //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
            pubTime = dest.readString();
            count = dest.readInt();
            startTime = dest.readString();
            endTime = dest.readString();
            //Parcelable[] parcelables = dest.readParcelableArray(SeriesBean.class.getClassLoader
             //());
            dest.readList(series,SeriesBean.class.getClassLoader());
            //if (parcelables != null) {
              //  series = Arrays.copyOf(parcelables, parcelables.length, SeriesBean[].class);
            //}
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(pubTime);
            dest.writeInt(count);
            dest.writeString(startTime);
            dest.writeString(endTime);
            //dest.writeParcelableArray(series,flags);
            dest.writeList(series);
        }

        public static class SeriesBean implements Parcelable {
            /**
             * cw : 53
             * w : 霾
             * rh : 27
             * cwd : 02
             * wd : 东风
             * wdg : 0
             * tmp : 3
             * airp : 102215
             * st : 3
             * aqi : 0
             * tip_aqi :
             */

            private String cw;
            private String w;
            private int rh;
            private String cwd;
            private String wd;
            private int wdg;
            private int tmp;
            private int airp;
            private int st;
            private int aqi;
            private String tip_aqi;

            public SeriesBean() {

            }

            protected SeriesBean(Parcel in) {
                readFromParcel(in);
            }

            public static final Creator<SeriesBean> CREATOR = new Creator<SeriesBean>() {
                @Override
                public SeriesBean createFromParcel(Parcel in) {
                    return new SeriesBean(in);
                }

                @Override
                public SeriesBean[] newArray(int size) {
                    return new SeriesBean[size];
                }
            };

            public String getCw() {
                return cw;
            }

            public void setCw(String cw) {
                this.cw = cw;
            }

            public String getW() {
                return w;
            }

            public void setW(String w) {
                this.w = w;
            }

            public int getRh() {
                return rh;
            }

            public void setRh(int rh) {
                this.rh = rh;
            }

            public String getCwd() {
                return cwd;
            }

            public void setCwd(String cwd) {
                this.cwd = cwd;
            }

            public String getWd() {
                return wd;
            }

            public void setWd(String wd) {
                this.wd = wd;
            }

            public int getWdg() {
                return wdg;
            }

            public void setWdg(int wdg) {
                this.wdg = wdg;
            }

            public int getTmp() {
                return tmp;
            }

            public void setTmp(int tmp) {
                this.tmp = tmp;
            }

            public int getAirp() {
                return airp;
            }

            public void setAirp(int airp) {
                this.airp = airp;
            }

            public int getSt() {
                return st;
            }

            public void setSt(int st) {
                this.st = st;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getTip_aqi() {
                return tip_aqi;
            }

            public void setTip_aqi(String tip_aqi) {
                this.tip_aqi = tip_aqi;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public void readFromParcel(Parcel dest) {
                cw = dest.readString();
                rh = dest.readInt();
                w = dest.readString();
                cwd = dest.readString();
                wdg = dest.readInt();
                tmp = dest.readInt();
                airp = dest.readInt();
                st = dest.readInt();
                aqi = dest.readInt();
                wd = dest.readString();
                tip_aqi = dest.readString();

            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {

                dest.writeString(cw);
                dest.writeInt(rh);
                dest.writeString(w);
                dest.writeString(cwd);
                dest.writeInt(wdg);
                dest.writeInt(tmp);
                dest.writeInt(airp);
                dest.writeInt(st);
                dest.writeInt(aqi);
                dest.writeString(wd);
                dest.writeString(tip_aqi);
            }
        }
    }
}
