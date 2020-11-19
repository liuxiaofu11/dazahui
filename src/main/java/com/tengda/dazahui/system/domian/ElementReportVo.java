package com.tengda.dazahui.system.domian;

import java.io.Serializable;

/**
 * @Author teswell
 * @Date 2020/11/16 15:03
 * @function
 */

public class ElementReportVo implements Serializable {
    private int stationInfoId;
    private int spd;
    private int dir;
    private int hum;
    private int pa;
    private int rain;
    private int temp;


    public int getStationInfoId() {
        return stationInfoId;
    }

    public void setStationInfoId(int stationInfoId) {
        this.stationInfoId = stationInfoId;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    public int getPa() {
        return pa;
    }

    public void setPa(int pa) {
        this.pa = pa;
    }

    public int getRain() {
        return rain;
    }

    public void setRain(int rain) {
        this.rain = rain;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
