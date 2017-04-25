package com.zxgzero.remotememory.Util.toolutil;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	/**
	 * 判断字符串是否为null或""空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		boolean result = false;
		if (null == str || "".equals(str.trim())) {
			result = true;
		}
		return result;
	}
	/**
	 * 去掉字符串中的所有的单个空格" "
	 * 
	 * @param string
	 */
	public static String replaceSpaceCharacter(String string) {
		if (null == string || "".equals(string)) {
			return "";
		}
		return string.replace(" ", "");
	}

	/**
	 * 获取小数位为6位的经纬度
	 * 
	 * @param string
	 * @return
	 */
	public static String getStringLongitudeOrLatitude(String string) {
		if (StringUtil.isNullOrEmpty(string)) {
			return "";
		}
		if (string.contains(".")) {
			String[] splitArray = string.split("\\.");
			if (splitArray[1].length() > 6) {
				String substring = splitArray[1].substring(0, 6);
				return splitArray[0] + "." + substring;
			} else {
				return string;
			}
		} else {
			return string;
		}
	}

	/**
	 * @description 获取字符串的字符个数（包含中英文，一个中文算2个字符）
	 * @param content
	 * @return
	 */

	public static int getCharacterNum(final String content) {
		if (null == content || "".equals(content)) {
			return 0;
		} else {
			return (content.length() + getChineseNum(content));
		}
	}

	/**
	 * @description 返回字符串里中文字或者全角字符的个数
	 * @param s
	 * @return
	 */

	public static int getChineseNum(String s) {
		int num = 0;
		char[] myChar = s.toCharArray();
		for (int i = 0; i < myChar.length; i++) {
			if ((char) (byte) myChar[i] != myChar[i]) {
				num++;
			}
		}
		return num;
	}
	
	/**
	 * 使用特定符号拼接String集合
	 * @return
	 */
	public static String listToString(List<String> stringlist,String sep){
		if(stringlist==null){
			return null;
		}
		StringBuilder result=new StringBuilder();
		boolean flag=false;
		for(String string:stringlist){
			if(flag){
				result.append(sep);
			}else{
				flag=true;
			}
			result.append(string);
		}
		return result.toString();
	}
	
	/**
	 * 传入拼接后的字符串，根据分割符，还原为String集合
	 * @param string
	 * @param sep
	 * @return
	 */
	public static List<String> stringToList(String string,String sep){
		if(string.length()==0){
			return null;
		}
		String[] strs=string.split(sep);
		List<String> strings=new ArrayList<String>();
		if(strs.length-1 >= 0){
			for(String str:strs){
				strings.add(str);
			}
		}
		return strings;
	}
}
