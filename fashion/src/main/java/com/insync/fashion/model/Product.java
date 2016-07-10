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
@Table(name="product")
public class Product implements Serializable {
     
	private static final long serialVersionUID = -7988799579036225137L;
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	    private String name;
	    private String description;
	    private int productType;
	    private int productBran;
	    private int barcode;
	    private String imgUrl;
	    private String defaultUnit;
	    
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
		public int getProductType() {
			return productType;
		}
		public void setProductType(int productType) {
			this.productType = productType;
		}
		public int getProductBran() {
			return productBran;
		}
		public void setProductBran(int productBran) {
			this.productBran = productBran;
		}
		public int getBarcode() {
			return barcode;
		}
		public void setBarcode(int barcode) {
			this.barcode = barcode;
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
		public String getImgUrl() {
			return imgUrl;
		}
		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
		public String getDefaultUnit() {
			return defaultUnit;
		}
		public void setDefaultUnit(String defaultUnit) {
			this.defaultUnit = defaultUnit;
		}
		
		
	    
}
