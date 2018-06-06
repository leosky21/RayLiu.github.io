package cn.hhit.eshop.model;

import java.io.Serializable;

public class SorderBean implements Serializable{
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sid;
	private ForderBean forder;
	private ProductTest product;
	private String sname;
	private double sprice;
	private Integer snumber;
	
	
	public SorderBean() {
		super();
	}
	public SorderBean(Integer sid, ForderBean forder, ProductTest product, String sname, double sprice, Integer snumber) {
		super();
		this.sid = sid;
		this.forder = forder;
		this.product = product;
		this.sname = sname;
		this.sprice = sprice;
		this.snumber = snumber;
	}
	
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public ForderBean getForder() {
		return forder;
	}
	public void setForder(ForderBean forder) {
		this.forder = forder;
	}
	public ProductTest getProduct() {
		return product;
	}
	public void setProduct(ProductTest product) {
		this.product = product;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public double getSprice() {
		return sprice;
	}
	public void setSprice(double sprice) {
		this.sprice = sprice;
	}
	public Integer getSnumber() {
		return snumber;
	}
	public void setSnumber(Integer snumber) {
		this.snumber = snumber;
	}


}
