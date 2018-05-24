package hibernate.training.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Laptop {
	@Id
	private String serialNumber;
	private String make;
	private String model;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	// join-column with unique, makes this one to one
	@JoinColumn(name = "owned_by", unique = true)
	private Employee ownedBy;

	public Laptop() {
	}

	public Laptop(String serialNumber, String make, String model) {
		this.serialNumber = serialNumber;
		this.make = make;
		this.model = model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Employee getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(Employee ownedBy) {
		this.ownedBy = ownedBy;
		this.ownedBy.setLaptop(this);
	}

}





