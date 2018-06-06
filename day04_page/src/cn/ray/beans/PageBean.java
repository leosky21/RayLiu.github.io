package cn.ray.beans;

import java.util.List;

public class PageBean {
	private List list;
	private int totalrecord;
	private int pagesize;
	private int totalpage;
	private int currentpage;
	private int nextpage;
	private int previouspage;
	
	private int[] pagebar;
	
	/**
	 * @return the list
	 */
	public List getList() {
		return list;
	}
	/**
	 * @return the totalrecord
	 */
	public int getTotalrecord() {
		return totalrecord;
	}
	/**
	 * @return the pagesize
	 */
	public int getPagesize() {
		return pagesize;
	}
	/**
	 * @return the totalpage
	 */
	public int getTotalpage() {
		
		int i = this.totalrecord % this.pagesize;
		if(i==0)//取余
			this.totalpage = i;
		else
			this.totalpage = (i+1);
		return totalpage;
	}
	/**
	 * @return the currentpage
	 */
	public int getCurrentpage() {
		return currentpage;
	}
	/**
	 * @return the nextpage
	 */
	public int getNextpage() {
		if(nextpage>=this.totalpage)
			this.nextpage = this.totalpage;
		else
			this.nextpage = this.currentpage+1;
		return nextpage;
	}
	/**
	 * @return the previouspage
	 */
	public int getPreviouspage() {
		if(previouspage<=1)
			this.previouspage = 1;
		else
			previouspage--;
		return previouspage;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List list) {
		this.list = list;
	}
	/**
	 * @param totalrecord the totalrecord to set
	 */
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}
	/**
	 * @param pagesize the pagesize to set
	 */
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
//	/**
//	 * @param totalpage the totalpage to set
//	 */
//	public void setTotalpage(int totalpage) {
//		this.totalpage = totalpage;
//	}
	/**
	 * @param currentpage the currentpage to set
	 */
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	/**
	 * @param nextpage the nextpage to set
	 */
	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}
//	/**
//	 * @param previouspage the previouspage to set
//	 */
//	public void setPreviouspage(int previouspage) {
//		this.previouspage = previouspage;
//	}
	/**
	 * @return the pagebar
	 */
	public int[] getPagebar() {
		 /*
        int[] pagebar = new int[this.totalpage];
        for (int i = 1; i <= this.totalpage; i++) {
            pagebar[i-1] = i;
        }
        this.pagebar = pagebar;
        return pagebar;
        */
		int[] pagebar = null;
		int startpage = 1;
		int endpage = this.totalpage;;
		if(this.totalpage<=5){
			pagebar = new int[this.totalpage];
		}else{
			pagebar = new int[5];
			startpage = this.currentpage-2;
			endpage = this.currentpage+2;
		}
		if(startpage <1){
			startpage =1;
			endpage = 5;
		}
		if(endpage>this.totalpage){
			endpage = this.totalpage;
			startpage = this.totalpage -4;
		}
		
		
		int index = 0;
		for(int i=startpage;i<=endpage;i++){
			pagebar[index++] = i;
		}
		this.pagebar = pagebar;
		return this.pagebar;
	}
//	/**
//	 * @param pagebar the pagebar to set
//	 */
//	public void setPagebar(int[] pagebar) {
//		this.pagebar = pagebar;
//	}
	
}
