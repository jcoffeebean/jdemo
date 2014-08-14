package tydic.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import java.util.Date;


public class HiTag2 extends SimpleTagSupport {
	
	public void doTag() throws JspException,IOException {
		
		//获取页面输出流，并输出字符串
		getJspContext().getOut().print("Jsp_Tag_测试"+new Date());
		
	}
}
