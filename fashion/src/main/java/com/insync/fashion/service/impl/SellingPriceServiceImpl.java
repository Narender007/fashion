package com.insync.fashion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.SellingPriceDao;
import com.insync.fashion.model.SellingPrice;
import com.insync.fashion.service.SellingPriceService;


@Service
@Transactional
public class SellingPriceServiceImpl implements SellingPriceService {
	
	   public SellingPriceServiceImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("SellingPriceServiceImpl constructor");
	}
	   
	   @Autowired
		 private SellingPriceDao priceDao;

	@Override
	public long createSellingPrice(SellingPrice price) {
		// TODO Auto-generated method stub
		return priceDao.createSellingPrice(price);
	}

	@Override
	public SellingPrice updateSellingPrice(SellingPrice price) {
		// TODO Auto-generated method stub
		return priceDao.updateSellingPrice(price);
	}

	@Override
	public void deleteSellingPrice(long id) {
		// TODO Auto-generated method stub
		priceDao.deleteSellingPrice(id);
	}

	@Override
	public List<SellingPrice> getAllSellingPrice() {
		// TODO Auto-generated method stub
		return priceDao.getAllSellingPrice();
	}

	@Override
	public SellingPrice getSellingPrice(long id) {
		// TODO Auto-generated method stub
		return priceDao.getSellingPrice(id);
	}

	@Override
	public boolean isnameConflict(SellingPrice price) {
		// TODO Auto-generated method stub
		return priceDao.isnameConflict(price);
	}

	@Override
	public boolean editnameConflict(SellingPrice price) {
		// TODO Auto-generated method stub
		return priceDao.editnameConflict(price);
	}

}
