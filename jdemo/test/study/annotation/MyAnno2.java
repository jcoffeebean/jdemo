/**
 * @author taowei20061122@163.com 
 * @createtime 2015年2月2日
 * @version V1.0
 */
package study.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnno2 {
	
	// 为注解定义一个方法即为注解定义了一个元素，返回的默认值为hell world  
	public String name() default "taowei";
	
	public String address() default "SZ 123#";
}
