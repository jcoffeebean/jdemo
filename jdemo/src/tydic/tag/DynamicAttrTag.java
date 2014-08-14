package tydic.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
/*
 *JSP 2
 */
public class DynamicAttrTag extends SimpleTagSupport implements DynamicAttributes {
	//保存每个属性名的集合
	private ArrayList<String> keys = new ArrayList<String>();
	//保存每个属性值的集合
	private ArrayList<Object> values = new ArrayList<Object>();
	
	public void doTag() throws JspException,IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<ol>");
		//此处只是简单的输出每个属性
		for(int i=0;i<keys.size();i++) {
			String key = keys.get(i);
			Object value = values.get(i);
			out.println("<li>"+key+"="+value+"</li>");
		}	
		out.println("</ol>");
	}
	
	public void setDynamicAttribute(String uri,String localName,Object value) throws JspException {
		keys.add(localName);
		values.add(value);
	}
}
