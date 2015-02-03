/**
 * @author taowei20061122@163.com 
 * @createtime 2015年2月2日
 * @version V1.0
 */
package study;

import study.annotation.MyAnno2;
import study.annotation.MyAnnotation;
import study.annotation.ParseMyAnnotation;

public class TestAnnotation {
	@MyAnno2(name = "Filed-Name")
	private String name;
	
	@MyAnnotation(name = "zsf")
    public static void say(String name ) {  
        System.out.println("welcome: "+ name);  
    }
	
	@MyAnno2(address = "我住在下水径附加")
	public void getAddress(String dz) {
		System.out.println("My address: " + dz);
	}
	
    // 通过main来模拟注解的使用  
    public static void main(final String[] args) throws Exception {  
        final ParseMyAnnotation pm = new ParseMyAnnotation();  
        pm.parseMethod(TestAnnotation.class);  
        pm.parseMethod2(TestAnnotation.class);  
    }  
}
