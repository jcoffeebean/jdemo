/**
 * @文件 SpringUtils.java 2013-9-18
 */
package util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author taowei20061122@163.com 2013-9-18
 */
public class SpringUtils {
	
	/** Spring容器，在DefaultServletContextListener中初始化 **/
	private static ApplicationContext ctx = null;
	
	/**
	 * 获取Spring容器的引用
	 */
	public static void init(ServletContext sc) {
		WebApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(sc);
		setCtx(app);
		
	}
	
	public static void setCtx(ApplicationContext context) {
		ctx = context;
	}
	
	/**
	 * 功能：获取Spring的业务bean
	 * @param bean
	 * @return
	 */
	public static Object getBean(String bean) {
		return ctx.getBean(bean);
	}
}
