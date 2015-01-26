/**
 * @author taowei20061122@163.com 
 * @createtime 2015年1月26日
 * @version V1.0
 */
package study.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {

	private static final String REGEX = "foo";
	private static final String INPUT = "fooooooooooooooooo";
	private static Pattern pattern;
	private static Matcher matcher;

	public static void main(String args[]) {
		// matches 和lookingAt
		// 方法都用来尝试匹配一个输入序列模式。它们的不同是matcher要求整个序列都匹配，而lookingAt 不要求
		pattern = Pattern.compile(REGEX);
		matcher = pattern.matcher(INPUT);

		System.out.println("Current REGEX is: " + REGEX);
		System.out.println("Current INPUT is: " + INPUT);

		System.out.println("lookingAt(): " + matcher.lookingAt());
		System.out.println("matches(): " + matcher.matches());

		// replaceFirst 和replaceAll 方法用来替换匹配正则表达式的文本。不同的是，replaceFirst
		// 替换首次匹配，replaceAll 替换所有匹配
		String regex = "dog";
		String input = "The dog says meow. " + "All dogs say meow.";
		String replace = "cat";

		Pattern p = Pattern.compile(regex);
		// get a matcher object
		Matcher m = p.matcher(input);
		input = m.replaceAll(replace);
		System.out.println(input);
		input = m.replaceFirst(replace);
		System.out.println(input);

		// Matcher 类也提供了appendReplacement 和appendTail 方法用于文本替换
		regex = "a*b";
		input = "aabfooaabfooabfoob";
		replace = "-";

		p = Pattern.compile(regex);
		// 获取 matcher 对象
		m = p.matcher(input);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, replace);
		}
		m.appendTail(sb);
		System.out.println(sb.toString());
	}

}
