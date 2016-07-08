package com.insync.fashion.service;

import java.util.List;

import com.insync.fashion.model.Product;

public interface ProductService {
	public long createProduct(Product product);
	public Product updateProduct(Product product);
	public void deleteProduct(long id);
	public List<Product> getAllProduct();
	public Product getProduct(long id);
    
    
    // for add
    public boolean isnameConflict(Product product);
    
    
    // for edit
    public boolean editnameConflict(Product product);
    
}
