/**
 * @文件 HelloWorldController.java 2013-10-13
 */
package study.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import study.entity.QQ;

/**
 * 
 * @author taowei20061122@163.com 2013-10-13
 */
@Controller  //或者 @RequestMapping   //1.将一个普通的POJO类声明为处理器
public class HelloWorldController {
	
	public static Log logger = LogFactory.getLog(HelloWorldController.class);
	/**
	 * 进入控制器首先执行标有注解@ModelAttribute的方法，
	 * 有多个的时候依次执行，最后根据URL进入功能处理方法
	 * @return
	 */
	@ModelAttribute(value="nameList") // 不写value="nameList"，则默认为名为：stringList
	public List<String> getNameList() {
		return Arrays.asList("深圳","广州");
	}
	
	
	@ModelAttribute(value="nameList2") // 不写value="nameList2"，则默认为名为：stringList
	public List<String> getNameList2() {
		return Arrays.asList("中山","厦门");
	}
	
	@ModelAttribute // 默认为名为：stringList
	public List<String> getNameList3() {
		return Arrays.asList("佛山","北京");
	}
	
	@ModelAttribute // 没有返回值呢？
	public void getNameList4() {
		Arrays.asList("佛山","北京");
		logger.info("Test--");
	}
	
	@ModelAttribute(value = "nameList5") // 没有返回值呢？
	public void getNameList5() {
		Arrays.asList("佛山","北京");
		logger.info("Test--");
	}
	
	@RequestMapping(value="/hello")  //请求URL（/hello，如果@Controller处有value值，则此处的URL前面还要加上此value）到处理器的功能处理方法的映射
	public ModelAndView helloWorld(){
		
		ModelAndView mv = new ModelAndView();
		//添加模型数据，可以是任意的POJO对象
		mv.addObject("msg", "Hello World!!");
		
		//设置逻辑视图名，视图解析器会根据该名字解析到具体的页面
//		mv.setViewName("hi.jsp");
		mv.setViewName("hello.jsp");
		mv.addObject("viewName", mv.getViewName());
		
		return mv;  //包含模型数据和逻辑视图名
	}
	
	@RequestMapping(value="/service/{id}")
	public @ResponseBody Map service(@PathVariable String id) { //@ResponseBody 表示服务返回json格式的数据
		Map data = new HashMap();
		if (id != null) {
			//你的业务逻辑处理
			
			//处理结果放到集合里面返回给调用的客户端
			data.put("id", id);
			data.put("message", "哈哈哈哈哈哈哈哈哈哈");
		}
		logger.info(data);
		return data;
	}
	
	@RequestMapping(value="/jsonp/{id}")
	public @ResponseBody String jsonpService(@PathVariable String id, String callback) { //@ResponseBody 表示服务返回json格式的数据
		StringBuffer sb = new StringBuffer(callback != null ? callback : "cllBack").append("(");
		if (id != null) {
			//你的业务逻辑处理
			
			//处理结果放到集合里面返回给调用的客户端
			sb.append("{\"id\":\"").append(id).append("\"}");
		}
		sb.append(")");
		logger.info(sb.toString());
		return sb.toString();
	}
	
	@RequestMapping(value="/jsonp2/{id}")
	public @ResponseBody String jsonpService2(@PathVariable String id, String callback) { //@ResponseBody 表示服务返回json格式的数据
		StringBuffer sb = new StringBuffer(callback != null ? callback : "cllBack").append("(");
		if (id != null) {
			//你的业务逻辑处理
			
			//处理结果放到集合里面返回给调用的客户端
			sb.append("{\"id\":\"").append(id).append("\"}");
		}
		sb.append(");");
		sb.append("function ").append(callback != null ? callback : "cllBack").append("(o) {var id = o.id; alert(id);}");
		logger.info(sb.toString());
		return sb.toString();
	}
	
	@RequestMapping(value="/f")  //请求URL（/jsp）到处理器的功能处理方法的映射 //
	public ModelAndView forward(HttpServletRequest request){
		String username = request.getParameter("username");
		System.out.println("username = " + username);
		ModelAndView mv = new ModelAndView();
		//添加模型数据，可以是任意的POJO对象
		mv.addObject("msg", "forward服务器内部跳转，对客户端透明，客户端URL地址不变");
		
		//设置逻辑视图名，视图解析器会根据该名字解析到具体的页面
//		mv.setViewName("hi.jsp");
		mv.setViewName("7/jsp-forward.jsp");
		mv.addObject("viewName", mv.getViewName());
		
		return mv;  //包含模型数据和逻辑视图名
	}
	
	@RequestMapping(value="xml")
	public HttpEntity<byte[]> getXml(ModelMap map, HttpServletResponse response) {
		
		QQ qq = new QQ();
		qq.setQq("1237896985");
		qq.setSfzh("421281198858898789");
		qq.setXm("ZHW");
	    String xml = qq.toString();
	    logger.info(xml);
	    byte[] documentBody = xml.getBytes();

	    HttpHeaders header = new HttpHeaders();
	    header.setContentType(new MediaType("application", "xml"));
	    header.setContentLength(documentBody.length);
	    return new HttpEntity<byte[]>(documentBody, header);
	}
	
	/**
	 *requestmapping如果采用uritemplate后如何识别具体的URL对应哪个uritemplate及提取url中的变量名值的API_备忘
	 * @return
	 */
	@RequestMapping(value="u/{id}/name/{address}")
	public @ResponseBody Map testUriTemplate(HttpServletRequest request) {
		Map dataMap = new HashMap();
		String pattern = (String) request
				.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		dataMap.put("1", "url is[" + request.getServletPath() + "] match uritemplate is [" + pattern + "]");
		logger.info("url is[" + request.getServletPath() + "] match uritemplate is [" + pattern + "]");
		Map<String, String> pathVariables = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		logger.info("变量个数" + pathVariables.keySet().size());
		int index = 1;
		for (Iterator<String> iterator = pathVariables.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			logger.info(key + "=>" + pathVariables.get(key));
			dataMap.put("param_" + index++, key + "=" + pathVariables.get(key));
		}
		
		
		//测试日志级别
		logger.debug("+++++++++debug+++++++++");
		logger.info("+++++++++info+++++++++");
		logger.warn("+++++++++warn+++++++++");
		logger.error("+++++++++error+++++++++");
		return dataMap;
	}
}
