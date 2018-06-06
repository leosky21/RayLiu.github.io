package cn.hhit.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "MedBoxDB")
@JsonIgnoreProperties(value={"medicinePlans"})
public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4433357813254882665L;
	private String phone;
	private String nick;
	private String username;
	private String password;
	private String sex;
	private String height;
	private String weight;
	private String drugallergyhistory;
	private String medicalhistory;
	private String img;
	private Set<MedicinePlan> medicinePlans = new HashSet<MedicinePlan>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String phone, String nick, String username,String pass) {
		this.phone = phone;
		this.nick = nick;
		this.username = username;
		this.password = pass;
	}
	
	public User(String phone, String nick, String username, String password, String sex, String height, String weight,
			String drugallergyhistory, String medicalhistory, String img) {
		this.phone = phone;
		this.nick = nick;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.height = height;
		this.weight = weight;
		this.drugallergyhistory = drugallergyhistory;
		this.medicalhistory = medicalhistory;
		this.img = img;
	}

	/** full constructor */
	public User(String phone, String nick, String username, String password, String sex, String height, String weight,
			String drugallergyhistory, String medicalhistory, String img, Set<MedicinePlan> medicinePlans) {
		this.phone = phone;
		this.nick = nick;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.height = height;
		this.weight = weight;
		this.drugallergyhistory = drugallergyhistory;
		this.medicalhistory = medicalhistory;
		this.img = img;
		this.medicinePlans = medicinePlans;
	}

	// Property accessors
	@Id

	@Column(name = "phone", unique = true, nullable = false, length = 11)

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "nick", nullable = false, length = 20)

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	@Column(name = "username", nullable = false, length = 20)

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 20)

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "sex", length = 20)

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "height", length = 20)

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Column(name = "weight", length = 20)

	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "drugallergyhistory", length = 65535)

	public String getDrugallergyhistory() {
		return this.drugallergyhistory;
	}

	public void setDrugallergyhistory(String drugallergyhistory) {
		this.drugallergyhistory = drugallergyhistory;
	}

	@Column(name = "medicalhistory", length = 65535)

	public String getMedicalhistory() {
		return this.medicalhistory;
	}

	public void setMedicalhistory(String medicalhistory) {
		this.medicalhistory = medicalhistory;
	}

	@Column(name = "img")

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")

	public Set<MedicinePlan> getMedicinePlans() {
		return this.medicinePlans;
	}

	public void setMedicinePlans(Set<MedicinePlan> medicinePlans) {
		this.medicinePlans = medicinePlans;
	}

}