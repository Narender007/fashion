package com.insync.fashion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.SupplierDao;
import com.insync.fashion.model.Supplier;
import com.insync.fashion.service.SupplierService;


@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
	
	   public SupplierServiceImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("SupplierServiceImpl constructor");
	}
	   
	   @Autowired
		 private SupplierDao supplierDao;

	@Override
	public long createSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierDao.createSupplier(supplier);
	}

	@Override
	public Supplier updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierDao.updateSupplier(supplier);
	}

	@Override
	public void deleteSupplier(long id) {
		// TODO Auto-generated method stub
		supplierDao.deleteSupplier(id);
	}

	@Override
	public List<Supplier> getAllSupplier() {
		// TODO Auto-generated method stub
		return supplierDao.getAllSupplier();
	}

	@Override
	public Supplier getSupplier(long id) {
		// TODO Auto-generated method stub
		return supplierDao.getSupplier(id);
	}

	@Override
	public boolean isnameConflict(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierDao.isnameConflict(supplier);
	}

	@Override
	public boolean editnameConflict(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierDao.editnameConflict(supplier);
	}

}
