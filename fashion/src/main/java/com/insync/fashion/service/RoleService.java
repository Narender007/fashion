package com.insync.fashion.service;

import java.util.List;

import com.insync.fashion.model.Role;

public interface RoleService {
	public long createRole(Role role);
	public Role updateRole(Role role);
	public void deleteRole(long id);
	public List<Role> getAllRole();
	public Role getRole(long id);
    
    
    // for add
    public boolean isnameConflict(Role role);
    
    
    // for edit
    public boolean editnameConflict(Role role);
}
