package cn.ray.beans;

public class QueryInfo {
	private int currentpage = 1; // 用户当前看的页
    private int pagesize = 5; // 记住用户想看的页面大小
    private int startindex; // 记住用户看的页的数据在数据库表中的起始位置
    
	/**
	 * @return the currentpage
	 */
	public int getCurrentpage() {
		return currentpage;
	}
	/**
	 * @return the pagesize
	 */
	public int getPagesize() {
		return pagesize;
	}
	/**
	 * @return the startindex
	 */
	public int getStartindex() {
		return startindex;
	}
	/**
	 * @param currentpage the currentpage to set
	 */
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	/**
	 * @param pagesize the pagesize to set
	 */
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	/**
	 * @param startindex the startindex to set
	 */
	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}
	
    
}
