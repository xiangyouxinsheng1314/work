package com.myrecyclerview;

/**
 * Created by c_qiangs on 17-1-3.
 */

public class DisplayReporter {
    private String times;
    private int image;
    private int tem;

    public int getTem() {
        return tem;
    }

    public void setTem(int tem) {
        this.tem = tem;
    }

    public String getTimes() {

        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public DisplayReporter(){

    }
    public DisplayReporter(String times,int image, int tem){
        this.times = times;
        this.image = image;
        this.tem = tem;
    }

}
