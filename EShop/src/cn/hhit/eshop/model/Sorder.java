package cn.hhit.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 购物项
 * Sorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sorder", catalog = "shop")
public class Sorder implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sid;
	private Forder forder;
	private Product product;
	private String sname;
	private double sprice;
	private Integer snumber;

	// Constructors

	/** default constructor */
	public Sorder() {
	}

	/** minimal constructor */
	public Sorder(Integer snumber) {
		this.snumber = snumber;
	}

	/** full constructor */
	public Sorder(Forder forder, Product product, String sname, double sprice, Integer snumber) {
		this.forder = forder;
		this.product = product;
		this.sname = sname;
		this.sprice = sprice;
		this.snumber = snumber;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "sid", unique = true, nullable = false)

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fid")
	public Forder getForder() {
		return this.forder;
	}

	public void setForder(Forder forder) {
		this.forder = forder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "sname", length = 20)
	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	@Column(name = "sprice", precision = 8)
	public double getSprice() {
		return this.sprice;
	}

	public void setSprice(double sprice) {
		this.sprice = sprice;
	}

	@Column(name = "snumber", nullable = false)
	public Integer getSnumber() {
		return this.snumber;
	}

	public void setSnumber(Integer snumber) {
		this.snumber = snumber;
	}

}