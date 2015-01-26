/**
 * @author taowei20061122@163.com 
 * @createtime 2014-8-12
 * @version V1.0
 */
package study;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class TestJsp {

	@Test
	public void test() {
		boolean flag = false;
		try {
			System.out.println("try 语句块执行 start");
			flag = true;
			if (flag) {//直接返回 finally始终是要执行
				return;
			}
			System.out.println("try 语句块执行 end");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			System.out.println("finally释放资源");
			String bString = "true";
			Boolean boolean1 = Boolean.valueOf(bString);
			Boolean boolean2 = Boolean.parseBoolean(bString);
			System.out.println(boolean1 + " " + boolean2);
			
//			另一方面，下面的代码不能工作：

			List<Integer> li = new ArrayList<Integer>();
			li.add(new Integer(42));
			List<?> lu = li;
//			lu.add(new Integer(43)); // error
			Number number = new Number() {
				
				@Override
				public long longValue() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public int intValue() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public float floatValue() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public double doubleValue() {
					// TODO Auto-generated method stub
					return 0;
				}
			};
		}
		
	}
}
