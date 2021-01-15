package com.tengda.dazahui.system.controller;

import jdk.internal.util.xml.impl.Input;

import java.io.*;

/**
 * @Author teswell
 * @Date 2020/11/17 17:59
 * @function
 */
public class TestController {

    /**
     * 地球半径
     */
    private static double EarthRadius = 6378.137;

    /**
     * 经纬度转化成弧度
     * @param d 经度/纬度
     * @return
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }


    /**
     * 计算两个坐标点之间的距离
     * @param firstLatitude   第一个坐标的纬度
     * @param firstLongitude  第一个坐标的经度
     * @param secondLatitude  第二个坐标的纬度
     * @param secondLongitude 第二个坐标的经度
     * @return 返回两点之间的距离，单位：公里/千米
     */
    public static double getDistance(double firstLatitude, double firstLongitude,
                                     double secondLatitude, double secondLongitude) {
        double firstRadLat = rad(firstLatitude);
        double firstRadLng = rad(firstLongitude);
        double secondRadLat = rad(secondLatitude);
        double secondRadLng = rad(secondLongitude);

        double a = firstRadLat - secondRadLat;
        double b = firstRadLng - secondRadLng;
        double cal = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(firstRadLat)
                * Math.cos(secondRadLat) * Math.pow(Math.sin(b / 2), 2))) * EarthRadius;
        double result = Math.round(cal * 10000d) / 10000d;
        return result;
    }
//    public static void main(String[] args) {
//        double distance = getDistance(39.947468, 116.547423, 39.947480, 116.547440);
//        System.out.println(distance);
//    }

    public static void main(String[] args) {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("abcdefg");
//        System.out.println(stringBuffer.reverse());

        File  file = new File("");
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] b =new byte[1024];
        int a = 0;
        try {
            a = is.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] str = new String(b,0,a).split("");
        int count = 0;
        for (int i = 0; i <str.length ; i++) {
       //设置查询统计的字符窜为a
            if ("a".equals(str[i])) {
                count++;
            }
        }
        System.out.println(count);
    }

}
