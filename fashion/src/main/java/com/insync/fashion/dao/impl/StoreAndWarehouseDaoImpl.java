package com.insync.fashion.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.insync.fashion.dao.StoreAndWarehouseDao;
import com.insync.fashion.model.StoreAndWarehouse;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;



  @Repository


public class StoreAndWarehouseDaoImpl implements StoreAndWarehouseDao {
     
	  public StoreAndWarehouseDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	  
	  @Autowired
	  private HibernateUtils hibernateUtils;
	  
	@Override
	public long createStore(StoreAndWarehouse sw) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(sw);
	}

	@Override
	public StoreAndWarehouse updateStore(StoreAndWarehouse sw) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(sw);
	}

	@Override
	public void deleteStore(long id) {
		// TODO Auto-generated method 
		  StoreAndWarehouse sw = new StoreAndWarehouse();
		  sw.setId(id);
          hibernateUtils.delete(sw);
	}

	@Override
	public List<StoreAndWarehouse> getAllStore() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(StoreAndWarehouse.class);
	}

	@Override
	public StoreAndWarehouse getStore(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, StoreAndWarehouse.class);
	}

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean isnameConflict(StoreAndWarehouse sw) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM StoreAndWarehouse WHERE name =:name");
		 query.setParameter("name", sw.getName());
		
		List<StoreAndWarehouse> roleList =  query.list();
		 if(roleList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean editnameConflict(StoreAndWarehouse sw) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM StoreAndWarehouse WHERE name =:name and id !="+ sw.getId());
		 query.setParameter("name", sw.getName());
		
		List<StoreAndWarehouse> roleList =  query.list();
		 if(roleList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
