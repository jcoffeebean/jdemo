/**
 * @author taowei20061122@163.com 
 * @createtime 2013-10-23
 * @version V1.0
 */
package study.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;


import study.entity.QQ;

@Repository(value="qQMapper")
public interface QQMapper {
	
	public List<QQ> getList();
	
	public List<QQ> queryByQQ(Object filters, RowBounds rowBounds);
	
	public int queryByQQ_count(Object filters);
	
	public List<QQ> getQQBySfzh(Object sfzh);
	
	public List<QQ> getQQByXm(String xm);
}
