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
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "shop")
public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1045193283905619711L;
	private Integer uid;
	private String unick;//登录名
	private String uname;//用户真实姓名
	private String upass;//密码
	private String usex;
	private String uphone;//手机号码
	private String uemail;
	private Set<Forder> forders = new HashSet<Forder>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String unick, String uname, String upass, String usex, String uphone, String uemail,
			Set<Forder> forders) {
		this.unick = unick;
		this.uname = uname;
		this.upass = upass;
		this.usex = usex;
		this.uphone = uphone;
		this.uemail = uemail;
		this.forders = forders;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "uid", unique = true, nullable = false)

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "unick", length = 20)

	public String getUnick() {
		return this.unick;
	}

	public void setUnick(String unick) {
		this.unick = unick;
	}

	@Column(name = "uname", length = 20)

	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Column(name = "upass", length = 20)

	public String getUpass() {
		return this.upass;
	}

	public void setUpass(String upass) {
		this.upass = upass;
	}

	@Column(name = "usex", length = 20)

	public String getUsex() {
		return this.usex;
	}

	public void setUsex(String usex) {
		this.usex = usex;
	}

	@Column(name = "uphone", length = 20)

	public String getUphone() {
		return this.uphone;
	}

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	@Column(name = "uemail", length = 20)

	public String getUemail() {
		return this.uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")

	public Set<Forder> getForders() {
		return this.forders;
	}

	public void setForders(Set<Forder> forders) {
		this.forders = forders;
	}

}