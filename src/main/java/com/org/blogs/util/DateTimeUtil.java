package com.org.blogs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.XMLGregorianCalendar;

public class DateTimeUtil {

	// public static
	/**
	 * yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return
	 * @author Mr.chang
	 */
	public static String getCurTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}

	/**
	 * yyyy-MM-dd "
	 * 
	 * @return
	 * @author Mr.chang
	 */
	public static String getCurTime2() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
		return dateFormat.format(new Date());
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 * @param date
	 *            日期字符串
	 * @author Mr.chang
	 */
	public static String getCurTime3(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
		return dateFormat.format(date);
	}

	/**
	 * 时间字符串转换为date类型
	 * 
	 * @param dateVal
	 *            2014-02-22 12:12:12
	 * @return
	 */
	public static Date convertStringToDate(String dateVal) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date = dateFormat.parse(dateVal);
			return date;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 时间转换为指定类型字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDateToStr(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		if (date == null) {
			return dateFormat.format(new Date());
		} else {
			return dateFormat.format(date);
		}

	}

	/**
	 * 将亚马逊指定时间格式转换为String字符串
	 * 
	 * @param cal
	 *            XMLGregorianCalendar 类型
	 * @return
	 */
	public static String convertDateToStr(XMLGregorianCalendar cal) {
		GregorianCalendar ca = cal.toGregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(ca.getTime());
	}

	/**
	 * 2 * 获取指定时间到格林威治时间的秒数 3 *
	 * UTC：格林威治时间1970年01月01日00时00分00秒（UTC+8北京时间1970年01月01日08时00分00秒） 4 * @param
	 * time 5 * @return 6
	 */
	public static long diffSeconds(String time) {
		Calendar calendar = Calendar.getInstance();

		calendar.clear();
		Date datetime = convertStringToDate(time);
		calendar.setTime(datetime);

		TimeZone timeZone = TimeZone.getTimeZone("GMT+08:00");
		calendar.setTimeZone(timeZone);
		return calendar.getTimeInMillis() / 1000;
	}

	/**
	 * 根据时间字符串 得到该时间 减去Val 之前的时间 减的类型type 可以是 天，小时，分,秒
	 * 
	 * @param strdate
	 *            需要处理的时间字符串
	 * @param type
	 *            需要计算的类型 如天，小时 注：值只能是 day,hour,minute,second
	 * @param val
	 * @return
	 */
	public static String getDatebyDiff(String strdate, String type, int val) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date date = null;
			if (strdate == null || strdate.length() == 0) {
				date = new Date();
			} else {
				date = dateFormat.parse(strdate);
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (type.equalsIgnoreCase("day")) {
				calendar.add(Calendar.DAY_OF_MONTH, val);
			} else if (type.equalsIgnoreCase("hour")) {
				calendar.add(Calendar.HOUR_OF_DAY, val);
			} else if (type.equalsIgnoreCase("minute")) {
				calendar.add(Calendar.MINUTE, val);
			} else if (type.equalsIgnoreCase("second")) {
				calendar.add(Calendar.SECOND, val);
			}

			date = calendar.getTime();
			return dateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取当前日期的前几天
	 * 
	 * @param specifiedDay
	 * @param val
	 *            前几天
	 * @return
	 * @author Mr.chang
	 */
	public static String getSpecifiedDayAfter(String specifiedDay, int val) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + val);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
	}

	/**
	 * 获取当前日期的前几天
	 * 
	 * @param specifiedDay
	 * @param val
	 *            前几天
	 * @return
	 * @author Mr.chang
	 */
	public static String getSpecifiedDayBefore(String specifiedDay, int val) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - val);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
	}

	/**
	 * 获取当前年份的第一天
	 * 
	 * @param specifiedDay
	 * @param val
	 *            前几天
	 * @return
	 * @author Mr.chang
	 */
	public static String getThefirstdayofthecurrentyear() {
		Calendar cal = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		Date currYearFirst = calendar.getTime();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd ");
		String sDate = f.format(currYearFirst);
		return sDate;
	}

	/**
	 * 获取本月第一天
	 */
	public static String getFirstMonth() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		Date beginTime = cal.getTime();
		return datef.format(beginTime) + " 00:00:00";
	}

	public static void main(String[] args) {
		// DateTimeUtil.getFisrtDayOfMonth();
		System.out.println(getFirstMonth());
	}

}
