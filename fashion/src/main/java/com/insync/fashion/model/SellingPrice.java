package com.insync.fashion.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sellingPrice")
public class SellingPrice implements Serializable {
    
	private static final long serialVersionUID = -7988799579036225137L;
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	    private int productId;//productId
	    private int unit;//unit
	    private String price;//Price
	    
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
		public int getUnit() {
			return unit;
		}
		public void setUnit(int unit) {
			this.unit = unit;
		}
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
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
