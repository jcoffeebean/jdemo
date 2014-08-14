/**
 * @author taowei20061122@163.com 
 * @createtime 2013-10-23
 * @version V1.0
 */
package study.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.page.Page;

import study.entity.QQ;
import study.service.QQService;

@Controller
@RequestMapping(value="/qq")
public class QQController {
	
	@Resource(name="qqService")
	private QQService qqService;
	
	/**
	 * 1.功能方法参数中的modelMap、map和model为同一个对象，封装功能方法的其他参数page和qq;
	 * 2.请求URL中的参数与功能处理方法的参数绑定时会调用每一个对象的setter方法完成设值,例如sfzh=350426198003227019
	 *   则检查page和qq对象中是否有setSfzh()方法,有则请求参数值分别设置到这两个对象的相应属性中;
	 * 3.将值或者bean设置到ModelAndView的属性中,跳转到页面时可通过EL表达式在页面呈现,例如下面的mView.getModel().put("qqBean",qq)
	 *   则list.jsp页面可通过${qqBean.xm}获取到qq对象属性xm的值：本质是先通过key:qqBean获取到对象的引用qq,然后调用qq的getXm()方法取值;
	 */
	@RequestMapping(value="/li")
	public ModelAndView getQQ(Page<QQ> page, QQ qq, ModelMap modelMap, Map map, Model model) {//测试显示此处的modelMap、map、model为同一个对象，封装方法的参数page和qq
		System.out.println(modelMap==map); // true
		System.out.println(model==map);  // true
		ModelAndView mView = new ModelAndView("test/list.jsp");
		page.setFilters(qq);
		page = qqService.queryByQQ(page);
		mView.getModel().put("page",page);		
		mView.getModel().put("qqBean",qq);	
		System.out.println(mView.getModel()==modelMap); // false
		return mView;
	}
	
	@RequestMapping(value="getqq")
	public ModelAndView getQQ(ModelMap modelMap, String sfzh) {
		ModelAndView mView = new ModelAndView();
		Map parameterMap = new HashedMap();
		if (StringUtils.hasText(sfzh)) {
			parameterMap.put("sfzh", sfzh);
		}
		List<QQ> qq = qqService.getQQBySfzh(parameterMap); // 以Map或者bean封装参数
		return mView;
	}
	
	
	@RequestMapping(value="/xm")
	@ResponseBody public ModelAndView getQQByXm(ModelMap modelMap, String xm) {
		ModelAndView mView = new ModelAndView();
		List<QQ> qq = qqService.getQQByXm(xm);
		return null;
	}
	
	/**
	 * 1.Spring MVC自动绑定请求参数到功能处理方法的相应参数上;
	 * 2.如果功能处理方法的参数中有原始类型（例如int age）,则URL（http://localhost:8080/jdemo/test?age=9）中必须带有该参数否则抛出异常,如果允许为空值则用包装类代替,Boolean包装类默认值为FALSE,其他引用类型为NULL;
	 * 3.请求参数为role=admin,user则功能处理方法可以用数组接收;
	 * 4.如果在功能处理方法中定义ModelMap类型的参数则该方法的其他对象类型(java自定义的类型除外)的参数会被封装到ModelMap中,例如下面的qq对象会装填到modelMap中;
	 */
	@RequestMapping(value="/test")
	public ModelAndView testBinding(ModelMap modelMap, String xm, Integer sex, String[] role,QQ qq) {
		ModelAndView mView = new ModelAndView();
		return mView;
	}
}
