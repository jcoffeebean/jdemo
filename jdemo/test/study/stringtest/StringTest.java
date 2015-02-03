/**
 * @author taowei20061122@163.com 
 * @createtime 2015年1月29日
 * @version V1.0
 */
package study.stringtest;


public class StringTest {

	public static void main(String[] args) {

		String string = "hello world!";
		
		String tmp0 = string.substring(0, 0);
		String tmp1 = string.substring(0, 1);
		System.out.println(tmp0+ " length:" + tmp0.length());
		System.out.println(tmp1 + " length:" + tmp1.length());
		System.out.println("".equals(tmp0));
		System.out.println(String.valueOf(33/2));
		System.out.println(String.valueOf(33/3));
		System.out.println(String.valueOf(33/4));
		System.out.println(String.valueOf(33/5));
	}


}
