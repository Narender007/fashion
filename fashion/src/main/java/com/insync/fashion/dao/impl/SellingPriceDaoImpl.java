package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.SellingPriceDao;
import com.insync.fashion.model.SellingPrice;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class SellingPriceDaoImpl implements SellingPriceDao {
	
	   public SellingPriceDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("priceDaoImpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createSellingPrice(SellingPrice price) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(price);
	}

	@Override
	public SellingPrice updateSellingPrice(SellingPrice price) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(price);
	}

	@Override
	public void deleteSellingPrice(long id) {
		// TODO Auto-generated method stub
		SellingPrice price = new SellingPrice();
		  price.setId(id);
       hibernateUtils.delete(price);
	}

	@Override
	public List<SellingPrice> getAllSellingPrice() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(SellingPrice.class);
	}

	@Override
	public SellingPrice getSellingPrice(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, SellingPrice.class);
	}
 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Override
	 @SuppressWarnings("unchecked")
	public boolean isnameConflict(SellingPrice price) {
		// TODO Auto-generated method stub
				boolean result = true;
				org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM SellingPrice WHERE productId =:productid and unit =:unitid");
				 query.setParameter("productid", price.getProductId());
				 query.setParameter("unitid", price.getUnit());
				List<SellingPrice> priceList =  query.list();
				 if(priceList.isEmpty())
				 {
					 result = false;
				 }
				 
				return result;
	}

	@Override
	 @SuppressWarnings("unchecked")
	public boolean editnameConflict(SellingPrice price) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM SellingPrice WHERE productId =:productid and unit =:unitid and id !="+ price.getId());
		 query.setParameter("productid", price.getProductId());
		 query.setParameter("unitid", price.getUnit());
		List<SellingPrice> priceList =  query.list();
		 if(priceList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
