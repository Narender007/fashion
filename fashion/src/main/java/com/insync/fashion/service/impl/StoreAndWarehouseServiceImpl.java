package com.insync.fashion.service.impl;

import java.util.List;

import com.insync.fashion.dao.StoreAndWarehouseDao;
import com.insync.fashion.model.StoreAndWarehouse;
import com.insync.fashion.service.StoreAndWarehouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class StoreAndWarehouseServiceImpl implements StoreAndWarehouseService {

	public StoreAndWarehouseServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private StoreAndWarehouseDao saw;
	 
	@Override
	public long createStore(StoreAndWarehouse sw) {
		// TODO Auto-generated method stub
		return saw.createStore(sw);
	}

	@Override
	public StoreAndWarehouse updateStore(StoreAndWarehouse sw) {
		// TODO Auto-generated method stub
		return saw.updateStore(sw);
	}

	@Override
	public void deleteStore(long id) {
		// TODO Auto-generated method stub
          saw.deleteStore(id);
	}

	@Override
	public List<StoreAndWarehouse> getAllStore() {
		// TODO Auto-generated method stub
		return saw.getAllStore();
	}

	@Override
	public StoreAndWarehouse getStore(long id) {
		// TODO Auto-generated method stub
		return saw.getStore(id);
	}

	@Override
	public boolean isnameConflict(StoreAndWarehouse sw) {
		// TODO Auto-generated method stub
		return saw.isnameConflict(sw);
	}

	@Override
	public boolean editnameConflict(StoreAndWarehouse sw) {
		// TODO Auto-generated method stub
		return saw.editnameConflict(sw);
	}

}
