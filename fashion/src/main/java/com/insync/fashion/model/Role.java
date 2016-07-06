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
@Table(name="role")
public class Role implements Serializable {
	private static final long serialVersionUID = -7988799579036225137L;
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	    private String roleName;
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
		
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
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
