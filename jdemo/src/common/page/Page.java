package common.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 分页信息
 * 第一页从1开始
 */
public class Page<T> implements Serializable,Iterable<T>
{
	
//	protected List<T> result = new ArrayList<T>();
	//提供属性的getter方法
	private List<T> result = new ArrayList<T>();

	protected int pageSize = 10;

	protected int pageNumber = 1;

	protected int totalCount = 0;
/*	private String sfzh; //测试SpringMVC的数据绑定
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}*/
	/**
	 * 过滤参数
	 */
	private Object filters;
	/**
	 * 排序的多个列,如: username desc
	 */
	private String sortColumns;	
	
	public Page() {
	}
	
	public Page(int pageSize) {
		this.pageSize = pageSize;
	}	
	
	public Page(int pageNumber,int pageSize,int totalCount) {
		this(pageNumber,pageSize,totalCount,new ArrayList(0));
	}
	
	public Page(int pageNumber,int pageSize,int totalCount,List<T> result) {
		if(pageSize <= 0) throw new IllegalArgumentException("[pageSize] must great than zero");
		this.pageSize = pageSize;
		this.pageNumber = PageUtils.computePageNumber(pageNumber,pageSize,totalCount);
		this.totalCount = totalCount;
		setResult(result);
	}

	public void setResult(List<T> elements) {
		if(elements == null) throw new IllegalArgumentException("'result' must be not null");
		this.result = elements;
	}
	
    /**
     * 当前页包含的数据
     *
     * @return 当前页数据源
     */
	public List<T> getResult() {
		return result;
	}
	
    public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
     * 是否是首页（第一页），第一页页码为1
     *
     * @return 首页标识
     */
	public boolean isFirstPage() {
		return getThisPageNumber() == 1;
	}

    /**
     * 是否是最后一页
     *
     * @return 末页标识
     */
	public boolean isLastPage() {
		return getThisPageNumber() >= getLastPageNumber();
	}
    /**
     * 是否有下一页
     *
     * @return 下一页标识
     */
	public boolean isHasNextPage() {
		return getLastPageNumber() > getThisPageNumber();
	}
    /**
     * 是否有上一页
     *
     * @return 上一页标识
     */
	public boolean isHasPreviousPage() {
		return getThisPageNumber() > 1;
	}
    /**
     * 获取最后一页页码，也就是总页数
     *
     * @return 最后一页页码
     */
	public int getLastPageNumber() {
		return PageUtils.computeLastPageNumber(totalCount, pageSize);
	}
    /**
     * 总的数据条目数量，0表示没有数据
     *
     * @return 总数量
     */
	public int getTotalCount() {
		return totalCount;
	}
    /**
     * 获取当前页的首条数据的行编码
     *
     * @return 当前页的首条数据的行编码
     */
	public int getThisPageFirstElementNumber() {
		return (getThisPageNumber() - 1) * getPageSize() + 1;
	}
    /**
     * 获取当前页的末条数据的行编码
     *
     * @return 当前页的末条数据的行编码
     */
	public int getThisPageLastElementNumber() {
		int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
		return getTotalCount() < fullPage ? getTotalCount() : fullPage;
	}
    /**
     * 获取下一页编码
     *
     * @return 下一页编码
     */
	public int getNextPageNumber() {
		return getThisPageNumber() + 1;
	}
    /**
     * 获取上一页编码
     *
     * @return 上一页编码
     */
	public int getPreviousPageNumber() {
		return getThisPageNumber() - 1;
	}
    /**
     * 每一页显示的条目数
     *
     * @return 每一页显示的条目数
     */
	public int getPageSize() {
		return pageSize;
	}
    /**
     * 当前页的页码
     *
     * @return 当前页的页码
     */
	public int getThisPageNumber() {
		return pageNumber;
	}

    /**
     * 得到用于多页跳转的页码
     * @return
     */
	public List<Integer> getLinkPageNumbers() {
		return PageUtils.generateLinkPageNumbers(getThisPageNumber(), getLastPageNumber(),10);
	}
	
	/**
	 * 得到数据库的第一条记录号
	 * @return
	 */
	public int getFirstResult() {
		return PageUtils.getFirstResult(pageNumber, pageSize);
	}

    public Iterator<T> iterator() {
        return result == null ? new ArrayList<T>().iterator() : result.iterator();
    }
    
	public Object getFilters() {
		if(filters == null){
			filters = new HashMap();
		}
		return filters;
	}

	public void setFilters(Object filters) {
		this.filters = filters;
	}
	
	public String getSortColumns() {
		return sortColumns;
	}	
	/**
	 * 排序的列,可以同时多列,使用逗号分隔,如 username desc,age asc
	 * @return
	 */
	public void setSortColumns(String sortColumns) {
		checkSortColumnsSqlInjection(sortColumns);
		if(sortColumns != null && sortColumns.length() > 50) {
			throw new IllegalArgumentException("sortColumns.length() <= 50 must be true");
		}
		this.sortColumns = sortColumns;
	}
	
	private void checkSortColumnsSqlInjection(String sortColumns) {
		if(sortColumns == null) return;
		if(sortColumns.indexOf("'") >= 0 || sortColumns.indexOf("\\") >= 0) {
			throw new IllegalArgumentException("sortColumns:"+sortColumns+" has SQL Injection risk");
		}
	}
	
}

