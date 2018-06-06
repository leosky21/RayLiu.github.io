package cn.hhit.eshop.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Status entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "status", catalog = "shop")

public class Status implements java.io.Serializable {

	// Fields

	private Integer sid;
	private String sstatus;
	private Set<Forder> forders = new HashSet<Forder>(0);

	// Constructors

	/** default constructor */
	public Status() {
	}
	public Status(Integer sid) {
		super();
		this.sid = sid;
	}
	/** full constructor */
	public Status(String sstatus, Set<Forder> forders) {
		this.sstatus = sstatus;
		this.forders = forders;
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

	@Column(name = "sstatus", length = 10)

	public String getSstatus() {
		return this.sstatus;
	}

	public void setSstatus(String sstatus) {
		this.sstatus = sstatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "status")

	public Set<Forder> getForders() {
		return this.forders;
	}

	public void setForders(Set<Forder> forders) {
		this.forders = forders;
	}

}