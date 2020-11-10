package com.tengda.dazahui.conmon.utils;

import java.math.BigInteger;

/**
 * @Author teswell
 * @Date 2020/11/10 15:40
 * @function
 */
public class IntConversion32Bits {
    /**
     * int类型获取32位   另外一种方法是循环32次 获取位数    (23556 & (0x01 <<i))   i代表位移第几位  0x01代表第一位
     * @param num
     * @return
     */
    public static String getfull(int num) {
        String s = Integer.toBinaryString(num);
        String format = String.format("%032d", new BigInteger(s));
        return format;
    }



    public static void main(String[] args) {
        int a = 35674;
        String getfull = getfull(a);
        System.out.println(getfull);
    }
}
