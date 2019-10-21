package com.org.blogs.util;

import java.util.HashMap;
import java.util.Map;



/**
 * 分页所需要的对象
 * 
 * @author Administrator
 * 
 */
public class PageRoll {

	/* 当前页数 */
	private int pageNo = 0;
	/* 每页记录数 默认10条 */
	private int pageSize = 10;
	/* 总记录数 */
	private int totalRows = 0;
	// 总页数
	private int totalPage;

	// 起始行
	private int offset = 0;

	private Map<String, Object> params = new HashMap<String, Object>();// 其他的参数我们把它分装成一个Map对象

	/**
	 * 查询的实体对象
	 */
	private Object searchDto = null;

	// 查询返回的 结果集
	private Object content;

	public PageRoll() {
	}

	/**
	 * 设置查询对象 查询对象必需是FrmSearch 的子类
	 * 
	 * @param obj
	 * @throws Exception
	 */
	/*public <T extends FrmSearch> void setSearchDto(T obj) {
		this.searchDto = obj;
	}
*/
	/**
	 * 获取 查询对象 需要转换为自己查询对象
	 * 
	 * @return
	 */
	public Object getSearchDto() {
		return searchDto;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 起始行的方式获取当前页码 10 / 10 + 1 主要针对mysql数据库
	 * 
	 * @return
	 */
	public int getPageNoByOffset() {
		return offset / pageSize + 1;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		// 在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
		int totalPage = totalRows % pageSize == 0 ? totalRows / pageSize : totalRows / pageSize + 1;
		this.setTotalPage(totalPage);
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	/**
	 * 获取 结束记录位置
	 * 
	 * @return
	 */
	/*
	 * public int getRowNum() { return getStartRow() + pageSize; }
	 */

	/**
	 * 获取开始记录数
	 * 
	 * @return
	 */
	public int getStartRow() {
		return pageSize * (pageNo - 1);
	}

	/**
	 * 获取开始记录数 由前台转入的
	 * 
	 * @return
	 */
	public int getStartRowByOff() {
		return getOffset();
	}

	/**
	 * 获取上一页
	 * 
	 * @return
	 */
	public int getPrevPage() {
		return this.pageNo > 1 ? this.pageNo - 1 : 1;
	}

	/**
	 * 获取下一页
	 * 
	 * @return
	 */
	public int getNextPage() {
		return this.pageNo < getTotalPage() ? this.pageNo + 1 : getTotalPage();
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNo=").append(pageNo).append(", pageSize=").append(pageSize).append(",totalPage=").append(totalPage).append(", totalRows=").append(totalRows).append("]");
		return builder.toString();
	}

}
