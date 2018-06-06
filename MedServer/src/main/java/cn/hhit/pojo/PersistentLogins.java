package cn.hhit.pojo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PersistentLogins entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "persistent_logins", catalog = "MedBoxDB")

public class PersistentLogins implements java.io.Serializable {

	// Fields

	private Integer id;
	private String uphone;
	private String unick;
	private String series;
	private String token;
	private Timestamp validTime;

	// Constructors

	/** default constructor */
	public PersistentLogins() {
	}

	/** minimal constructor */
	public PersistentLogins(String uphone, String unick) {
		this.uphone = uphone;
		this.unick = unick;
	}

	/** full constructor */
	public PersistentLogins(String uphone, String unick, String series, String token, Timestamp validTime) {
		this.uphone = uphone;
		this.unick = unick;
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

	@Column(name = "uphone", nullable = false, length = 11)

	public String getUphone() {
		return this.uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	@Column(name = "unick", nullable = false, length = 20)

	public String getUnick() {
		return this.unick;
	}

	public void setUnick(String unick) {
		this.unick = unick;
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

	public Timestamp getValidTime() {
		return this.validTime;
	}

	public void setValidTime(Timestamp validTime) {
		this.validTime = validTime;
	}

}