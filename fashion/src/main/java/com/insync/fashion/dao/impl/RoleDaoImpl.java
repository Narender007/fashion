package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.RoleDao;
import com.insync.fashion.model.Role;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class RoleDaoImpl implements RoleDao {
	
	   public RoleDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("roleDaoImpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createRole(Role role) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(role);
	}

	@Override
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(role);
	}

	@Override
	public void deleteRole(long id) {
		// TODO Auto-generated method stub
		Role role = new Role();
		  role.setId(id);
       hibernateUtils.delete(role);
	}

	@Override
	public List<Role> getAllRole() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(Role.class);
	}

	@Override
	public Role getRole(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, Role.class);
	}
 
	
	@Autowired
	private SessionFactory sessionFactory;
	
	 @Override
	 @SuppressWarnings("unchecked")
	public boolean isnameConflict(Role role) {
		// TODO Auto-generated method stub
				boolean result = true;
				org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM Role WHERE rolename =:rolename");
				 query.setParameter("rolename", role.getRoleName());
				
				List<Role> roleList =  query.list();
				 if(roleList.isEmpty())
				 {
					 result = false;
				 }
				 
				return result;
	}

	@Override
	 @SuppressWarnings("unchecked")
	public boolean editnameConflict(Role role) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM Role WHERE rolename =:rolename and id !="+ role.getId());
		 query.setParameter("rolename", role.getRoleName());
		
		List<Role> roleList =  query.list();
		 if(roleList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

}
