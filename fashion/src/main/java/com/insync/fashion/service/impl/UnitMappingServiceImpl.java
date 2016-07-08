package com.insync.fashion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.UnitMappingDao;
import com.insync.fashion.model.UnitMapping;
import com.insync.fashion.service.UnitMappingService;


@Service
@Transactional
public class UnitMappingServiceImpl implements UnitMappingService {
	
	   public UnitMappingServiceImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("UnitMappingServiceImpl constructor");
	}
	   
	   @Autowired
		 private UnitMappingDao unitMappingDao;

	@Override
	public long createUnitMapping(UnitMapping unitMapping) {
		// TODO Auto-generated method stub
		return unitMappingDao.createUnitMapping(unitMapping);
	}

	@Override
	public UnitMapping updateUnitMapping(UnitMapping unitMapping) {
		// TODO Auto-generated method stub
		return unitMappingDao.updateUnitMapping(unitMapping);
	}

	@Override
	public void deleteUnitMapping(long id) {
		// TODO Auto-generated method stub
		unitMappingDao.deleteUnitMapping(id);
	}

	@Override
	public List<UnitMapping> getAllUnitMapping() {
		// TODO Auto-generated method stub
		return unitMappingDao.getAllUnitMapping();
	}

	@Override
	public UnitMapping getUnitMapping(long id) {
		// TODO Auto-generated method stub
		return unitMappingDao.getUnitMapping(id);
	}

	@Override
	public boolean isnameConflict(UnitMapping unitMapping) {
		// TODO Auto-generated method stub
		return unitMappingDao.isnameConflict(unitMapping);
	}

	@Override
	public boolean editnameConflict(UnitMapping unitMapping) {
		// TODO Auto-generated method stub
		return unitMappingDao.editnameConflict(unitMapping);
	}

}
