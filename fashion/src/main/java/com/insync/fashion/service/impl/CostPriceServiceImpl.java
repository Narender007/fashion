package com.insync.fashion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.CostPriceDao;
import com.insync.fashion.model.CostPrice;
import com.insync.fashion.service.CostPriceService;


@Service
@Transactional
public class CostPriceServiceImpl implements CostPriceService {
	
	   public CostPriceServiceImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("CostPriceServiceImpl constructor");
	}
	   
	   @Autowired
		 private CostPriceDao priceDao;

	@Override
	public long createCostPrice(CostPrice price) {
		// TODO Auto-generated method stub
		return priceDao.createCostPrice(price);
	}

	@Override
	public CostPrice updateCostPrice(CostPrice price) {
		// TODO Auto-generated method stub
		return priceDao.updateCostPrice(price);
	}

	@Override
	public void deleteCostPrice(long id) {
		// TODO Auto-generated method stub
		priceDao.deleteCostPrice(id);
	}

	@Override
	public List<CostPrice> getAllCostPrice() {
		// TODO Auto-generated method stub
		return priceDao.getAllCostPrice();
	}

	@Override
	public CostPrice getCostPrice(long id) {
		// TODO Auto-generated method stub
		return priceDao.getCostPrice(id);
	}

	@Override
	public boolean isnameConflict(CostPrice price) {
		// TODO Auto-generated method stub
		return priceDao.isnameConflict(price);
	}

	@Override
	public boolean editnameConflict(CostPrice price) {
		// TODO Auto-generated method stub
		return priceDao.editnameConflict(price);
	}

}
