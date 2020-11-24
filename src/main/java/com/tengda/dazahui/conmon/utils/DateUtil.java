package com.tengda.dazahui.conmon.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author: created by wdp
 * @function:.
 */
@Slf4j
public class DateUtil {
    // 英文简写（默认）如：2010-12-01
    public static String FORMAT_SHORT = "yyyy-MM-dd";

    // 英文简写 如：12:00:00
    public static String FORMAT_SHORT_HM = "HH:mm:ss";

    // 英文全称 如：2010-12-01 23:15:06
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    // 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    // 中文简写 如：2010年12月01日
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

    // 中文全称 如：2010年12月01日 23时15分06秒
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    // 精确到毫秒的完整中文时间
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    private DateUtil() {

    }

    private static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simformat = new SimpleDateFormat(dateFormatType);
        return simformat.format(date);
    }

    public static String formatCSTTime(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date d = sdf.parse(date);
        return getDateFormat(d, format);
    }

    public static Timestamp strToTimestamp(String str) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            ts = Timestamp.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }

    /**
     * Date转时间字符串(yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_LONG);
        return format.format(date);
    }

    /**
     * 时间字符串转Date(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static Date strToDate(String date) {
        if (date == "") return null;
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_LONG);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(date, pos);
        return strtodate;
    }

    public static int strTimeToInt(String str) {
        str.replace("-", "");
        return Integer.parseInt(str);
    }

    /**
     * @return
     * @throws //获取当前时间
     */
    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_LONG);// 设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    /**
     * @return
     * @throws //yyyyMMddHHmmss ----- strLong
     */
    public static Long strTransTimeStamp(String str) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime ldt = LocalDateTime.parse(str, dtf);
        DateTimeFormatter fa = DateTimeFormatter.ofPattern(FORMAT_LONG);
        String datetime2 = ldt.format(fa);
        return Timestamp.valueOf(datetime2).getTime() / 1000;
    }

    /**
     * @return
     * @throws //yyyy-MM-dd HH:mm:ss ----- strLong
     */
    public static long strTransTimeStamp2(String str) {
        if (null == str || str == "") return 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(FORMAT_LONG);
        LocalDateTime ldt = LocalDateTime.parse(str, dtf);
        DateTimeFormatter fa = DateTimeFormatter.ofPattern(FORMAT_LONG);
        String datetime2 = ldt.format(fa);
        return Timestamp.valueOf(datetime2).getTime() / 1000;
    }

    /**
     * yyyy-MM-dd -----strLong
     *
     * @param str
     * @return
     */
    public static long strTransTimeStamp3(String str) {
        if (null == str || str == "") return 0;
        SimpleDateFormat dtf = new SimpleDateFormat(FORMAT_SHORT);
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(dtf.parse(str));
            return c.getTime().getTime() / 1000;
        } catch (ParseException p) {
            p.printStackTrace();
        }
        return 0;
    }

    public static Long getUTCCurrentTime() {
        Calendar instance = Calendar.getInstance();
        return instance.getTimeInMillis() / 1000;
    }

    /**
     * @param str
     * @return strLong to yyyy-MM-dd HH:mm:ss
     */
    public static String timeStampTransFormat(String str) {
        if (StringUtils.isBlank(str) || str.equals("0")) return "";
        SimpleDateFormat sf = new SimpleDateFormat(FORMAT_LONG);
        Date date = new Date();
        date.setTime(Long.valueOf(str) * 1000);
        return sf.format(date);
    }

    /**
     * @param str
     * @return strLong to yyyy-MM-dd
     */
    public static String timeStampTransFormatShort(String str) {
        if (StringUtils.isBlank(str)) return "";
        SimpleDateFormat sf = new SimpleDateFormat(FORMAT_SHORT);
        Date date = new Date();
        date.setTime(Long.valueOf(str) * 1000);
        return sf.format(date);
    }

    /**
     * @param past
     * @return getPastDate
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_LONG);
        String result = format.format(today);
        return result;
    }

    /**
     * @param past
     * @return yyyy-MM-dd
     */
    public static String getPastDate1(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_SHORT);
        String result = format.format(today);
        return result;
    }

    /**
     * @param year
     * @param month
     * @return currentMonthLast
     */
    public static int getCurrentMonthLastDay(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 0, 0, 0, 0);
        c.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        c.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        int maxDate = c.get(Calendar.DATE);
        return maxDate;
    }

    public static long getCurrentDayStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return (cal.getTimeInMillis() / 1000);
    }

    public static long getCurrentDayEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return (cal.getTimeInMillis() / 1000);
    }

    // 获得时间差 并返回HH:mm:ss 格式字符串
    public static String getDiffTime(Date d1, Date d2) {
        long number = d1.getTime() - d2.getTime();
        long time = number - TimeZone.getDefault().getRawOffset();
        DateFormat formatter = new SimpleDateFormat(FORMAT_SHORT_HM);
        return formatter.format(time);
    }

    // HH:mm:ss 转时间戳
    public static long getLongTimeByShortHm(String str) {
        DateFormat formatter = new SimpleDateFormat(FORMAT_SHORT_HM);
        try {
            return formatter.parse(str).getTime() + TimeZone.getDefault().getRawOffset();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 时间戳转 HH:mm:Ss
    public static String getDiffTime2(long time) {
        long t = time - TimeZone.getDefault().getRawOffset();
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_SHORT_HM);
        return format.format(t);
    }

    public static long getGpsDayTime(long gpsTime) {
        long tmpTime = gpsTime / 86400 * 86400 + 24 * 60 * 60;// 24h*24h
        return tmpTime;
    }

    // 查询过去两个月
    public static String getLastTwoMonth() {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_LONG);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -2);
        Date m = c.getTime();
        return format.format(m);
    }

    public static long twoTimeDiffSeconds(String start, String end) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_LONG);
        try {
            Date date1 = format.parse(start);
            Date date2 = format.parse(end);
            long diff = date2.getTime() - date1.getTime();
            return diff / 1000;
        } catch (ParseException e) {
            log.error("twoTimeDiff parse error");
            return 0;
        }
    }

    public static int shijian(String time) {
        if (time != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            try {
                Date parse = sdf.parse(time);
                //System.out.println(parse.getTime());
                return (int) parse.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static String shijianjiequ(String realtime1) {
        try {
            String[] split = realtime1.split(" ");
            for (int i = 0; i < split.length; i++) {
                String s = split[0];
                //System.out.println(s);
                return s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串时间转对应时间
     *
     * @param str
     * @return
     */
    public static String strinTimeConversion(String str) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dd = null;
        try {
            dd = sd.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String format = sd.format(dd);

        return format;
    }

    /**
     * 获取指定日期所在月份开始的时间
     * lkeji
     *
     * @return
     */
    public static String getMonthBegin(String specifiedDay) {
        Date data = null;
        try {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 本月第一天的时间戳转换为字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(sdf.format(new Date(new Long(c.getTimeInMillis()))));
            //Date date = sdf.parse(sdf.format(new Long(s)));// 等价于
            return sdf.format(date);
        } catch (NumberFormatException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取指定日期所在月份结束的时间
     *
     * @return
     */
    public static String getMonthEnd(String specifiedDay) {
        Date data = null;
        try {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(data);

        //设置为当月最后一天
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至23
        c.set(Calendar.HOUR_OF_DAY, 23);
        //将分钟至59
        c.set(Calendar.MINUTE, 59);
        //将秒至59
        c.set(Calendar.SECOND, 59);
        //将毫秒至999
        c.set(Calendar.MILLISECOND, 999);
        // 本月第一天的时间戳转换为字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(sdf.format(new Date(new Long(c.getTimeInMillis()))));
            //Date date = sdf.parse(sdf.format(new Long(s)));// 等价于
            return sdf.format(date);
        } catch (NumberFormatException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取两个日期之间的所有日期
     *
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
    public static List<String> getDays(String startTime, String endTime) {

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date start = null;
            try {
                start = dateFormat.parse(startTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat1.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }
	
	/**
	 * 推算N年后的时间
	 * @return
	 */
	public static String getYearsLaterDate(Date data,int i) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.add(Calendar.YEAR, i);
		Date time = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_SHORT);
		return sdf.format(time);
	}
}
