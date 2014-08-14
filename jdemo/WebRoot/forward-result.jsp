<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
	<head><title>forward-result.jsp</title></head>
	<body>
	服务器内部跳转forward到forward-result.jsp页面</br></br>
	<!-- 使用request内置对象获取参数age的值 -->
	<p style="color:red;font-weight: bold;font-size:18px;">
		表单提交的数据可以用request.getParameter("key")获取；
		后台添加的Attribute，可以用request.getAttribute("key")获取；
	</p>
	request.getParameter("age") = <%=request.getParameter("age")%><br/>
	request.getParameter("name") = <%=request.getParameter("name")%><br/>
	request.getAttribute("getAttribute") = <%=request.getAttribute("viewName")%><br/>
	request.getParameter("username") = <%=request.getParameter("username")%><br/>
	FORWARD: request.getAttribute("key_out") = <%=request.getAttribute("key_out")%><br/>
	RESULT: out = <%=out.toString()%>
	<p style="color:red;font-weight: bold;font-size:18px;">使用下面这个指令就会报错: java.io.IOException: Stream closed</p>
	<p style="color:red;font-weight: bold;font-size:18px;">Stacktrace:] with root cause
java.io.IOException: Stream closed
	at org.apache.jasper.runtime.JspWriterImpl.ensureOpen(JspWriterImpl.java:210)
	at org.apache.jasper.runtime.JspWriterImpl.flushBuffer(JspWriterImpl.java:115)
	at org.apache.jasper.runtime.PageContextImpl.release(PageContextImpl.java:190)
	at org.apache.jasper.runtime.JspFactoryImpl.internalReleasePageContext(JspFactoryImpl.java:126)
	at org.apache.jasper.runtime.JspFactoryImpl.releasePageContext(JspFactoryImpl.java:80)
	at org.apache.jsp.pages._7.forward_002dresult_jsp._jspService(forward_002dresult_jsp.java:105)
	at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:728)
	at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:432)
	at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:390)
	at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:334)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:728)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:305)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:210)
	at org.apache.catalina.core.ApplicationDispatcher.invoke(ApplicationDispatcher.java:749)
	at org.apache.catalina.core.ApplicationDispatcher.processRequest(ApplicationDispatcher.java:487)
	at org.apache.catalina.core.ApplicationDispatcher.doForward(ApplicationDispatcher.java:412)
	at org.apache.catalina.core.ApplicationDispatcher.forward(ApplicationDispatcher.java:339)
	at org.apache.jasper.runtime.PageContextImpl.doForward(PageContextImpl.java:746)
	at org.apache.jasper.runtime.PageContextImpl.forward(PageContextImpl.java:716)
	at org.apache.jsp.pages._7.jsp_002dforward_jsp._jspService(jsp_002dforward_jsp.java:93)
	at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:728)
	at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:432)
	at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:390)
	at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:334)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:728)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:305)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:210)
	at org.apache.catalina.core.ApplicationDispatcher.invoke(ApplicationDispatcher.java:749)
	at org.apache.catalina.core.ApplicationDispatcher.processRequest(ApplicationDispatcher.java:487)
	at org.apache.catalina.core.ApplicationDispatcher.doForward(ApplicationDispatcher.java:412)
	at org.apache.catalina.core.ApplicationDispatcher.forward(ApplicationDispatcher.java:339)
	at org.springframework.web.servlet.view.InternalResourceView.renderMergedOutputModel(InternalResourceView.java:238)
	at org.springframework.web.servlet.view.AbstractView.render(AbstractView.java:262)
	at org.springframework.web.servlet.DispatcherServlet.render(DispatcherServlet.java:1180)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:950)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:852)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:882)
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:778)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:621)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:728)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:305)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:210)
	at tydic.filter.LogFilter.doFilter(LogFilter.java:41)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:243)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:210)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:222)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:123)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:472)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:171)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:99)
	at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:931)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:118)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:407)
	at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1004)
	at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:589)
	at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:310)
	at java.util.concurrent.ThreadPoolExecutor$Worker.runTask(ThreadPoolExecutor.java:886)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:908)
	at java.lang.Thread.run(Thread.java:662)
</p>
	<p style="color:red;font-weight: bold;font-size:18px;">
	解决办法：将静态的html文件form-commit.html改成form-commit.jsp
	</p>
	<jsp:include page="form-commit.jsp">
		<jsp:param value="tw_arci" name="username"/>
	</jsp:include>
	</body>
</html>