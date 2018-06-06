package cn.hhit.eshop.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */
//http://blog.csdn.net/li396864285/article/details/72961546
@JsonIgnoreProperties(value={"categories"})
public class Account implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String login;
	private String name;
	private String pass;
	private Set<Category> categories = new HashSet(0);

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** full constructor */
	public Account(String login, String name, String pass, Set categories) {
		this.login = login;
		this.name = name;
		this.pass = pass;
		this.categories = categories;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "account")  
	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set categories) {
		this.categories = categories;
	}

}