package com.insync.fashion.service;

import java.util.List;

import com.insync.fashion.model.SupplierCatelog;

public interface SupplierCatelogService {
	public long createSupplierCatelog(SupplierCatelog catelog);
	public SupplierCatelog updateSupplierCatelog(SupplierCatelog catelog);
	public void deleteSupplierCatelog(long id);
	public List<SupplierCatelog> getAllSupplierCatelog();
	public SupplierCatelog getSupplierCatelog(long id);
    
    
    // for add
    public boolean isnameConflict(SupplierCatelog catelog);
    
    
    // for edit
    public boolean editnameConflict(SupplierCatelog catelog);
}
