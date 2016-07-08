package com.insync.fashion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.ProductTypeDao;
import com.insync.fashion.model.ProductType;
import com.insync.fashion.service.ProductTypeService;


@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {
	
	   public ProductTypeServiceImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("ProductTypeServiceImpl constructor");
	}
	   
	   @Autowired
		 private ProductTypeDao typeDao;

	@Override
	public long createProductType(ProductType type) {
		// TODO Auto-generated method stub
		return typeDao.createProductType(type);
	}

	@Override
	public ProductType updateProductType(ProductType type) {
		// TODO Auto-generated method stub
		return typeDao.updateProductType(type);
	}

	@Override
	public void deleteProductType(long id) {
		// TODO Auto-generated method stub
		typeDao.deleteProductType(id);
	}

	@Override
	public List<ProductType> getAllProductType() {
		// TODO Auto-generated method stub
		return typeDao.getAllProductType();
	}

	@Override
	public ProductType getProductType(long id) {
		// TODO Auto-generated method stub
		return typeDao.getProductType(id);
	}

	@Override
	public boolean isnameConflict(ProductType type) {
		// TODO Auto-generated method stub
		return typeDao.isnameConflict(type);
	}

	@Override
	public boolean editnameConflict(ProductType type) {
		// TODO Auto-generated method stub
		return typeDao.editnameConflict(type);
	}

}
