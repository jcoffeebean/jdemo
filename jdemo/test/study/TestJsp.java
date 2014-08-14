/**
 * @author taowei20061122@163.com 
 * @createtime 2014-8-12
 * @version V1.0
 */
package study;

import org.junit.Test;


public class TestJsp {

	@Test
	public void test() {
		boolean flag = false;
		try {
			System.out.println("try 语句块执行 start");
			flag = true;
			if (flag) {
				return;
			}
			System.out.println("try 语句块执行 end");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println("finally释放资源");
		}
	}
}
