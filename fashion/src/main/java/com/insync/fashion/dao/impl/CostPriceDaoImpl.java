package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.CostPriceDao;
import com.insync.fashion.model.CostPrice;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class CostPriceDaoImpl implements CostPriceDao {
	
	   public CostPriceDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("priceDaoImpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createCostPrice(CostPrice price) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(price);
	}

	@Override
	public CostPrice updateCostPrice(CostPrice price) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(price);
	}

	@Override
	public void deleteCostPrice(long id) {
		// TODO Auto-generated method stub
		CostPrice price = new CostPrice();
		  price.setId(id);
       hibernateUtils.delete(price);
	}

	@Override
	public List<CostPrice> getAllCostPrice() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(CostPrice.class);
	}

	@Override
	public CostPrice getCostPrice(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, CostPrice.class);
	}
 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Override
	 @SuppressWarnings("unchecked")
	public boolean isnameConflict(CostPrice price) {
		// TODO Auto-generated method stub
				boolean result = true;
				org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM CostPrice WHERE productId =:productid and unit =:unitid");
				 query.setParameter("productid", price.getProductId());
				 query.setParameter("unitid", price.getUnit());
				List<CostPrice> priceList =  query.list();
				 if(priceList.isEmpty())
				 {
					 result = false;
				 }
				 
				return result;
	}

	@Override
	 @SuppressWarnings("unchecked")
	public boolean editnameConflict(CostPrice price) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM CostPrice WHERE productId =:productid and unit =:unitid and id !="+ price.getId());
		 query.setParameter("productid", price.getProductId());
		 query.setParameter("unitid", price.getUnit());
		List<CostPrice> priceList =  query.list();
		 if(priceList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
