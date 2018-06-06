package cn.hhit.eshop.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */
//http://blog.csdn.net/li396864285/article/details/72961546
//,"login"
@JsonIgnoreProperties(value={"products"})
public class Category implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@JsonIgnoreProperties(value={"pass"})
	private Account account;
	private String type;
	private Boolean hot;
	private Set<Product> products = new HashSet<Product>(0);

	// Constructors

	/** default constructor */
	public Category() {
	}
	public Category(Integer id) {
		this.id = id;
	}
	/** full constructor */
	public Category(Account account, String type, Boolean hot, Set products) {
		this.account = account;
		this.type = type;
		this.hot = hot;
		this.products = products;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="account_id")
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getHot() {
		return this.hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}