package com.shop.www.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSONObject;

public class StringUtils {
	private static final String NUM_S = "0123456789";

	private static final String STR_S = "abcdefghijklmnopqrstuvwxyz0123456789";

	public static boolean isEmpty(final String str) {
		return (str == null) || (str.length() == 0);
	}

	public static boolean isNotEmpty(final String str) {
		return !isEmpty(str);
	}

	public static boolean isBlank(final String str) {
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0))
			return true;
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(final String str) {
		return !isBlank(str);
	}

	public static String trimToNull(final String str) {
		String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	public static String trimToEmpty(final String str) {
		return str == null ? "" : str.trim();
	}

	public static String trim(final String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * 计算文字长度 无中文问题
	 * 
	 * @param @param string @param @return 设定文件 @return int 返回类型 @throws
	 */
	public static int getLength(final String string) {
		if (isBlank(string)) {
			return 0;
		} else {
			char[] strChars = string.toCharArray();
			return strChars.length;
		}
	}

	/**
	 * 将字符串中特定模式的字符转换成map中对应的值,
	 * 
	 * @param s
	 *            需要转换的字符串
	 * @param map
	 *            转换所需的键值对集合
	 * @return 转换后的字符串
	 */
	public static String replace(final String s, Map<String, String> map) {
		StringBuilder sb = new StringBuilder((int) (s.length() * 1.5));
		int cursor = 0;
		for (int start, end; (start = s.indexOf("${", cursor)) != -1 && (end = s.indexOf("}", start)) != -1;) {
			sb.append(s.substring(cursor, start));
			String key = s.substring(start + 2, end);
			sb.append(map.get(trim(key)));
			cursor = end + 1;
		}
		sb.append(s.substring(cursor, s.length()));
		return sb.toString();
	}

	/**
	 * 获取ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Requested-For");
		if (isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}


	/**
	 * 首字母变小写
	 */
	public static String firstCharToLowerCase(String str) {
		Character firstChar = str.charAt(0);
		String tail = str.substring(1);
		str = Character.toLowerCase(firstChar) + tail;
		return str;
	}

	/**
	 * 首字母变大写
	 */
	public static String firstCharToUpperCase(String str) {
		Character firstChar = str.charAt(0);
		String tail = str.substring(1);
		str = Character.toUpperCase(firstChar) + tail;
		return str;
	}

	/**
	 * 首字母大写其余小写
	 *
	 * @param str
	 */
	public static String upperFirstLowerOther(final String str) {
		if (isEmpty(str))
			return str;
		StringBuilder sb = new StringBuilder();
		char c = str.charAt(0);
		sb.append(Character.toUpperCase(c));
		String other = str.substring(1);
		sb.append(other.toLowerCase());
		return sb.toString();
	}

	/**
	 * 字符串不为 null 而且不为 "" 时返回 true
	 */
	public static boolean notBlank(final String str) {
		return str == null || "".equals(str.trim()) ? false : true;
	}

	public static boolean notBlank(final String... strings) {
		if (strings == null)
			return false;
		for (String str : strings)
			if (str == null || "".equals(str.trim()))
				return false;
		return true;
	}

	public static boolean notNull(Object... paras) {
		if (paras == null)
			return false;
		for (Object obj : paras)
			if (obj == null)
				return false;
		return true;
	}


	/**
	 * 生成一个10位的tonken用于http cache
	 * 
	 * @param @return 设定文件 @return String 返回类型 @throws
	 */
	public static String getTonken() {
		return RandomStringUtils.random(10, NUM_S);
	}

	/**
	 * 生成随机字符串
	 * 
	 * @author：rex
	 * @param count
	 * @return
	 */
	public static String randomStr(final int count) {
		return RandomStringUtils.random(count, STR_S);
	}

	/**
	 * 生成随机数
	 * 
	 * @author：rex
	 * @param count
	 * @return
	 */
	public static String randomNum(final int count) {
		return RandomStringUtils.randomNumeric(count);
	}

	/**
	 * 是否为正确的用户名
	 * 
	 * @param msg
	 * @return
	 */
	public static boolean isAlphaUnderline(final String msg) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
		Matcher matcher = pattern.matcher(msg);
		return matcher.matches();
	}
	
	public static JSONObject parseJson(String code, String msg, Object data){
        JSONObject jo = new JSONObject();
        //返回码，1表示成功，2表示失败
        jo.put("result", code);
        //中文提示
        jo.put("msg", msg);
        //返回数据
        jo.put("data", data);
        return jo;
    }
	public static void responseBuildJson(HttpServletResponse response, Object jo) throws Exception{
        String json = "";
        if(jo instanceof JSONObject){
            json = JsonUtils.json2Object(json, String.class);
        }else{
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                json = objectMapper.writeValueAsString(jo);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        //response.setContentType("text/plain");
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter writer;
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * 该方法返回一个字符的DBCS编码值
	 * 
	 * @param cc
	 * @return int
	 * @throws UnsupportedEncodingException
	 */
	private static int getCode(char cc) throws UnsupportedEncodingException {
		byte[] bs = String.valueOf(cc).getBytes("GBK");
		int code = (bs[0] << 8) | (bs[1] & 0x00FF);
		if (bs.length < 2)
			code = (int) cc;
		bs = null;
		return code;
	}

}
