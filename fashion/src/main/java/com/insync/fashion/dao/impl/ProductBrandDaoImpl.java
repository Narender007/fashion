package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.ProductBrandDao;
import com.insync.fashion.model.ProductBrand;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class ProductBrandDaoImpl implements ProductBrandDao {
	
	   public ProductBrandDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("brandDaoImpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createProductBrand(ProductBrand brand) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(brand);
	}

	@Override
	public ProductBrand updateProductBrand(ProductBrand brand) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(brand);
	}

	@Override
	public void deleteProductBrand(long id) {
		// TODO Auto-generated method stub
		ProductBrand brand = new ProductBrand();
		  brand.setId(id);
       hibernateUtils.delete(brand);
	}

	@Override
	public List<ProductBrand> getAllProductBrand() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(ProductBrand.class);
	}

	@Override
	public ProductBrand getProductBrand(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, ProductBrand.class);
	}
 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Override
	 @SuppressWarnings("unchecked")
	public boolean isnameConflict(ProductBrand brand) {
		// TODO Auto-generated method stub
				boolean result = true;
				org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM ProductBrand WHERE name =:brandname");
				 query.setParameter("brandname", brand.getName());
				
				List<ProductBrand> brandList =  query.list();
				 if(brandList.isEmpty())
				 {
					 result = false;
				 }
				 
				return result;
	}

	@Override
	 @SuppressWarnings("unchecked")
	public boolean editnameConflict(ProductBrand brand) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM ProductBrand WHERE name =:brandname and id !="+ brand.getId());
		 query.setParameter("brandname", brand.getName());
		
		List<ProductBrand> brandList =  query.list();
		 if(brandList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
