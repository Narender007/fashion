package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.ProductTypeDao;
import com.insync.fashion.model.ProductType;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class ProductTypeDaoImpl implements ProductTypeDao {
	
	   public ProductTypeDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("typeDaoImpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createProductType(ProductType type) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(type);
	}

	@Override
	public ProductType updateProductType(ProductType type) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(type);
	}

	@Override
	public void deleteProductType(long id) {
		// TODO Auto-generated method stub
		ProductType type = new ProductType();
		  type.setId(id);
       hibernateUtils.delete(type);
	}

	@Override
	public List<ProductType> getAllProductType() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(ProductType.class);
	}

	@Override
	public ProductType getProductType(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, ProductType.class);
	}
 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Override
	 @SuppressWarnings("unchecked")
	public boolean isnameConflict(ProductType type) {
		// TODO Auto-generated method stub
				boolean result = true;
				org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM ProductType WHERE name =:typename");
				 query.setParameter("typename", type.getName());
				
				List<ProductType> typeList =  query.list();
				 if(typeList.isEmpty())
				 {
					 result = false;
				 }
				 
				return result;
	}

	@Override
	 @SuppressWarnings("unchecked")
	public boolean editnameConflict(ProductType type) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM ProductType WHERE name =:typename and id !="+ type.getId());
		 query.setParameter("typename", type.getName());
		
		List<ProductType> typeList =  query.list();
		 if(typeList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
