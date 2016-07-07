package com.insync.fashion.dao;

import java.util.List;

import com.insync.fashion.model.StoreAndWarehouse;

public interface StoreAndWarehouseDao {
	public long createStore(StoreAndWarehouse sw);
	public StoreAndWarehouse updateStore(StoreAndWarehouse sw);
	public void deleteStore(long id);
	public List<StoreAndWarehouse> getAllStore();
	public StoreAndWarehouse getStore(long id);
    
    
    // for add
    public boolean isnameConflict(StoreAndWarehouse sw);
    
    
    // for edit
    public boolean editnameConflict(StoreAndWarehouse sw);
}
