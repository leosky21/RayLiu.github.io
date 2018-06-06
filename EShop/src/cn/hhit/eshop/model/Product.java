package cn.hhit.eshop.model;

import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
//http://blog.csdn.net/li396864285/article/details/72961546
//,"category","account"
//@JsonIgnoreProperties(value={"hibernateLazyInitializer"},ignoreUnknown = true)
public class Product implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@JsonIgnoreProperties(value={"account"})
	private Category category;
	private String name;
	private Double price;
	private Blob pic;
	private String remark;
	private String xremark;
	private Timestamp date;
	private Boolean commend;
	private Boolean open;

	// Constructors

	/** default constructor */
	public Product() {
	}
	/** default constructor */
	public Product(Integer id) {
		this.id = id;
	}
//	/** minimal constructor */
//	public Product(Timestamp date) {
//		this.date = date;
//	}

	/** full constructor */
	public Product(Category category, String name, Double price, Blob pic, String remark, String xremark,
			Timestamp date, Boolean commend, Boolean open) {
		this.category = category;
		this.name = name;
		this.price = price;
		this.pic = pic;
		this.remark = remark;
		this.xremark = xremark;
		this.date = date;
		this.commend = commend;
		this.open = open;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cid")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Blob getPic() {
		return this.pic;
	}

	public void setPic(Blob pic) {
		this.pic = pic;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getXremark() {
		return this.xremark;
	}

	public void setXremark(String xremark) {
		this.xremark = xremark;
	}

	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Boolean getCommend() {
		return this.commend;
	}

	public void setCommend(Boolean commend) {
		this.commend = commend;
	}

	public Boolean getOpen() {
		return this.open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

}