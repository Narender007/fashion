package com.insync.fashion.dao;

import java.util.List;



import com.insync.fashion.model.UnitMapping;

public interface UnitMappingDao {
	public long createUnitMapping(UnitMapping unitMapping);
	public UnitMapping updateUnitMapping(UnitMapping unitMapping);
	public void deleteUnitMapping(long id);
	public List<UnitMapping> getAllUnitMapping();
	public UnitMapping getUnitMapping(long id);
    
    
    // for add
    public boolean isnameConflict(UnitMapping unitMapping);
    
    
    // for edit
    public boolean editnameConflict(UnitMapping unitMapping);
    
}
