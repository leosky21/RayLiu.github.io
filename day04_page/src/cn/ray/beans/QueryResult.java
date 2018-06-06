package cn.ray.beans;

import java.util.List;

public class QueryResult {
	private List list;//记住当前用户看到的页面的数据
	private int totalrecord;//记住总记录数
	
	
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
	
	
}
