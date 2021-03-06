package com.insync.fashion.dao;

import java.util.List;
import com.insync.fashion.model.CostPrice;

public interface CostPriceDao {
	public long createCostPrice(CostPrice price);
	public CostPrice updateCostPrice(CostPrice price);
	public void deleteCostPrice(long id);
	public List<CostPrice> getAllCostPrice();
	public CostPrice getCostPrice(long id);
    
    
    // for add
    public boolean isnameConflict(CostPrice price);
    
    
    // for edit
    public boolean editnameConflict(CostPrice price);
    
}
