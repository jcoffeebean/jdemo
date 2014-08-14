package tydic.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {
	
	//向application范围添加属性时触发该方法
	public void attributeAdded(ServletContextAttributeEvent event) {
		ServletContext application = event.getServletContext();
		String name = event.getName();
		Object value = event.getValue();
		System.out.println(application+"范围内添加属性："+name+"="+value);
		
	}
	
	//application范围删除属性时触发该方法
	public void attributeRemoved(ServletContextAttributeEvent event) {
		ServletContext application = event.getServletContext();
		String name = event.getName();
		Object value = event.getValue();
		System.out.println(application+"范围内删除属性："+name+"="+value);
	}
	//application范围替换属性时触发该方法
	public void attributeReplaced(ServletContextAttributeEvent event) {
		ServletContext application = event.getServletContext();
		String name = event.getName();
		Object value = event.getValue();
		System.out.println(application+"范围内替换属性"+name+"="+value);
	}
}
