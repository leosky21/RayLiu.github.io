package cn.hhit.eshop.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PersistentLogins entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "persistent_logins", catalog = "shop")
public class PersistentLogins implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String uphone;
	private String series;
	private String token;
	private Date validTime;

	// Constructors

	/** default constructor */
	public PersistentLogins() {
	}

	/** minimal constructor */
	public PersistentLogins(String uphone) {
		this.uphone = uphone;
	}

	/** full constructor */
	public PersistentLogins(String uphone, String series, String token, Date validTime) {
		this.uphone = uphone;
		this.series = series;
		this.token = token;
		this.validTime = validTime;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "uphone", nullable = false, length = 50)

	public String getUphone() {
		return this.uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	@Column(name = "series", length = 300)

	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	@Column(name = "token", length = 500)

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "validTime", length = 19)

	public Date getValidTime() {
		return this.validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

}