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
 * Medicine entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "medicine", catalog = "MedBoxDB")
@JsonIgnoreProperties(value={"medicinePlans"})
public class Medicine implements java.io.Serializable {

	// Fields

	private String licensenumber;
	private String medicinename;
	private String activeingredient;
	private String medcharacter;
	private String dose;
	private String dosage;
	private String contraindication;
	private String indication;
	private String dosagefromdoc;
	private String untowardeffect;
	private String druginteraction;
	private String periodvalidity;
	private String manufacturer;
	private String storageconditions;
	private Set<MedicinePlan> medicinePlans = new HashSet<MedicinePlan>(0);

	// Constructors

	/** default constructor */
	public Medicine() {
	}

	/** minimal constructor */
	public Medicine(String licensenumber) {
		this.licensenumber = licensenumber;
	}

	/** full constructor */
	public Medicine(String licensenumber, String medicinename, String activeingredient, String medcharacter,
			String dose, String dosage, String contraindication, String indication, String dosagefromdoc,
			String untowardeffect, String druginteraction, String periodvalidity, String manufacturer,
			String storageconditions, Set<MedicinePlan> medicinePlans) {
		this.licensenumber = licensenumber;
		this.medicinename = medicinename;
		this.activeingredient = activeingredient;
		this.medcharacter = medcharacter;
		this.dose = dose;
		this.dosage = dosage;
		this.contraindication = contraindication;
		this.indication = indication;
		this.dosagefromdoc = dosagefromdoc;
		this.untowardeffect = untowardeffect;
		this.druginteraction = druginteraction;
		this.periodvalidity = periodvalidity;
		this.manufacturer = manufacturer;
		this.storageconditions = storageconditions;
		this.medicinePlans = medicinePlans;
	}

	// Property accessors
	@Id

	@Column(name = "licensenumber", unique = true, nullable = false, length = 18)

	public String getLicensenumber() {
		return this.licensenumber;
	}

	public void setLicensenumber(String licensenumber) {
		this.licensenumber = licensenumber;
	}

	@Column(name = "medicinename", length = 50)

	public String getMedicinename() {
		return this.medicinename;
	}

	public void setMedicinename(String medicinename) {
		this.medicinename = medicinename;
	}

	@Column(name = "activeingredient", length = 65535)

	public String getActiveingredient() {
		return this.activeingredient;
	}

	public void setActiveingredient(String activeingredient) {
		this.activeingredient = activeingredient;
	}

	@Column(name = "medcharacter")

	public String getMedcharacter() {
		return this.medcharacter;
	}

	public void setMedcharacter(String medcharacter) {
		this.medcharacter = medcharacter;
	}

	@Column(name = "dose", length = 50)

	public String getDose() {
		return this.dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	@Column(name = "dosage")

	public String getDosage() {
		return this.dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	@Column(name = "contraindication")

	public String getContraindication() {
		return this.contraindication;
	}

	public void setContraindication(String contraindication) {
		this.contraindication = contraindication;
	}

	@Column(name = "indication", length = 65535)

	public String getIndication() {
		return this.indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}

	@Column(name = "dosagefromdoc")

	public String getDosagefromdoc() {
		return this.dosagefromdoc;
	}

	public void setDosagefromdoc(String dosagefromdoc) {
		this.dosagefromdoc = dosagefromdoc;
	}

	@Column(name = "untowardeffect")

	public String getUntowardeffect() {
		return this.untowardeffect;
	}

	public void setUntowardeffect(String untowardeffect) {
		this.untowardeffect = untowardeffect;
	}

	@Column(name = "druginteraction")

	public String getDruginteraction() {
		return this.druginteraction;
	}

	public void setDruginteraction(String druginteraction) {
		this.druginteraction = druginteraction;
	}

	@Column(name = "periodvalidity", length = 10)

	public String getPeriodvalidity() {
		return this.periodvalidity;
	}

	public void setPeriodvalidity(String periodvalidity) {
		this.periodvalidity = periodvalidity;
	}

	@Column(name = "manufacturer", length = 40)

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Column(name = "storageconditions", length = 30)

	public String getStorageconditions() {
		return this.storageconditions;
	}

	public void setStorageconditions(String storageconditions) {
		this.storageconditions = storageconditions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medicine")

	public Set<MedicinePlan> getMedicinePlans() {
		return this.medicinePlans;
	}

	public void setMedicinePlans(Set<MedicinePlan> medicinePlans) {
		this.medicinePlans = medicinePlans;
	}

}