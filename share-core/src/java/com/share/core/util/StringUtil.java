package com.share.core.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;
import com.share.core.util.Check;
import com.share.core.util.SystemUtil;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * 字符串工具类
 * 
 * @author ruan 2013-1-24
 * 
 */
public final class StringUtil {
	private final static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	private final static Pattern filterHTMLPattern = Pattern.compile("<!?\\/?[a-zA-Z]+[^><]*>");
	private final static Pattern nbspPattern = Pattern.compile("&nbsp;");

	/**
	 * 私有构造函数
	 * 
	 * @author ruan 2013-1-24
	 */
	private StringUtil() {
	}

	/**
	 * getInt
	 * 
	 * @param str
	 * @return
	 */
	public final static int getInt(String str) {
		str = getString(str);
		if (!Check.isNumber(str)) {
			return 0;
		}
		if (str.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(str.split("\\.")[0]);
	}

	/**
	 * getInt
	 * 
	 * @param str
	 * @return
	 */
	public final static int getInt(Object obj) {
		if (obj == null) {
			return 0;
		}
		return getInt(obj.toString().trim());
	}

	/**
	 * getByte
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static byte getByte(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return 0;
		}
		if (!Check.isNumber(str)) {
			return 0;
		}
		return Byte.parseByte(str.split("\\.")[0]);
	}

	/**
	 * getByte
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static byte getByte(Object obj) {
		if (obj == null) {
			return 0;
		}
		return getByte(obj.toString().trim());
	}

	/**
	 * getShort
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static short getShort(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return 0;
		}
		if (!Check.isNumber(str)) {
			return 0;
		}
		return Short.parseShort(str.split("\\.")[0]);
	}

	/**
	 * getShort
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static short getShort(Object obj) {
		if (obj == null) {
			return 0;
		}
		return getShort(obj.toString().trim());
	}

	/**
	 * getLong
	 * 
	 * @param str
	 * @return
	 */
	public final static long getLong(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return 0;
		}
		if (!Check.isNumber(str)) {
			return 0;
		}
		return Long.parseLong(str.split("\\.")[0]);
	}

	/**
	 * getLong
	 * 
	 * @param str 
	 * @return
	 */
	public final static long getLong(Object obj) {
		if (obj == null) {
			return 0;
		}
		return getLong(obj.toString().trim());
	}

