package kr.or.ddit.common.model;

public class Page {
	private int page;
	private int pageSize;
	
	public Page() {
	}
	
	public Page(int page, int pagesize) {
		this.page = page;
		this.pageSize = pagesize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	@Override
	public String toString() {
		return "Page [page=" + page + ", pageSize=" + pageSize + "]";
	}
	
	
	
	
}
