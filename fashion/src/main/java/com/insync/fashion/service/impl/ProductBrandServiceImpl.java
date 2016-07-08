package com.insync.fashion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.ProductBrandDao;
import com.insync.fashion.model.ProductBrand;
import com.insync.fashion.service.ProductBrandService;


@Service
@Transactional
public class ProductBrandServiceImpl implements ProductBrandService {
	
	   public ProductBrandServiceImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("ProductBrandServiceImpl constructor");
	}
	   
	   @Autowired
		 private ProductBrandDao brandDao;

	@Override
	public long createProductBrand(ProductBrand brand) {
		// TODO Auto-generated method stub
		return brandDao.createProductBrand(brand);
	}

	@Override
	public ProductBrand updateProductBrand(ProductBrand brand) {
		// TODO Auto-generated method stub
		return brandDao.updateProductBrand(brand);
	}

	@Override
	public void deleteProductBrand(long id) {
		// TODO Auto-generated method stub
		brandDao.deleteProductBrand(id);
	}

	@Override
	public List<ProductBrand> getAllProductBrand() {
		// TODO Auto-generated method stub
		return brandDao.getAllProductBrand();
	}

	@Override
	public ProductBrand getProductBrand(long id) {
		// TODO Auto-generated method stub
		return brandDao.getProductBrand(id);
	}

	@Override
	public boolean isnameConflict(ProductBrand brand) {
		// TODO Auto-generated method stub
		return brandDao.isnameConflict(brand);
	}

	@Override
	public boolean editnameConflict(ProductBrand brand) {
		// TODO Auto-generated method stub
		return brandDao.editnameConflict(brand);
	}

}
