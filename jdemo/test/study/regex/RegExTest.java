/**
 * @author taowei20061122@163.com 
 * @createtime 2015年1月26日
 * @version V1.0
 */
package study.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExTest {

	public static void main(String[] args) {
		// 按指定模式在字符串查找
		String line = "This order was placed for QT3000! OK?";
		String pattern = "(.*)(\\d+)(.*)";

		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		if (m.find()) {
			System.out.println("Found value: " + m.group(0));
			System.out.println("Found value: " + m.group(1));
			System.out.println("Found value: " + m.group(2));
		} else {
			System.out.println("NO MATCH");
		}
		System.out.println("m.groupCount() ==> " + m.groupCount());
		System.out.println("m.group() ==> " + m.group());

		//可以看到这个例子是使用单词边界，以确保字母 "c" "a" "t" 并非仅是一个较长的词的子串。它也提供了一些关于输入字符串中匹配发生位置的有用信息。 
		Pattern p = Pattern.compile(REGEX);
		Matcher m1 = p.matcher(INPUT); // 获取 matcher 对象
		int count = 0;

		while (m1.find()) {
			count++;
			System.out.println("Match number " + count);
			System.out.println("start(): " + m1.start());
			System.out.println("end(): " + m1.end());
		}
		
		
	}

	private static final String REGEX = "\\bcat\\b";
	private static final String INPUT = "cat cat cat cattie cat";
}
