package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.SupplierDao;
import com.insync.fashion.model.Supplier;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class SupplierDaoImpl implements SupplierDao {
	
	   public SupplierDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("SupplierDaoImpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(supplier);
	}

	@Override
	public Supplier updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(supplier);
	}

	@Override
	public void deleteSupplier(long id) {
		// TODO Auto-generated method stub
		Supplier supplier = new Supplier();
		  supplier.setId(id);
       hibernateUtils.delete(supplier);
	}

	@Override
	public List<Supplier> getAllSupplier() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(Supplier.class);
	}

	@Override
	public Supplier getSupplier(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, Supplier.class);
	}
 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Override
	 @SuppressWarnings("unchecked")
	public boolean isnameConflict(Supplier supplier) {
		// TODO Auto-generated method stub
				boolean result = true;
				org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM Supplier WHERE name =:suppliername and companyName =:companyname");
				 query.setParameter("suppliername", supplier.getName());
				 query.setParameter("companyname", supplier.getCompanyName());
				List<Supplier> supplierList =  query.list();
				 if(supplierList.isEmpty())
				 {
					 result = false;
				 }
				 
				return result;
	}

	@Override
	 @SuppressWarnings("unchecked")
	public boolean editnameConflict(Supplier supplier) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM Supplier WHERE name =:suppliername and companyName =:companyname and id !="+ supplier.getId());
		query.setParameter("suppliername", supplier.getName());
		query.setParameter("companyname", supplier.getCompanyName());
		List<Supplier> supplierList =  query.list();
		 if(supplierList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
