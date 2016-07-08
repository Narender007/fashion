package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.IndoorLocationDao;
import com.insync.fashion.model.IndoorLocation;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class IndoorLocationDaoImpl implements IndoorLocationDao {
	
	   public IndoorLocationDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("IndoorLocationDaoimpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createIndoorLocation(IndoorLocation indoorLocation) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(indoorLocation);
	}

	@Override
	public IndoorLocation updateIndoorLocation(IndoorLocation indoorLocation) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(indoorLocation);
	}

	@Override
	public void deleteIndoorLocation(long id) {
		// TODO Auto-generated method stub
		IndoorLocation indoorLocation = new IndoorLocation();
		  indoorLocation.setId(id);
       hibernateUtils.delete(indoorLocation);
	}

	@Override
	public List<IndoorLocation> getAllIndoorLocation() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(IndoorLocation.class);
	}

	@Override
	public IndoorLocation getIndoorLocation(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, IndoorLocation.class);
	}
 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Override
	 @SuppressWarnings("unchecked")
	public boolean isnameConflict(IndoorLocation role) {
		// TODO Auto-generated method stub
				boolean result = true;
				org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM IndoorLocation WHERE name =:rolename and placeId =:placeid");
				 query.setParameter("rolename", role.getName());
				 query.setParameter("placeid", role.getPlaceId());
				
				List<IndoorLocation> roleList =  query.list();
				 if(roleList.isEmpty())
				 {
					 result = false;
				 }
				 
				return result;
	}

	 @Override
	 @SuppressWarnings("unchecked")
	public boolean editnameConflict(IndoorLocation role) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM IndoorLocation WHERE name =:rolename and placeId =:placeid and id !="+ role.getId());
		 query.setParameter("rolename", role.getName());
		 query.setParameter("placeid", role.getPlaceId());
		List<IndoorLocation> roleList =  query.list();
		 if(roleList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
