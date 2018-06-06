package cn.hhit.eshop.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Forder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "forder", catalog = "shop")

public class Forder implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer fid;
	private Status status;
	@JsonIgnoreProperties
	private User user;
	private String fname;
	private String fphone;
	private String fremark;
	private Date fdate;
	private double ftotal;
	private String fpost;
	private String faddress;
	private Set<Sorder> sorders = new HashSet<Sorder>(0);

	// Constructors

	/** default constructor */
	public Forder() {
	}

	/** minimal constructor */
	public Forder(Date fdate) {
		this.fdate = fdate;
	}

	/** full constructor */
	public Forder(Status status, User user, String fname, String fphone, String fremark, Date fdate, double ftotal,
			String fpost, String faddress, Set<Sorder> sorders) {
		this.status = status;
		this.user = user;
		this.fname = fname;
		this.fphone = fphone;
		this.fremark = fremark;
		this.fdate = fdate;
		this.ftotal = ftotal;
		this.fpost = fpost;
		this.faddress = faddress;
		this.sorders = sorders;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "fid", unique = true, nullable = false)

	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sid")

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "fname", length = 20)

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Column(name = "fphone", length = 20)

	public String getFphone() {
		return this.fphone;
	}

	public void setFphone(String fphone) {
		this.fphone = fphone;
	}

	@Column(name = "fremark", length = 20)

	public String getFremark() {
		return this.fremark;
	}

	public void setFremark(String fremark) {
		this.fremark = fremark;
	}

	@Column(name = "fdate", nullable = false, length = 19)

	public Date getFdate() {
		return this.fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}

	@Column(name = "ftotal", precision = 8)

	public double getFtotal() {
		return this.ftotal;
	}

	public void setFtotal(double ftotal) {
		this.ftotal = ftotal;
	}

	@Column(name = "fpost", length = 20)

	public String getFpost() {
		return this.fpost;
	}

	public void setFpost(String fpost) {
		this.fpost = fpost;
	}

	@Column(name = "faddress", length = 200)

	public String getFaddress() {
		return this.faddress;
	}

	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "forder")

	public Set<Sorder> getSorders() {
		return this.sorders;
	}

	public void setSorders(Set<Sorder> sorders) {
		this.sorders = sorders;
	}

}