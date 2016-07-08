package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.ProductDao;
import com.insync.fashion.model.Product;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class ProductDaoImpl implements ProductDao {
	
	   public ProductDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("productDaoImpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createProduct(Product product) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(product);
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(product);
	}

	@Override
	public void deleteProduct(long id) {
		// TODO Auto-generated method stub
		Product product = new Product();
		  product.setId(id);
       hibernateUtils.delete(product);
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(Product.class);
	}

	@Override
	public Product getProduct(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, Product.class);
	}
 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Override
	 @SuppressWarnings("unchecked")
	public boolean isnameConflict(Product product) {
		// TODO Auto-generated method stub
				boolean result = true;
				org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM Product WHERE name =:productname");
				 query.setParameter("productname", product.getName());
				
				List<Product> productList =  query.list();
				 if(productList.isEmpty())
				 {
					 result = false;
				 }
				 
				return result;
	}

	@Override
	 @SuppressWarnings("unchecked")
	public boolean editnameConflict(Product product) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM Product WHERE name =:productname and id !="+ product.getId());
		 query.setParameter("productname", product.getName());
		
		List<Product> productList =  query.list();
		 if(productList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
