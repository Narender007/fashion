package com.insync.fashion.dao;

import java.util.List;
import com.insync.fashion.model.Unit;

public interface UnitDao {
	public long createUnit(Unit role);
	public Unit updateUnit(Unit role);
	public void deleteUnit(long id);
	public List<Unit> getAllUnit();
	public Unit getUnit(long id);
    
    
    // for add
    public boolean isnameConflict(Unit role);
    
    
    // for edit
    public boolean editnameConflict(Unit role);
    
}
