package tydic.filter;

import javax.servlet.FilterChain;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;

public class LogFilter implements Filter {
	
	//FilterConfig用于访问Filter的配置信息
	private FilterConfig config;
	
	//实现初始化方法
	public void init(FilterConfig config) {
		this.config = config;
	}
	//实现销毁方法
	public void destroy() {
		this.config = null;
	}
	
	//执行过滤的核心方法
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException {
		//-----------------下面代码用于对用户请求进行预处理
		//获取ServletContext用于记录日志
		ServletContext context = this.config.getServletContext();
		long before = System.currentTimeMillis();
		
		//将请求转换成HttpServletRequset请求
		HttpServletRequest hrequest = (HttpServletRequest)request;
		//输出提示信息
		System.out.println("LogFilter.doFilter()开始过滤....已拦截到用户的请求地址："+hrequest.getServletPath());
		
		request.setCharacterEncoding("UTF-8");
		//Filter只是链式处理，请求依然放行到目的地址
		chain.doFilter(request,response);
		
		response.setCharacterEncoding("UTF-8");
		//------------------下面代码用于对服务器响应进行后处理
		long after = System.currentTimeMillis();		
		
		//测试，再次转换
		//HttpServletRequest hrequest2 = (HttpServletRequest)request;
		//System.out.println("过滤结束，请求被定位到："+hrequest2.getRequestURI()+" 用时："+(after-before));
		System.out.println("LogFilter.doFilter()过滤结束....用户请求的地址："+hrequest.getRequestURI());
	}
}
