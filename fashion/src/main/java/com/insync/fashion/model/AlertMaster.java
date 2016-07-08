package com.insync.fashion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;

@Entity
@Table(name="alertMaster")
public class AlertMaster implements Serializable {
	private static final long serialVersionUID = -7988799579036225137L;
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	    private int productId;
	    private int placeId;
	    private int unit;
	    private Double yellow;
	    private Double red; 
	    private Double green;
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
		
	
		public int getProductId() {
			return productId;
		}
		public void setProductId(int productId) {
			this.productId = productId;
		}
		public int getPlaceId() {
			return placeId;
		}
		public void setPlaceId(int placeId) {
			this.placeId = placeId;
		}
		public int getUnit() {
			return unit;
		}
		public void setUnit(int unit) {
			this.unit = unit;
		}
		public Double getYellow() {
			return yellow;
		}
		public void setYellow(Double yellow) {
			this.yellow = yellow;
		}
		public Double getRed() {
			return red;
		}
		public void setRed(Double red) {
			this.red = red;
		}
		public Double getGreen() {
			return green;
		}
		public void setGreen(Double green) {
			this.green = green;
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
