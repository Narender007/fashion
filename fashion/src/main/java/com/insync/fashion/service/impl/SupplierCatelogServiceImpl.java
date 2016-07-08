package com.insync.fashion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.SupplierCatelogDao;
import com.insync.fashion.model.SupplierCatelog;
import com.insync.fashion.service.SupplierCatelogService;


@Service
@Transactional
public class SupplierCatelogServiceImpl implements SupplierCatelogService {
	
	   public SupplierCatelogServiceImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("SupplierCatelogServiceImpl constructor");
	}
	   
	   @Autowired
		 private SupplierCatelogDao catelogDao;

	@Override
	public long createSupplierCatelog(SupplierCatelog catelog) {
		// TODO Auto-generated method stub
		return catelogDao.createSupplierCatelog(catelog);
	}

	@Override
	public SupplierCatelog updateSupplierCatelog(SupplierCatelog catelog) {
		// TODO Auto-generated method stub
		return catelogDao.updateSupplierCatelog(catelog);
	}

	@Override
	public void deleteSupplierCatelog(long id) {
		// TODO Auto-generated method stub
		catelogDao.deleteSupplierCatelog(id);
	}

	@Override
	public List<SupplierCatelog> getAllSupplierCatelog() {
		// TODO Auto-generated method stub
		return catelogDao.getAllSupplierCatelog();
	}

	@Override
	public SupplierCatelog getSupplierCatelog(long id) {
		// TODO Auto-generated method stub
		return catelogDao.getSupplierCatelog(id);
	}

	@Override
	public boolean isnameConflict(SupplierCatelog catelog) {
		// TODO Auto-generated method stub
		return catelogDao.isnameConflict(catelog);
	}

	@Override
	public boolean editnameConflict(SupplierCatelog catelog) {
		// TODO Auto-generated method stub
		return catelogDao.editnameConflict(catelog);
	}

}
