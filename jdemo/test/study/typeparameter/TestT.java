/**
 * @author taowei20061122@163.com 
 * @createtime 2015年2月3日
 * @version V1.0
 */
package study.typeparameter;

public class TestT {
	//<T> 类型参数
	public static <T> T foo(T t) {
		return t;
	}

	static class P {
		private String name;
		private int age;
	}
	public static <T> void say(T t) {
		System.out.println(t);
		if (t instanceof String) {
			System.out.println("a string --> " + t.toString());
		} else if (t instanceof P) {
//			System.out.println(t.getClass().getClassLoader());
			System.out.println(t.getClass().getClassLoader().getClass().getSimpleName());
			System.out.println(t.getClass().getClassLoader().getClass().getName());
		}
	}
	
	public static void main(String[] args) {

		String string1 = foo("taowei1");
		String string2 = foo("taowei2");
		Integer integer = foo(100);
		System.out.println(string1);
		System.out.println(string2);
		System.out.println(integer);
		
		say(new String("Hello World!"));
		say(new P());
	}

}
