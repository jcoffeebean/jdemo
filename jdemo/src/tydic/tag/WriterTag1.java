package tydic.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/*
 *JSP1.1 
 */
public class WriterTag1 extends TagSupport {
	//该标签从page里面查找item属性，并且输出其值
	private String item;
	
	public void setItem(String s) {
		item = s;
	}
	
	public String getItem() {
		return item;
	}
	
	//开始处理标签时调用该方法
	public int doStartTag() throws JspTagException {
		
		try {
			//从page范围内搜索item属性
			pageContext.getOut().write((String)pageContext.getAttribute(item));
		} catch(Exception e) {
				throw new JspTagException("Error");
		}
		//返回EVAL_PAGE继续计算JSP页面输出
		return EVAL_PAGE;
	}
}
