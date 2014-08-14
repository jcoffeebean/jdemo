package tydic.tag;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.util.List;

/*
JSP1.1 带有标签体的标签
*/
public class MyIteratorTag1 extends BodyTagSupport {
	
	//集合对象的元素
	private String item;
	
	//标签需要迭代的集合对象名
	private String bean;
	//
	private int i;
	private int size;
	private List<String> itemList;
	
	public void setBean(String s) {
		this.bean = s;
	} 
	
	public String getBean(){
		return bean;
	}
	
	public void setItem(String s) {
		item = s;
	}
	
	public String getItem() {
		return item;
	}
	
	//开始处理标签时调用该方法
	public int doStartTag() throws JspTagException {
		
		//从page范围内获取List对象
		itemList = (List<String>) pageContext.getAttribute(bean);
		//获取List的长度
		size = itemList.size();
		//将集合的当前索引的值放到page范围内item变量中
		pageContext.setAttribute(item,itemList.get(i));
		
		//返回EVAL_BODY_BUFFERED表明需要计算标签体
		return EVAL_BODY_BUFFERED;
		
	}	
	
	//每次标签体处理完后调用该方法
	public int doAfterBody() throws JspException {
		//移动list对象的索引位置
		i++;
		//如果索引超出了集合的长度就将索引回零
		if(i>=size) {
			//将索引回零
			i = 0;
			//不再计算标签体
			return SKIP_BODY;
		}
		//将集合的当前索引的值放到page范围内item变量中
		pageContext.setAttribute(item,itemList.get(i));
		//循环计算标签体
		return EVAL_BODY_AGAIN;
	}
	
	//标签体结束时调用该方法
	public int doEndTag() throws JspTagException {
		try {
			//输出标签体内容
			bodyContent.writeOut(pageContext.getOut());
		} catch(Exception e) {
				throw new JspTagException("Error");
		}
		
		return EVAL_PAGE;
	}
		
}
 
