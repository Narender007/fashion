package com.insync.fashion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.ProductDao;
import com.insync.fashion.model.Product;
import com.insync.fashion.service.ProductService;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	   public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("ProductServiceImpl constructor");
	}
	   
	   @Autowired
		 private ProductDao productDao;

	@Override
	public long createProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.createProduct(product);
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(long id) {
		// TODO Auto-generated method stub
		productDao.deleteProduct(id);
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productDao.getAllProduct();
	}

	@Override
	public Product getProduct(long id) {
		// TODO Auto-generated method stub
		return productDao.getProduct(id);
	}

	@Override
	public boolean isnameConflict(Product product) {
		// TODO Auto-generated method stub
		return productDao.isnameConflict(product);
	}

	@Override
	public boolean editnameConflict(Product product) {
		// TODO Auto-generated method stub
		return productDao.editnameConflict(product);
	}

}
