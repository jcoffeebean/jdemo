package tydic.tag;

import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.JspException;
import java.io.IOException;
import javax.servlet.jsp.JspWriter;


public class FragmentTag extends SimpleTagSupport {
	private JspFragment fragment;
	public void setFragment(JspFragment f) {
		fragment = f;
	}	
	public JspFragment getFragment() {
		return fragment;
	}
	public void doTag() throws JspException,IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<div style='padding:10px;border:1px solid black'");
		out.println("<h3>下面是动态传入的JSP片段</h3>");
		//调用，输出页面片段
		fragment.invoke(null);
		out.println("</div>");
	}
}
