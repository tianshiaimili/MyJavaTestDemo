package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberRegex {

	public static void main(String[] args) {
		// 匹配是否全数字
		String reg = "^\\d+$";
		String str = "124567989522";
		System.out.println("--------"+str.matches(reg));
		// /////////////////
		String s = "123456";
		String regex = "[0-9]+?";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(s);
		if (!m.matches() == true) {
			System.out.println("字符串包含字符");
		} else {
			System.out.println("字符串不包含字符");
		}
	}

}
