package com.insync.fashion.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



import java.io.Serializable;


@Entity
@Table(name="store_Warehouse")
public class StoreAndWarehouse implements Serializable {
	
	private static final long serialVersionUID = -7988799579036225137L;
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	@Column(name = "description", nullable = true, length = 100)
	private String description;
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private int flourCount;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	private int status;
	
	
	private String createdBy;
	private String modifiedBy;
	@Temporal (TemporalType.DATE)
	private java.util.Date createdDate;
	@Temporal (TemporalType.TIMESTAMP)
	private java.util.Date modifiedDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFlourCount() {
		return flourCount;
	}
	public void setFlourCount(int flourCount) {
		this.flourCount = flourCount;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public java.util.Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}
	public java.util.Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(java.util.Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
