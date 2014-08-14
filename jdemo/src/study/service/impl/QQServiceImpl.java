/**
 * @author taowei20061122@163.com 
 * @createtime 2013-10-23
 * @version V1.0
 */
package study.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import common.page.Page;
import util.PropertyUtils;

import study.entity.QQ;
import study.mapper.QQMapper;
import study.service.QQService;

@Service(value="qqService")
public class QQServiceImpl implements QQService {
	
	@Resource(name="qQMapper")
	private QQMapper qQMapper;

	@Override
	public List<QQ> getQQBySfzh(Object sfzh) {
		return qQMapper.getQQBySfzh(sfzh);
	}

	@Override
	public List<QQ> getList() {
		return qQMapper.getList();
	}
	
	@Override
	public List<QQ> getQQByXm(String xm) {
		return qQMapper.getQQByXm(xm);
	}

	@Override
	public Page queryByQQ(Page page) throws DataAccessException {
		Map filters = new HashMap();
		filters.put("sortColumns", page.getSortColumns());
		Map parameterObject = PropertyUtils.describe(page.getFilters());
		filters.putAll(parameterObject);
		//查询总数量
		int totalCount = qQMapper.queryByQQ_count(filters);
		page.setTotalCount(totalCount);
		if(totalCount == 0){
			return page;
		}
		//查询列表
		List list = qQMapper.queryByQQ(filters, new RowBounds(page.getFirstResult(), page.getPageSize()));
		page.setResult(list);
		return page;
	}
}
