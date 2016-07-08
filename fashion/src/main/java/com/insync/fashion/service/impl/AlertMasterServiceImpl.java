package com.insync.fashion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.AlertMasterDao;
import com.insync.fashion.model.AlertMaster;
import com.insync.fashion.service.AlertMasterService;


@Service
@Transactional
public class AlertMasterServiceImpl implements AlertMasterService {
	
	   public AlertMasterServiceImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("AlertMasterServiceImpl constructor");
	}
	   
	   @Autowired
		 private AlertMasterDao alertDao;

	@Override
	public long createAlertMaster(AlertMaster alert) {
		// TODO Auto-generated method stub
		return alertDao.createAlertMaster(alert);
	}

	@Override
	public AlertMaster updateAlertMaster(AlertMaster alert) {
		// TODO Auto-generated method stub
		return alertDao.updateAlertMaster(alert);
	}

	@Override
	public void deleteAlertMaster(long id) {
		// TODO Auto-generated method stub
		alertDao.deleteAlertMaster(id);
	}

	@Override
	public List<AlertMaster> getAllAlertMaster() {
		// TODO Auto-generated method stub
		return alertDao.getAllAlertMaster();
	}

	@Override
	public AlertMaster getAlertMaster(long id) {
		// TODO Auto-generated method stub
		return alertDao.getAlertMaster(id);
	}

	@Override
	public boolean isnameConflict(AlertMaster alert) {
		// TODO Auto-generated method stub
		return alertDao.isnameConflict(alert);
	}

	@Override
	public boolean editnameConflict(AlertMaster alert) {
		// TODO Auto-generated method stub
		return alertDao.editnameConflict(alert);
	}

}
