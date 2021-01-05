package com.tengda.dazahui.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.tengda.dazahui.conmon.utils.CommonResponseDto;
import com.tengda.dazahui.system.dto.TestRequestDto;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author teswell
 * @Date 2020/12/11 15:36
 * @function
 */
@Controller
public class HtmlController {

    @RequestMapping(value = "/vue", method = RequestMethod.GET)
    public String test() {

        return "dingdanliebiao";
    }


    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "success";
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public @ResponseBody
    CommonResponseDto test(@RequestBody String request) {
        TestRequestDto requestDto = JSONObject.parseObject(request, TestRequestDto.class);
        System.out.println(requestDto);
        return new CommonResponseDto().code(0).success(true);
    }


    /****
     * 传入具体日期 ，返回具体日期增加一个月。
     * @param date 日期(2017-04-13)
     * @return 2017-05-13
     * @throws ParseException
     */
    public static String subMonth(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, -1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    public static void main(String[] args) throws ParseException {
        String aa = "1100000000000000";
        //int anInt = Integer.valueOf(aa).intValue();
        Long aLong = Long.valueOf(aa);
        Integer integer = Integer.valueOf(aa, 2);
        short ss = integer.shortValue();
        for (int i = 0; i < 16; i++) {

            Integer dd = ss & 0x01 >> i;
            System.out.println(dd);
        }

        System.out.println(aLong);
        //9223372036854774807
    }

    /**
     * 计算字符长度
     *
     * @param value
     * @return
     */
    public static int length(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < value.length(); i++) {
            // 获取一个字符
            String temp = value.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为2
                valueLength += 2;
            } else {
                // 其他字符长度为1
                valueLength += 1;
            }
        }
        return valueLength;
    }


    /****
     * 传入具体日期 ，返回具体日期减少一天
     * @param date 日期(2017-04-13)
     * @return 2017-04-12
     * @throws ParseException
     */
    private static String subDay(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, -5);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }

}
