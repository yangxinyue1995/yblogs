package com.org.blogs.util;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class Pager {
	
	/**
	 * 总记录数必须初始化
	 * 每页查询多少条记录必须初始化
	 * 当前第几页必须初始化
	 * */
	
	/**总记录数*/
	private int totalRecord = 0;
	/**总记页数*/
	private int totalPage = 0;	
	/**当前页*/
	private int currentPage = 1;
	/**查询开始行*/
	private int  startIndex = 0;
	/**每页查询多少条记录*/
	private int   pageSize = 0;
	List<Field> fields = new ArrayList<Field>();
	
	
	
	public Pager(int totalRecord,int pageSize){
		init(totalRecord, pageSize);
	}
	
	private void init(int totalRecord,int pageSize){
		//初始化总记录
		this.totalRecord = totalRecord;
		//初始化每页的size
		this.pageSize = pageSize;
		//初始化总页数
		this.totalPage = getTotalPage();
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}


	/**根据总记录，和每页大小 得出总页数*/
	public int getTotalPage() {
		
		int totalPage = 0;
		if(this.totalRecord != 0 ){
			if(totalRecord % this.getPageSize()!=0){
				totalPage = this.totalRecord / this.getPageSize() + 1;
			}else{
				totalPage = this.totalRecord / this.getPageSize();
			}
		}else{
			totalPage = 0;
		}
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


    /**算出当前页*/
	public int getCurrentPage() {
		
		if(this.currentPage>=this.totalPage){
			this.currentPage = this.totalPage;
		}else if(this.currentPage <=0 ){
			this.currentPage = 1;
		}
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartIndex() {
		if(this.getCurrentPage()>0){
			this.startIndex = (this.getCurrentPage()-1)*this.getPageSize();
		}else{
			this.startIndex =0;
		}
		return this.startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


	/**不让pageSize为0*/
	public int getPageSize() {
		if(this.pageSize==0){
			this.pageSize = 100;
		}
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	


	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public static String pageServices(Pager pager,HttpServletRequest request) {
	    StringBuffer pageCoumnStr = new StringBuffer();
        StringBuffer requrl = new StringBuffer();
        requrl.append(request.getScheme() + "://");
        requrl.append(request.getHeader("host"));
        requrl.append(request.getRequestURI());
//        System.out.println(requrl.toString());
//        System.out.println("pageServices");
        if(pager.getTotalPage()==0){
//        	System.out.println("getTotalPage==0");
        	pageCoumnStr.append("当前没有记录");
        }else{
//        	System.out.println("getTotalPage!=0");
        	pageCoumnStr.append("<a href='"+requrl+"?toPage="+(1)+"'>首页</a>");
        	pageCoumnStr.append("<a href='"+requrl+"?toPage="+(pager.getCurrentPage()-1)+"'>上一页</a>");
        	pageCoumnStr.append(pager.getCurrentPage());
        	pageCoumnStr.append("<a href='"+requrl+"?toPage="+(pager.getCurrentPage()+1)+"'>下一页</a>");
        	pageCoumnStr.append("<a href='"+requrl+"?toPage="+(pager.getTotalPage())+"'>尾页</a>");
        	pageCoumnStr.append("共"+pager.getTotalPage()+"页");
//        	System.out.println(requrl);
//        	System.out.println(pageCoumnStr.toString());
        	
        }
        return pageCoumnStr.toString();
	}
	
}
