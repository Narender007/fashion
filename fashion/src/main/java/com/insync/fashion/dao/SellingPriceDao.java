package com.insync.fashion.dao;

import java.util.List;



import com.insync.fashion.model.SellingPrice;

public interface SellingPriceDao {
	public long createSellingPrice(SellingPrice price);
	public SellingPrice updateSellingPrice(SellingPrice price);
	public void deleteSellingPrice(long id);
	public List<SellingPrice> getAllSellingPrice();
	public SellingPrice getSellingPrice(long id);
    
    
    // for add
    public boolean isnameConflict(SellingPrice price);
    
    
    // for edit
    public boolean editnameConflict(SellingPrice price);
    
}
