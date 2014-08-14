package tydic.tag;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

/*
 *JSP 1.1
 */
public class HiTag extends TagSupport {
	
	public int doEndTag() throws JspTagException {
		try {
			//获取页面输出流，并输出字符串
			pageContext.getOut().print("Jsp_Tag_测试");
		} catch(IOException ex) {
			//抛出新异常
			throw new JspTagException("Error");
		}
		//return EVAL_PAGE;
		return SKIP_PAGE;//忽略hiTag.jsp页面第一个标签以后的内容
	}
	/*
	public int doStartTag() throws JspTagException {
		try {
			//获取页面输出流，并输出字符串
			pageContext.getOut().print("Jsp_Tag_测试");
		} catch(IOException ex) {
			//抛出新异常
			throw new JspTagException("Error");
		}
		return SKIP_BODY;//EVAL_BODY_INCLUDE
	}*/
}
