package cn.hhit.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MedBoxMini entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "medBoxMini", catalog = "MedBoxDB")

public class MedBoxMini implements java.io.Serializable {

	// Fields

	private Integer id;
	private String a;
	private Integer ta;
	private Integer da;
	private Integer statusA;
	private Integer time;
	private Integer day;
	private Integer hour;
	private Integer min;

	// Constructors

	/** default constructor */
	public MedBoxMini() {
	}

	/** full constructor */
	public MedBoxMini(String a, Integer ta, Integer da, Integer statusA, Integer time, Integer day, Integer hour,
			Integer min) {
		this.a = a;
		this.ta = ta;
		this.da = da;
		this.statusA = statusA;
		this.time = time;
		this.day = day;
		this.hour = hour;
		this.min = min;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "A", length = 25)

	public String getA() {
		return this.a;
	}

	public void setA(String a) {
		this.a = a;
	}

	@Column(name = "TA")

	public Integer getTa() {
		return this.ta;
	}

	public void setTa(Integer ta) {
		this.ta = ta;
	}

	@Column(name = "DA")

	public Integer getDa() {
		return this.da;
	}

	public void setDa(Integer da) {
		this.da = da;
	}

	@Column(name = "statusA")

	public Integer getStatusA() {
		return this.statusA;
	}

	public void setStatusA(Integer statusA) {
		this.statusA = statusA;
	}

	@Column(name = "time")

	public Integer getTime() {
		return this.time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	@Column(name = "day")

	public Integer getDay() {
		return this.day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	@Column(name = "hour")

	public Integer getHour() {
		return this.hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	@Column(name = "min")

	public Integer getMin() {
		return this.min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

}