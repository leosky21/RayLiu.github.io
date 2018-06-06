package cn.ray.beans;

import java.util.List;

public class PageBean {
	private List list;
    private int totalrecord;
    private int pagesize;
    private int totalpage;
    private int currentpage;
    private int previouspage;
    private int nextpage;
    
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
		/*  101 页,每页20条,6页*/
		if(this.totalrecord % this.pagesize ==0){
			this.totalpage = this.totalrecord / this.pagesize;
		}else
			this.totalpage = (this.totalrecord / this.pagesize) +1;
		return totalpage;
	}

	/**
	 * @return the currentpage
	 */
	public int getCurrentpage() {
		return currentpage;
	}

	/**
	 * @return the previouspage
	 */
	public int getPreviouspage() {
		return this.previouspage<=1 ? 
					this.previouspage : this.previouspage-1;
	}

	/**
	 * @return the nextpage
	 */
	public int getNextpage() {
		return nextpage>=this.totalpage ? totalpage : nextpage+1 ;
	}

	/**
	 * @return the pagebar
	 */
	public int[] getPagebar() {
		int[] pagebar = null;
		int startpage;
		int endpage;
		if(this.totalpage<=10){
			pagebar = new int[this.totalpage];
			startpage=1;
			endpage = this.totalpage;
		}else{
			pagebar = new int[10];
			startpage=this.currentpage - 4;
			 endpage = this.currentpage + 5;
		}
				/*以防显示无根据*/
				if(startpage<1){
					startpage=1;
					endpage = 10;
				}
				if (endpage > this.totalpage) {
		            endpage = this.totalpage;
		            startpage = this.totalpage - 9;
		        }
		 int index = 0;
		 for (int i = startpage; i <= endpage; i++) {
			 pagebar[index++] = i;
       }
		 this.pagebar = pagebar;
		return this.pagebar;
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

	/**
	 * @param totalpage the totalpage to set
	 */
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
	 * @param previouspage the previouspage to set
	 */
//	public void setPreviouspage(int previouspage) {
//		this.previouspage = previouspage;
//	}

	/**
	 * @param nextpage the nextpage to set
	 */
//	public void setNextpage(int nextpage) {
//		this.nextpage = nextpage;
//	}

	/**
	 * @param pagebar the pagebar to set
	 */
//	public void setPagebar(int[] pagebar) {
//		this.pagebar = pagebar;
//	}

	

    
    
}
