package cn.hhit.eshop.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ForderBean {
	
	private Integer fid;
	private Status status;
	private User user;
	private String fname;
	private String fphone;
	private String fremark;
	private Date fdate;
	private double ftotal;
	private String fpost;
	private String faddress;
	private Set<SorderBean> sorders = new HashSet<SorderBean>(0);
	
	public ForderBean() {
		super();
	}
	public ForderBean(Integer fid, Status status, User user, String fname, String fphone, String fremark, Date fdate,
			double ftotal, String fpost, String faddress, Set<SorderBean> sorders) {
		super();
		this.fid = fid;
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
	
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFphone() {
		return fphone;
	}
	public void setFphone(String fphone) {
		this.fphone = fphone;
	}
	public String getFremark() {
		return fremark;
	}
	public void setFremark(String fremark) {
		this.fremark = fremark;
	}
	public Date getFdate() {
		return fdate;
	}
	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}
	public double getFtotal() {
		return ftotal;
	}
	public void setFtotal(double ftotal) {
		this.ftotal = ftotal;
	}
	public String getFpost() {
		return fpost;
	}
	public void setFpost(String fpost) {
		this.fpost = fpost;
	}
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	public Set<SorderBean> getSorders() {
		return sorders;
	}
	public void setSorders(Set<SorderBean> sorders) {
		this.sorders = sorders;
	}
}
