package cn.hhit.pojo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MedicinePlanId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class MedicinePlanId implements java.io.Serializable {

	// Fields

	private String phone;
	private String license;
	private Integer times;
	private Timestamp medicineTime;
	private String num;

	// Constructors

	/** default constructor */
	public MedicinePlanId() {
	}

	/** full constructor */
	public MedicinePlanId(String phone, String license, Integer times, Timestamp medicineTime, String num) {
		this.phone = phone;
		this.license = license;
		this.times = times;
		this.medicineTime = medicineTime;
		this.num = num;
	}

	// Property accessors

	@Column(name = "phone", nullable = false, length = 11)

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "license", nullable = false, length = 18)

	public String getLicense() {
		return this.license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	@Column(name = "times", nullable = false)

	public Integer getTimes() {
		return this.times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	@Column(name = "medicine_time", nullable = false, length = 19)

	public Timestamp getMedicineTime() {
		return this.medicineTime;
	}

	public void setMedicineTime(Timestamp medicineTime) {
		this.medicineTime = medicineTime;
	}

	@Column(name = "num", nullable = false)

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MedicinePlanId))
			return false;
		MedicinePlanId castOther = (MedicinePlanId) other;

		return ((this.getPhone() == castOther.getPhone()) || (this.getPhone() != null && castOther.getPhone() != null
				&& this.getPhone().equals(castOther.getPhone())))
				&& ((this.getLicense() == castOther.getLicense()) || (this.getLicense() != null
						&& castOther.getLicense() != null && this.getLicense().equals(castOther.getLicense())))
				&& ((this.getTimes() == castOther.getTimes()) || (this.getTimes() != null
						&& castOther.getTimes() != null && this.getTimes().equals(castOther.getTimes())))
				&& ((this.getMedicineTime() == castOther.getMedicineTime())
						|| (this.getMedicineTime() != null && castOther.getMedicineTime() != null
								&& this.getMedicineTime().equals(castOther.getMedicineTime())))
				&& ((this.getNum() == castOther.getNum()) || (this.getNum() != null && castOther.getNum() != null
						&& this.getNum().equals(castOther.getNum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result + (getLicense() == null ? 0 : this.getLicense().hashCode());
		result = 37 * result + (getTimes() == null ? 0 : this.getTimes().hashCode());
		result = 37 * result + (getMedicineTime() == null ? 0 : this.getMedicineTime().hashCode());
		result = 37 * result + (getNum() == null ? 0 : this.getNum().hashCode());
		return result;
	}

}