package com.insync.fashion.service;

import java.util.List;

import com.insync.fashion.model.ProductBrand;


public interface ProductBrandService {
	public long createProductBrand(ProductBrand brand);
	public ProductBrand updateProductBrand(ProductBrand brand);
	public void deleteProductBrand(long id);
	public List<ProductBrand> getAllProductBrand();
	public ProductBrand getProductBrand(long id);
    
    
    // for add
    public boolean isnameConflict(ProductBrand brand);
    
    
    // for edit
    public boolean editnameConflict(ProductBrand brand);
}
