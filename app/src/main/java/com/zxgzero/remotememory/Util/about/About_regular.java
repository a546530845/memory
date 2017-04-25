package com.zxgzero.remotememory.Util.about;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证
 * @author Zero
 *
 */
public class About_regular {
	private static int[] idsArray = new int[]{7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
	
	//验证手机号
    private static final String REGEX_MOBILE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
    //验证座机号,正确格式：xxx/xxxx-xxxxxxx/xxxxxxxx
    private static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";
    //验证邮箱
    private static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    //验证url
    private static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?";
    //验证汉字
    private static final String REGEX_CHZ = "^[\\u4e00-\\u9fa5]+$";
    //验证用户名,取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
    private static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
    //验证IP地址
    private static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    //If u want more please visit http://toutiao.com/i6231678548520731137/
 
    /**
     * @param string 待验证文本
     * @return 是否符合手机号格式
     */
    public static boolean isMobile(String string) {
        return isMatch(REGEX_MOBILE, string);
    }
 
    /**
     * @param string 待验证文本
     * @return 是否符合座机号码格式
     */
    public static boolean isTel(String string) {
        return isMatch(REGEX_TEL, string);
    }
 
    /**
     * @param string 待验证文本
     * @return 是否符合邮箱格式
     */
    public static boolean isEmail(String string) {
        return isMatch(REGEX_EMAIL, string);
    }
 
    /**
     * @param string 待验证文本
     * @return 是否符合网址格式
     */
    public static boolean isURL(String string) {
        return isMatch(REGEX_URL, string);
    }
 
    /**
     * @param string 待验证文本
     * @return 是否符合汉字
     */
    public static boolean isChz(String string) {
        return isMatch(REGEX_CHZ, string);
    }
 
    /**
     * @param string 待验证文本
     * @return 是否符合用户名
     */
    public static boolean isUsername(String string) {
        return isMatch(REGEX_USERNAME, string);
    }
 
    /**
     * @param regex  正则表达式字符串
     * @param string 要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    public static boolean isMatch(String regex, String string) {
        return !TextUtils.isEmpty(string) && Pattern.matches(regex, string);
    }
    /**
	 * 验证身份证的号码是否是格式正确的
	 * @param idCardNumber
	 * @return
	 */
	public static boolean isIDCardVerify(String idCardNumber){
		if(null == idCardNumber){
			return false;
		}
		
		if("".equals(idCardNumber.trim())){
			return false;
		}
		
		String idCardNumberTrim = idCardNumber.trim();
		String idPattern = "^((1[1-5])|(2[1-3])|(3[1-7])|(4[1-6])|(5[0-4])|(6[1-5])|71|(8[12])|91)\\d{4}((19\\d{2}(0[13-9]|1[012])(0[1-9]|[12]\\d|30))|(19\\d{2}(0[13578]|1[02])31)|(19\\d{2}02(0[1-9]|1\\d|2[0-8]))|(19([13579][26]|[2468][048]|0[48])0229))\\d{3}(\\d|X|x)?$";
		Pattern patternId = Pattern.compile(idPattern);//身份证号码
		Matcher matcherId = patternId.matcher(idCardNumberTrim);
		if (!matcherId.matches()) {
			return false;
		}
		
		//下面是验18位身份证的最后一位字符数字是否正确
		int temp  = 0;
		if(idCardNumberTrim.length() == 18){
			char[] idArray = idCardNumberTrim.toCharArray();
			
			for (int i = 0; i < idArray.length - 1; i++) {
				String valueOf = String.valueOf(idArray[i]);
				int parseInt = Integer.parseInt(valueOf);
				temp += parseInt * idsArray[i];
			}
			int temp2 = temp % 11;
			String lastChar  = "";
			switch (temp2) {
			case 0:
				lastChar = "1";
				break;
			case 1:
				lastChar = "0";
				break;
			case 2:
				lastChar = "X";
				break;
			case 3:
				lastChar = "9";
				break;
			case 4:
				lastChar = "8";
				break;
			case 5:
				lastChar = "7";
				break;
			case 6:
				lastChar = "6";
				break;
			case 7:
				lastChar = "5";
				break;
			case 8:
				lastChar = "4";
				break;
			case 9:
				lastChar = "3";
				break;
			case 10:
				lastChar = "2";
				break;
			}
			char charAtLast = idCardNumberTrim.charAt(17);
			if(!(""+charAtLast).equalsIgnoreCase(lastChar)){
				return false;
			}
		}
		//以上条件都不符合才会返回true
		return true;
	}
}
