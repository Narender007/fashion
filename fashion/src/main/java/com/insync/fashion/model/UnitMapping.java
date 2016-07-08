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
@Table(name="unitMapping")
public class UnitMapping implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4464840408350605965L;
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	    private String mappingDesc;
	    private int fromUnit;
	    private int toUnit;
	    private Double conversionFactor;
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
		public String getMappingDesc() {
			return mappingDesc;
		}
		public void setMappingDesc(String mappingDesc) {
			this.mappingDesc = mappingDesc;
		}
		public int getFromUnit() {
			return fromUnit;
		}
		public void setFromUnit(int fromUnit) {
			this.fromUnit = fromUnit;
		}
		public int getToUnit() {
			return toUnit;
		}
		public void setToUnit(int toUnit) {
			this.toUnit = toUnit;
		}
		public Double getConversionFactor() {
			return conversionFactor;
		}
		public void setConversionFactor(Double conversionFactor) {
			this.conversionFactor = conversionFactor;
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
