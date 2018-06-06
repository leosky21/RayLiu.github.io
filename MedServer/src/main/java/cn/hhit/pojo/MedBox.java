package cn.hhit.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MedBox entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "medBox", catalog = "MedBoxDB")

public class MedBox implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer MId;

	// Constructors

	/** default constructor */
	public MedBox() {
	}

	/** full constructor */
	public MedBox(Integer MId) {
		this.MId = MId;
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

	@Column(name = "m_id")

	public Integer getMId() {
		return this.MId;
	}

	public void setMId(Integer MId) {
		this.MId = MId;
	}

}