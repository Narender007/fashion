package com.insync.fashion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.UnitDao;
import com.insync.fashion.model.Unit;
import com.insync.fashion.service.UnitService;


@Service
@Transactional
public class UnitServiceImpl implements UnitService {
	
	   public UnitServiceImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("UnitServiceImpl constructor");
	}
	   
	   @Autowired
		 private UnitDao unitDao;

	@Override
	public long createUnit(Unit role) {
		// TODO Auto-generated method stub
		return unitDao.createUnit(role);
	}

	@Override
	public Unit updateUnit(Unit role) {
		// TODO Auto-generated method stub
		return unitDao.updateUnit(role);
	}

	@Override
	public void deleteUnit(long id) {
		// TODO Auto-generated method stub
		unitDao.deleteUnit(id);
	}

	@Override
	public List<Unit> getAllUnit() {
		// TODO Auto-generated method stub
		return unitDao.getAllUnit();
	}

	@Override
	public Unit getUnit(long id) {
		// TODO Auto-generated method stub
		return unitDao.getUnit(id);
	}

	@Override
	public boolean isnameConflict(Unit role) {
		// TODO Auto-generated method stub
		return unitDao.isnameConflict(role);
	}

	@Override
	public boolean editnameConflict(Unit role) {
		// TODO Auto-generated method stub
		return unitDao.editnameConflict(role);
	}

}
