package com.insync.fashion.service;

import java.util.List;
import com.insync.fashion.model.Unit;

public interface UnitService {
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
