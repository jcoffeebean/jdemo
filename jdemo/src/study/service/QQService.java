/**
 * @author taowei20061122@163.com 
 * @createtime 2013-10-23
 * @version V1.0
 */
package study.service;

import java.util.List;

import common.page.Page;

import study.entity.QQ;

public interface QQService {
	public List<QQ> getList();
	
	public Page<QQ> queryByQQ(Page<QQ> page);
	
	public List<QQ> getQQBySfzh(Object obj);
	
	public List<QQ> getQQByXm(String obj);
}