	/**
	 * getDouble
	 * 
	 * @param str
	 * @return
	 */
	public final static double getDouble(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return 0;
		}
		if (!Check.isNumber(str)) {
			return 0;
		}
		return Double.parseDouble(str);
	}

	/**
	 * getDouble
	 * 
	 * @param str
	 * @return
	 */
	public final static double getDouble(Object obj) {
		if (obj == null) {
			return 0;
		}
		return getDouble(obj.toString().trim());
	}

	/**
	 * getFloat
	 * 
	 * @param str
	 * @return
	 */
	public final static float getFloat(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return 0;
		}
		if (!Check.isNumber(str)) {
			return 0;
		}
		return Float.parseFloat(str);
	}

	/**
	 * getFloat
	 * 
	 * @param str
	 * @return
	 */
	public final static float getFloat(Object obj) {
		if (obj == null) {
			return 0;
		}
		return getFloat(obj.toString().trim());
	}

	/**
	 * getBoolean
	 * 
	 * @param str
	 * @return
	 */
	public final static boolean getBoolean(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return false;
		}
		return Boolean.valueOf(str);
	}

	/**
	 * getBoolean
	 * 
	 * @param str
	 * @return
	 */
	public final static boolean getBoolean(Object obj) {
		if (obj == null) {
			return false;
		}
		return getBoolean(obj.toString().trim());
	}

	/**
	 * getString
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static String getString(String str) {
		return str == null ? "" : str.trim();
	}

	/**
	 * getString
	 * 
	 * @author ruan
	 * @param str
	 * @return
	 */
	public final static String getString(Object obj) {
		if (obj == null) {
			return "";
		}
		return getString(obj.toString());
	}

	/**
	 * getString
	 * @param data
	 * @return
	 */
	public final static String getString(byte[] data) {
		if (data == null || data.length <= 0) {
			return "";
		}
		return getString(new String(data));
	}

	/**
	 * 过滤所有html标签
	 * 
	 * @param str
	 * @return
	 */
	public final static String filterHTML(String str) {
		str = getString(str);
		str = filterHTMLPattern.matcher(str).replaceAll("");
		str = nbspPattern.matcher(str).replaceAll("");
		return getString(str);
	}

	/**
	 * 计算子字符串在父字符串出现的次数
	 * @author ruan
	 * @param subject
	 * @param search
	 * @return
	 */
	public final static int strCount(String subject, String search) {
		String[] arr = subject.toLowerCase().split(search.toLowerCase());
		return arr.length > 0 ? arr.length - 1 : 0;
	}

	/**
	 * urlEncode
	 * @author ruan 
	 * @param str
	 */
	public final static String urlEncode(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return "";
		}
		try {
			return URLEncoder.encode(str, SystemUtil.getSystemCharsetString());
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}

	/**
	 * urlDecode
	 * @author ruan 
	 * @param str
	 */
	public final static String urlDecode(String str) {
		str = getString(str);
		if (str.isEmpty()) {
			return "";
		}
		try {
			return URLDecoder.decode(str, SystemUtil.getSystemCharsetString());
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}

	/**
	 * 截取定长开头字段,剩下的用省略号表示(str.length()<=length不处理)
	 * @param str
	 * @param length 截取的长度
	 * @return
	 */
	public final static String cutLengthEllipses(String str, int length) {
		int strLength = str.length();
		if (strLength <= length) {
			return str;
		}
		return str.substring(0, length) + "...";
	}

	/**
	 * 把字符串转成阿斯科码
	 * @param str 字符串
	 */
	public static int toPinyinOrder(String str) {
		int asciiValue = 0;
		try {
			// 输出格式
			HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
			hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
			hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);

			// 分解成拼音
			str = str.toUpperCase();
			StringBuilder zhongWenPinYin = new StringBuilder();
			char[] inputArray = str.toCharArray();
			for (int i = 0; i < inputArray.length; i++) {
				String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(inputArray[i], hanyuPinyinOutputFormat);
				if (pinYin != null) {
					zhongWenPinYin.append(pinYin[0]);
				} else {
					zhongWenPinYin.append(inputArray[i]);
				}
			}

			// 取前4个
			char[] charArray = zhongWenPinYin.toString().toCharArray();
			if (charArray == null || charArray.length <= 0) {
				return 0;
			}
			int ascii_1 = (int) charArray[0];
			int ascii_2 = charArray.length <= 1 ? 0 : (int) charArray[1];
			int ascii_3 = charArray.length <= 2 ? 0 : charArray[2];
			int ascii_4 = charArray.length <= 3 ? 0 : charArray[3];
			asciiValue = ascii_1 * 1000000 + ascii_2 * 10000 + ascii_3 * 100 + ascii_4;

			// 如果首字符不是字母，最终值除以1000000
			if (ascii_1 < 65 || ascii_1 > 90) {
				asciiValue = -asciiValue;
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return asciiValue;
	}

	/**
	 * 字符串转拼音 
	 * @param string 字符串
	 * @param caseType 大小写
	 */
	public final static String stringToPinyin(String string, HanyuPinyinCaseType caseType) {
		try {
			HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
			hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);

			StringBuilder zhongWenPinYin = new StringBuilder();
			char[] inputArray = string.toCharArray();
			for (int i = 0; i < inputArray.length; i++) {
				String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(inputArray[i], hanyuPinyinOutputFormat);
				if (pinYin != null && pinYin.length > 0) {
					zhongWenPinYin.append(pinYin[0]);
				} else {
					zhongWenPinYin.append(inputArray[i]);
				}
			}
			String result = zhongWenPinYin.toString();
			if (HanyuPinyinCaseType.LOWERCASE.equals(caseType)) {
				result = result.toLowerCase();
			} else {
				result = result.toUpperCase();
			}
			return result.replaceAll("\\s+", "");
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}

	/**
	 * 首字母大写
	 * @param str 字符串
	 */
	public final static String firstUpperCase(String str) {
		str = getString(str);
		return getString(str).substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 转成驼峰式
	 * @param str 字符串
	 */
	public final static String toCamelCase(String str) {
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, getString(str));
	}

	/**O
	 * 转成下划线
	 * @param str 字符串
	 */
	public final static String toSnakeCase(String str) {
		return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, getString(str));
	}
}