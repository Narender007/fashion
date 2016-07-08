package com.insync.fashion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import java.io.Serializable;


@Entity
@Table(name="supplierAddress")
public class SupplierAddress implements Serializable {
	
	private static final long serialVersionUID = -7988799579036225137L;
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
    private long address_id;
	@Column(name = "addressline1", nullable = true, length = 70)
	private String addressline1;
	@Column(name = "addressline2", nullable = true, length = 70)
	private String addressline2;
	private String street;
	public long getAddress_id() {
		return address_id;
	}
	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}
	public String getAddressline1() {
		return addressline1;
	}
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	private String city;
	private String state;
	private String zipcode;
}
