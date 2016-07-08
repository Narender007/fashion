package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.SupplierCatelogDao;
import com.insync.fashion.model.SupplierCatelog;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class SupplierCatelogDaoImpl implements SupplierCatelogDao {
	
	   public SupplierCatelogDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("catelogDaoImpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createSupplierCatelog(SupplierCatelog catelog) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(catelog);
	}

	@Override
	public SupplierCatelog updateSupplierCatelog(SupplierCatelog catelog) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(catelog);
	}

	@Override
	public void deleteSupplierCatelog(long id) {
		// TODO Auto-generated method stub
		SupplierCatelog catelog = new SupplierCatelog();
		  catelog.setId(id);
       hibernateUtils.delete(catelog);
	}

	@Override
	public List<SupplierCatelog> getAllSupplierCatelog() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(SupplierCatelog.class);
	}

	@Override
	public SupplierCatelog getSupplierCatelog(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, SupplierCatelog.class);
	}
 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Override
	 @SuppressWarnings("unchecked")
	public boolean isnameConflict(SupplierCatelog catelog) {
		// TODO Auto-generated method stub
				boolean result = true;
				org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM SupplierCatelog WHERE supplierId =:supplierid and productId =:productid");
				 query.setParameter("supplierid", catelog.getSupplierId());
				 query.setParameter("productid", catelog.getProductId());
				List<SupplierCatelog> catelogList =  query.list();
				 if(catelogList.isEmpty())
				 {
					 result = false;
				 }
				 
				return result;
	}

	@Override
	 @SuppressWarnings("unchecked")
	public boolean editnameConflict(SupplierCatelog catelog) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM SupplierCatelog WHERE supplierId =:supplierid and productId =:productid and id !="+ catelog.getId());
		 query.setParameter("supplierid", catelog.getSupplierId());
		 query.setParameter("productid", catelog.getProductId());
		List<SupplierCatelog> catelogList =  query.list();
		 if(catelogList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
