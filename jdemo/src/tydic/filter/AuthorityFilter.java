package tydic.filter;

import javax.servlet.ServletException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import java.io.IOException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpSession;
/*
@WebFilter(filterName="authority",urlPatterns={"/*"}
	,initParams={
			@WebInitParam(name="encoding",value="GBK"),
			@WebInitParam(name="loginPage",value="/login.jsp")
	 }
)*/
public class AuthorityFilter implements Filter {
	
	//FilterConfig读取配置信息
	private FilterConfig config;
	//实现初始化方法
	public void init(FilterConfig config) {
		this.config = config;
	}
	//实现销毁方法
	public void destroy() {
		this.config = null;
	}
	
	//执行核心过滤
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException,ServletException {
		
		//获取Filter的配置参数
		String encoding = config.getInitParameter("encoding");
		String loginPage = config.getInitParameter("loginPage");
		
		//设置字符集
		request.setCharacterEncoding(encoding);
		HttpServletRequest hsrequest = (HttpServletRequest)request;
		HttpSession session = hsrequest.getSession(true);
		
		//获取用户请求的页面
		String requestPath = hsrequest.getServletPath();
		
		//如果session范围的user为null，并且请求的URL不是以login.jsp结尾，则表示没有登录
		//if(session.getAttribute("user")==null && !requestPath.endsWith(loginPage)) {
		if(false){
			System.out.println("AuthorityFilter-->您还没有登录");
			//forward到登录界面
			request.setAttribute("err","您还没有登录");
			request.getRequestDispatcher(loginPage).forward(request,response);
		} else {
			//放行请求
				System.out.println("AuthorityFilter.doFilter()....放行请求");
				chain.doFilter(request,response);
			}
		System.out.println("AuthorityFilter.doFilter()....过滤结束");
	}
}
