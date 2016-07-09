package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.UnitDao;
import com.insync.fashion.model.Unit;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class UnitDaoImpl implements UnitDao {
	
	   public UnitDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("UnitDaoImpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createUnit(Unit Unit) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(Unit);
	}

	@Override
	public Unit updateUnit(Unit Unit) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(Unit);
	}

	@Override
	public void deleteUnit(long id) {
		// TODO Auto-generated method stub
		Unit Unit = new Unit();
		  Unit.setId(id);
       hibernateUtils.delete(Unit);
	}

	@Override
	public List<Unit> getAllUnit() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(Unit.class);
	}

	@Override
	public Unit getUnit(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, Unit.class);
	}
 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Override
	 @SuppressWarnings("unchecked")
	public boolean isnameConflict(Unit Unit) {
		// TODO Auto-generated method stub
				boolean result = true;
				org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM Unit WHERE name =:Unitname");
				 query.setParameter("Unitname", Unit.getName());
				
				List<Unit> UnitList =  query.list();
				 if(UnitList.isEmpty())
				 {
					 result = false;
				 }
				 
				return result;
	}

	@Override
	 @SuppressWarnings("unchecked")
	public boolean editnameConflict(Unit Unit) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM Unit WHERE Unitname =:name and id !="+ Unit.getId());
		 query.setParameter("Unitname", Unit.getName());
		
		List<Unit> UnitList =  query.list();
		 if(UnitList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
