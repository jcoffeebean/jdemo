/**
 * @author taowei20061122@163.com 
 * @createtime 2015年2月2日
 * @version V1.0
 */
package study.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ParseMyAnnotation {

	// 定义的注解可以在方法上也可以在类上，所以需要两个方法进行不同类型的处理
	// 处理定义在方法上的注解
	/**
	 * @param clazz
	 *            为注解所在的目标类
	 * @throws Exception
	 */
	public void parseMethod(final Class<?> clazz) throws Exception {
		// 生成目标类的一个实例
		final Object obj = clazz.getConstructor(new Class[] {}).newInstance(
				new Object[] {});
		// 得到目标类的方法集
		final Method[] methods = clazz.getDeclaredMethods();
		for (final Method method : methods) {
			// 获取方法上的注解，同时判断是否存在
			final MyAnnotation my = method.getAnnotation(MyAnnotation.class);
			if (null != my) {
				// 如果存在则将注解上的值传递个目标方法，并执行。
				method.invoke(obj, my.name());
			}
		}
	}
	
	public void parseMethod2(final Class<?> clazz) throws Exception {
		// 生成目标类的一个实例
		Object obj = clazz.getConstructor(new Class[] {}).newInstance(
				new Object[] {});
		// 得到目标类的方法集
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			// 获取方法上的注解，同时判断是否存在
			MyAnno2 my = method.getAnnotation(MyAnno2.class);
			if (null != my) {
				// 如果存在则将注解上的值传递个目标方法，并执行。
				method.invoke(obj, my.address());
			}
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			MyAnno2 myAnno2 = field.getAnnotation(MyAnno2.class);
			System.out.println(myAnno2.name());
		}
	}
}
