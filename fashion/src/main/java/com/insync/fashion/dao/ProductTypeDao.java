package com.insync.fashion.dao;

import java.util.List;



import com.insync.fashion.model.ProductType;

public interface ProductTypeDao {
	public long createProductType(ProductType type);
	public ProductType updateProductType(ProductType type);
	public void deleteProductType(long id);
	public List<ProductType> getAllProductType();
	public ProductType getProductType(long id);
    
    
    // for add
    public boolean isnameConflict(ProductType type);
    
    
    // for edit
    public boolean editnameConflict(ProductType type);
    
}
