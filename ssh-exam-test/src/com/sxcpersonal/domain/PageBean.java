package com.sxcpersonal.domain;

import java.util.List;

public class PageBean<T> {
	private int currPage;
	private int pageSize;
	private int totalCount;
	private int totalPage;
	private List<T> currContent;
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getCurrContent() {
		return currContent;
	}
	public void setCurrContent(List<T> currContent) {
		this.currContent = currContent;
	}
	
	
}
