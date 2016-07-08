package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.AlertMasterDao;
import com.insync.fashion.model.AlertMaster;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class AlertMasterDaoImpl implements AlertMasterDao {
	
	   public AlertMasterDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("alertDaoImpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createAlertMaster(AlertMaster alert) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(alert);
	}

	@Override
	public AlertMaster updateAlertMaster(AlertMaster alert) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(alert);
	}

	@Override
	public void deleteAlertMaster(long id) {
		// TODO Auto-generated method stub
		AlertMaster alert = new AlertMaster();
		  alert.setId(id);
       hibernateUtils.delete(alert);
	}

	@Override
	public List<AlertMaster> getAllAlertMaster() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(AlertMaster.class);
	}

	@Override
	public AlertMaster getAlertMaster(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, AlertMaster.class);
	}
 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Override
	 @SuppressWarnings("unchecked")
	public boolean isnameConflict(AlertMaster alert) {
		// TODO Auto-generated method stub
				boolean result = true;
				org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM AlertMaster WHERE productId =:productid and placeId =:placeid and unit =:unitid");
				 query.setParameter("placeid", alert.getPlaceId());
				 query.setParameter("productid", alert.getProductId());
				 query.setParameter("unitid", alert.getUnit());
				List<AlertMaster> alertList =  query.list();
				 if(alertList.isEmpty())
				 {
					 result = false;
				 }
				 
				return result;
	}

	@Override
	 @SuppressWarnings("unchecked")
	public boolean editnameConflict(AlertMaster alert) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM AlertMaster WHERE productId =:productid and placeId =:placeid and unit =:unitid and id !="+ alert.getId());
		query.setParameter("placeid", alert.getPlaceId());
		 query.setParameter("productid", alert.getProductId());
		 query.setParameter("unitid", alert.getUnit());
		List<AlertMaster> alertList =  query.list();
		 if(alertList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
