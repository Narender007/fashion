package com.insync.fashion.dao;

import java.util.List;
import com.insync.fashion.model.Supplier;

public interface SupplierDao {
	public long createSupplier(Supplier supplier);
	public Supplier updateSupplier(Supplier supplier);
	public void deleteSupplier(long id);
	public List<Supplier> getAllSupplier();
	public Supplier getSupplier(long id);
    
    
    // for add
    public boolean isnameConflict(Supplier supplier);
    
    
    // for edit
    public boolean editnameConflict(Supplier supplier);
    
}
