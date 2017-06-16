package com.shop.www.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
	private static final String patternDateShort = "yyyyMMdd";

	private static final String patternDate = "yyyy-MM-dd";

	private static final String patternDateTime = "yyyy-MM-dd HH:mm:ss";

	private static final String patternDateMinutes = "yyyy-MM-dd HH:mm";

	public static final long ONE_MINUTE = 60 * 1000L;

	public static final long ONE_HOUR = 60 * 60 * 1000L;

	public static final long ONE_DAY = 24 * ONE_HOUR;

	public static final long ONE_WEEK = 7 * ONE_DAY;

	public static final SimpleDateFormat MIDDLE_PATTEN = new SimpleDateFormat("MMM dd, yyyy 'at' HH:mm a", Locale.US);

	public static final SimpleDateFormat SMALL_PATTEN = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

	public static String getDatePattern() {
		return patternDate;
	}

	public static String getLastDate(int year, int month) {
		int[] monthDay = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			monthDay[1] = 29;
		}
		int monthDayNum = monthDay[month - 1];
		String end = year + "-" + month + "-" + monthDayNum;
		return end;
	}

	/* ��ʽ������Ϊ����ʽ */
	public static String getShortDate(Date myDate) {
		// SimpleDateFormat fd = new
		// SimpleDateFormat(patternDateShort,Locale.CHINA);
		SimpleDateFormat fd = new SimpleDateFormat(patternDateShort);
		return (fd.format(myDate));
	}

	/* ��ʽ������Ϊ��׼��ʽ */
	public static String formatDateTime(Date myDate, String pattern) {
		myDate = isDate(myDate);
		// SimpleDateFormat fd = new SimpleDateFormat(pattern, Locale.CHINA);
		SimpleDateFormat fd = new SimpleDateFormat(pattern);
		return (fd.format(myDate));
	}

	/* ��ʽ������Ϊ��׼��ʽ */
	public static String formatDateTime(String myDate, String pattern) {
		if (myDate.length() > 10)
			myDate = myDate.substring(0, 10);
		Date date = getDateByString(myDate, "yyyy-MM-dd");
		return formatDateTime(date, pattern, Locale.US);
	}

	/* ��ʽ������Ϊ��׼��ʽ */
	public static String formatDateTime(Date myDate, String pattern, Locale localcode) {
		myDate = isDate(myDate);
		SimpleDateFormat fd = new SimpleDateFormat(pattern, localcode);
		return (fd.format(myDate));
	}

	/* �ж�myDate�Ƿ�Ϊnull */
	public static Date isDate(Date myDate) {
		// if (myDate == null)
		// return new Date();
		return myDate;
	}

	// ���ڲ�
	public static long getQuot(Date time1, Date time2) {
		long quot = 0;
		try {
			quot = time1.getTime() - time2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return quot;
	}

	public static Integer getTodayTime() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(today.getTimeInMillis()).substring(0, 10));
	}

	public static Integer getTomorrowTime() {
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.set(Calendar.HOUR_OF_DAY, 24);
		tomorrow.set(Calendar.MINUTE, 0);
		tomorrow.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(tomorrow.getTimeInMillis()).substring(0, 10));
	}

	public static String getTipDay(String date) {
		long quot = getQuot(parse(date), new Date());
		if (quot == 0)
			return "����";
		else if (quot == 1)
			return "����";
		else if (quot == 2)
			return "����";
		return "";
	}

	// �ж�����Ϊ���ڼ�,1Ϊ������com.vnvtrip.util,��������
	public static int dayOfWeek(Object date1) {
		Date date = (Date) date1;
		// ���ȶ���һ��calendar������ʹ��getInstance()����ʵ����
		Calendar aCalendar = Calendar.getInstance();
		// ����Ұ����ֱ�Ӳ���date����
		aCalendar.setTime(date);
		// �����������һ���е���һ��
		int x = aCalendar.get(Calendar.DAY_OF_WEEK);
		return x;
	}

	public static String dayOfWeek2(Object date1) {
		Date date = (Date) date1;
		// ���ȶ���һ��calendar������ʹ��getInstance()����ʵ����
		Calendar aCalendar = Calendar.getInstance();
		// ����Ұ����ֱ�Ӳ���date����
		aCalendar.setTime(date);
		// �����������һ���е���һ��
		int x = aCalendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeekByDayNum(x);
	}

	public static String dayOfWeekByDayNum(int x) {
		String str = "����";
		if (x == 7) {
			str = "����";
		} else if (x == 6) {
			str = "����";
		} else if (x == 5) {
			str = "����";
		} else if (x == 4) {
			str = "����";
		} else if (x == 3) {
			str = "�ܶ�";
		} else if (x == 2) {
			str = "��һ";
		}
		return str;
	}

	// ���ݵ�ǰһ�������еĵڼ���õ���������
	public static Date getDateOfCurrentWeek(int day) {
		Calendar aCalendar = Calendar.getInstance();
		int x = aCalendar.get(Calendar.DAY_OF_WEEK);
		aCalendar.add(Calendar.DAY_OF_WEEK, day - (x - 1));
		return aCalendar.getTime();
	}

	// ĳһ����һ�����еĵڼ���
	public static int getWeekOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	public static Date addSomeDay(Date oldDate, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oldDate);
		calendar.add(Calendar.DATE, num);
		return calendar.getTime();
	}

	public static Date addHour(Date oldDate, int num) {
		oldDate.setTime(oldDate.getTime() + num * 3600 * 1000);
		return oldDate;
	}

	// �����ڡ�2006-09-01��ת����20060901
	public static String Dateformat(String date) {
		return date.replaceAll("-", "");

	}

	// �����ڡ�2006-09-01��ת���� ����һ
	public static String getWeekDay(String date) {
		return formatDateTime(parse(date), "EE");

	}

	/* ����static���� */
	/* ��ʽ������Ϊyyyy-MM-dd��ʽ */
	public static String formatDate(Date myDate) {
		return formatDateTime(myDate, patternDate);
	}

	/* ��ʽ������Ϊyyyy-MM-dd HH:mm��ʽ */
	public static String formatDateMinutes(Date myDate) {
		return formatDateTime(myDate, patternDateMinutes);
	}

	/* ��ʽ������Ϊyyyy-MM-dd HH:mm��ʽ */
	public static String formatDateMinutes(Date myDate, String pattern) {
		return formatDateTime(myDate, pattern);
	}

	/* ��ʽ������Ϊyyyy-MM-dd HH:mm:ss��ʽ */
	public static String formatDateTime(Date myDate) {
		return formatDateTime(myDate, patternDateTime);
	}

	/* ���ַ���ת�������� */
	public static Date getDateByString(String rq) {
		// DateFormat df = DateFormat.getDateInstance();
		DateFormat df = new SimpleDateFormat(patternDate);
		Date d = null;
		try {
			d = df.parse(rq);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return d;
	}

	/* ���ַ���ת�������� */
	public static Date getDateString(String rq) {
		// DateFormat df = DateFormat.getDateInstance();
		DateFormat df = new SimpleDateFormat(patternDateMinutes);
		Date d = null;
		try {
			d = df.parse(rq);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return d;
	}

	/* ���ַ���ת�������� */
	public static Date parse(String param) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(patternDate);
		try {
			date = sdf.parse(param);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return date;
	}

	//
	public static Integer getIntPreMonthFirstDay(Integer last_month) {
		Date date = convertInt2Date(last_month);
		GregorianCalendar aGregorianCalendar = new GregorianCalendar();
		aGregorianCalendar.setTime(date);
		aGregorianCalendar.set(Calendar.MONTH, aGregorianCalendar.get(Calendar.MONTH) - 1);
		aGregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		return convertDateToInt(aGregorianCalendar.getTime());

	}

	//
	public static Integer getIntNextMonthFirstDay(Integer last_month) {
		Date date = convertInt2Date(last_month);
		GregorianCalendar aGregorianCalendar = new GregorianCalendar();
		aGregorianCalendar.setTime(date);
		aGregorianCalendar.set(Calendar.MONTH, aGregorianCalendar.get(Calendar.MONTH) + 1);
		aGregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		return convertDateToInt(aGregorianCalendar.getTime());

	}

	// ���ӻ���ټ�����
	public static Date addMonth(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.MONTH, num);
		return startDT.getTime();
	}

	// ���ӻ��������
	public static Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	// ���ӻ��������
	public static Date addYear(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.YEAR, num);
		return startDT.getTime();
	}

	public static Integer addDay(Integer time, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.valueOf(time + "000"));
		calendar.add(Calendar.HOUR_OF_DAY, day * 24);
		return Integer.valueOf(String.valueOf(calendar.getTimeInMillis()).substring(0, 10));
	}

	/* ���ַ���ת�������� */
	public static Date getDateTimeByString(String rq) {
		DateFormat df = new SimpleDateFormat(patternDateTime);
		// DateFormat df = DateFormat.getDateTimeInstance();
		Date d = null;
		try {
			d = df.parse(rq);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return d;
	}

	public static boolean isSameDay(Date c1, Date c2) {
		return formatDate(c1).equals(formatDate(c2));
	}

	public static Calendar string2Cal(String arg) {
		SimpleDateFormat sdf = null;
		String trimString = arg.trim();
		if (trimString.length() > 14)
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		else
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(trimString);
		} catch (ParseException e) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal;
	}

	/**
	 * ϵͳ��ǰʱ��
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		java.util.Date currentTime = new java.util.Date();// �õ���ǰϵͳʱ��
		return currentTime;
	}

	public static String getCurrentStringToday() {
		String crrentdate = "";
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date currentTime = new java.util.Date();// �õ���ǰϵͳʱ��
		crrentdate = formatter.format(currentTime); // ������ʱ���ʽ��
		return crrentdate;
	}

	/**
	 * ���������ʾ��ʽ
	 * 
	 * @param date
	 * @return
	 */
	public static String getStrWithArea(Date date) {
		DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
		return format.format(date);
	}

	/**
	 * ȡ�ÿͻ��˵�ʱ��
	 * 
	 * @param timeZone_digital
	 * @return
	 */
	public static String getGMTTimeZone(String timeZone) {
		String gmt = "";
		if (timeZone != null && StringUtils.isNotBlank(timeZone)) {
			int length = timeZone.length();
			String f = "";
			if (length == 1) {
				gmt = "GMT";
			} else if (timeZone.startsWith("-")) {
				// ������Է��ſ�ͷ��GMT��+��
				f = timeZone.substring(1, length);
				gmt = "GMT+" + Integer.parseInt(f) / 60;
			} else {
				gmt = "GMT-" + Integer.parseInt(timeZone) / 60;
			}
		}
		return gmt;
	}

	/**
	 * ������ת��Ϊ�ͻ�������ʱ����ʱ��
	 * 
	 * @param value
	 * @param gmtTimeZone
	 * @return
	 */
	public static String changeTimeZoneDate(Date value, String timeZone, SimpleDateFormat format) {
		// log.info(" timeZone of browser is " + timeZone);
		if (value == null) {
			return null;
		}
		if (StringUtils.isBlank(timeZone)) {
			format.setTimeZone(TimeZone.getDefault());
		} else {
			format.setTimeZone(TimeZone.getTimeZone(getGMTTimeZone(timeZone)));
		}
		return format.format(value);
	}

	/**
	 * ��������ת��������
	 * 
	 * @return
	 */
	public static String changeMillSecond2Date(long millSeconds) {
		Date temp = new Date(millSeconds);
		return formatDateMinutes(temp);
	}

	public static String changeMillSecond2Date(long millSeconds, String pattern) {
		Date temp = new Date(millSeconds);
		return formatDateMinutes(temp, pattern);
	}

	public static String dateFormat(String str) {
		String resultDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = simpleDateFormat.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String cutDate = String.valueOf(System.currentTimeMillis() - date.getTime());
		String tmpDate = cutDate.substring(0, cutDate.length() - 3);
		int intDate = Integer.parseInt(tmpDate);
		if (intDate < 60) {
			resultDate = tmpDate + "��ǰ";
		}
		if (intDate == 60) {
			resultDate = "1����ǰ";
		}
		if (intDate > 60 && intDate < 3600) {

			resultDate = String.valueOf(intDate / 60) + "����ǰ";
		}
		if (intDate >= 3600 && intDate < 86400) {

			resultDate = String.valueOf(intDate / 3600) + "Сʱǰ";

		}
		if (intDate > 86400 && intDate < 864000) {

			resultDate = String.valueOf(intDate / 86400) + "��ǰ";

		}
		if (intDate >= 864000) {
			SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			resultDate = simpleDateFormat2.format(date);

		}

		return resultDate;
	}

	public static int getCurrentTime() {
		return Integer.valueOf(String.valueOf(java.util.Calendar.getInstance().getTimeInMillis()).substring(0, 10));
	}

	public static int convertStringDateToInt(String str, String pattern) {
		return convertDateToInt(convertStringToDate(str, pattern));
	}

	public static int convertDateToInt(Date d) {
		long time = d.getTime() / 1000;
		return (int) time;
	}

	public static Date convertStringToDate(String str, String pattern) {
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(pattern);
		} catch (Exception ex) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		try {
			return sdf.parse(str);
		} catch (Exception e) {
		}
		return null;
	}

	public static String convertIntToDate(int date) {
		SimpleDateFormat sdf = new SimpleDateFormat("M-d HH:mm");

		Long x = Long.valueOf(date) * 1000;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(x);
		return sdf.format(c.getTime());
	}

	public static Date convertInt2Date(int date) {
		Long x = Long.valueOf(date) * 1000;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(x);
		return c.getTime();
	}

	public static String convertIntToDatePattern(int date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Long x = Long.valueOf(date) * 1000;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(x);
		return sdf.format(c.getTime());
	}

	public static String convertIntToDatePattern(int date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy��MM��dd��  HH:mm");
		Long x = Long.valueOf(date) * 1000;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(x);
		return dateFormat.format(c.getTime());
	}

	public static String convertIntToDatePattern2(int date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long x = Long.valueOf(date) * 1000;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(x);
		return dateFormat.format(c.getTime());
	}

	public static String convertIntToDate(int date, String language) {
		SimpleDateFormat sdf = new SimpleDateFormat(language);
		Long x = Long.valueOf(date) * 1000;
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(x);
		return sdf.format(c.getTime());
	}

	public static String convertToString(Object o) {
		if (o instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format((Date) o);
		}
		return "";
	}

	public static Date getDateByString(String str, String pattern) {
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(pattern);
		} catch (Exception ex) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		try {
			return sdf.parse(str);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * ��������
	 * 
	 * @return
	 */
	public static Date getCurrentDay() {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * ���ڵ�Сʱ
	 * 
	 * @return
	 */
	public static Date getCurrentHour() {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * ���µ�һ��
	 * 
	 * @return
	 */
	public static Date getCurrentMonth() {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public static Date getYesterday() {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_MONTH, -1);

		return cal.getTime();
	}
}
