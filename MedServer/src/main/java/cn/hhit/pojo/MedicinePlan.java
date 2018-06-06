package cn.hhit.pojo;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * MedicinePlan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MedicinePlan", catalog = "MedBoxDB")

public class MedicinePlan implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	private MedicinePlanId id;
	private Medicine medicine;
	private User user;

	// Constructors

	/** default constructor */
	public MedicinePlan() {
	}

	/** full constructor */
	public MedicinePlan(MedicinePlanId id, Medicine medicine, User user) {
		this.id = id;
		this.medicine = medicine;
		this.user = user;
	}

	// Property accessors
	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "phone", column = @Column(name = "phone", nullable = false, length = 11)),
			@AttributeOverride(name = "license", column = @Column(name = "license", nullable = false, length = 18)),
			@AttributeOverride(name = "times", column = @Column(name = "times", nullable = false)),
			@AttributeOverride(name = "medicineTime", column = @Column(name = "medicine_time", nullable = false, length = 19)),
			@AttributeOverride(name = "num", column = @Column(name = "num", nullable = false)) })

	public MedicinePlanId getId() {
		return this.id;
	}

	public void setId(MedicinePlanId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "license", nullable = false, insertable = false, updatable = false)

	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "phone", nullable = false, insertable = false, updatable = false)

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}