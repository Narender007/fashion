package com.insync.fashion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.RoleDao;
import com.insync.fashion.model.Role;
import com.insync.fashion.service.RoleService;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	
	   public RoleServiceImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("RoleServiceImpl constructor");
	}
	   
	   @Autowired
		 private RoleDao roleDao;

	@Override
	public long createRole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.createRole(role);
	}

	@Override
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		return roleDao.updateRole(role);
	}

	@Override
	public void deleteRole(long id) {
		// TODO Auto-generated method stub
		roleDao.deleteRole(id);
	}

	@Override
	public List<Role> getAllRole() {
		// TODO Auto-generated method stub
		return roleDao.getAllRole();
	}

	@Override
	public Role getRole(long id) {
		// TODO Auto-generated method stub
		return roleDao.getRole(id);
	}

	@Override
	public boolean isnameConflict(Role role) {
		// TODO Auto-generated method stub
		return roleDao.isnameConflict(role);
	}

	@Override
	public boolean editnameConflict(Role role) {
		// TODO Auto-generated method stub
		return roleDao.editnameConflict(role);
	}

}
