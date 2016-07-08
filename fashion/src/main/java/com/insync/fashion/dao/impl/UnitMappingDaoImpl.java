package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.UnitMappingDao;
import com.insync.fashion.model.UnitMapping;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class UnitMappingDaoImpl implements UnitMappingDao {
	
	   public UnitMappingDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("unitMappingDaoImpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createUnitMapping(UnitMapping unitMapping) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(unitMapping);
	}

	@Override
	public UnitMapping updateUnitMapping(UnitMapping unitMapping) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(unitMapping);
	}

	@Override
	public void deleteUnitMapping(long id) {
		// TODO Auto-generated method stub
		UnitMapping unitMapping = new UnitMapping();
		  unitMapping.setId(id);
       hibernateUtils.delete(unitMapping);
	}

	@Override
	public List<UnitMapping> getAllUnitMapping() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(UnitMapping.class);
	}

	@Override
	public UnitMapping getUnitMapping(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, UnitMapping.class);
	}
 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Override
	 @SuppressWarnings("unchecked")
	public boolean isnameConflict(UnitMapping unitMapping) {
		// TODO Auto-generated method stub
				boolean result = true;
				org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM UnitMapping WHERE fromUnit =:fromunit and toUnit =:tounit");
				 query.setParameter("fromunit", unitMapping.getFromUnit());
				 query.setParameter("tounit", unitMapping.getToUnit());
				List<UnitMapping> unitMappingList =  query.list();
				 if(unitMappingList.isEmpty())
				 {
					 result = false;
				 }
				 
				return result;
	}

	@Override
	 @SuppressWarnings("unchecked")
	public boolean editnameConflict(UnitMapping unitMapping) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM UnitMapping WHERE fromUnit =:fromunit and toUnit =:tounit and id !="+ unitMapping.getId());
		 query.setParameter("fromunit", unitMapping.getFromUnit());
		 query.setParameter("tounit", unitMapping.getToUnit());
		List<UnitMapping> unitMappingList =  query.list();
		 if(unitMappingList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
