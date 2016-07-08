package com.insync.fashion.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.IndoorLocationDao;
import com.insync.fashion.model.IndoorLocation;
import com.insync.fashion.service.IndoorLocationService;


@Service
@Transactional
public class IndoorLocationServiceImpl implements IndoorLocationService {

	 public IndoorLocationServiceImpl() {
		// TODO Auto-generated constructor stub
		 System.out.println("IndoorLocationServiceImpl");
	}
	 
	 @Autowired
	 private IndoorLocationDao indoorlocationDao;
	
	 
	@Override
	public long createIndoorLocation(IndoorLocation IndoorLocation) {
		// TODO Auto-generated method stub
		return indoorlocationDao.createIndoorLocation(IndoorLocation);
	}

	@Override
	public IndoorLocation updateIndoorLocation(IndoorLocation IndoorLocation) {
		// TODO Auto-generated method stub
		return indoorlocationDao.updateIndoorLocation(IndoorLocation);
	}

	@Override
	public void deleteIndoorLocation(long id) {
		// TODO Auto-generated method stub
		indoorlocationDao.deleteIndoorLocation(id);
	}

	@Override
	public List<IndoorLocation> getAllIndoorLocation() {
		// TODO Auto-generated method stub
		return indoorlocationDao.getAllIndoorLocation();
	}

	@Override
	public IndoorLocation getIndoorLocation(long id) {
		// TODO Auto-generated method stub
		return indoorlocationDao.getIndoorLocation(id);
	}

	@Override
	public boolean isnameConflict(IndoorLocation IndoorLocation) {
		// TODO Auto-generated method stub
		return indoorlocationDao.isnameConflict(IndoorLocation);
	}

	@Override
	public boolean editnameConflict(IndoorLocation IndoorLocation) {
		// TODO Auto-generated method stub
		return indoorlocationDao.editnameConflict(IndoorLocation);
	}

}
