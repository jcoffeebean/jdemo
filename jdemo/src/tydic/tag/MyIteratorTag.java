package tydic.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.List;

public class MyIteratorTag extends SimpleTagSupport {
	//标签需要迭代的集合对象名
	private String bean;
	//集合对象的元素名称
	private String item;
	
	//集合的当前索引
	private int i = 0;
	private int size;
	private List<String> itemList;
	
	public void setBean(String bean) {
		this.bean = bean;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getBean() {
		return bean;
	}
	public String getItem() {
		return item;
	}
	
	//简单标签处理类，只需要重写doTag()方法
	public void doTag() throws JspException,IOException {
		//从page scope中获取属性名为bean的集合
		List list = (List)getJspContext().getAttribute(bean);
		//遍历集合
		for(Object obj: list) {
			//将集合的元素设置到page范围内
			getJspContext().setAttribute(item,obj);
			//输出标签体，getJspBody()返回JspFragment
			getJspBody().invoke(null);			
		}
	}
}
