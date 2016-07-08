package com.insync.fashion.service;

import java.util.List;

import com.insync.fashion.model.IndoorLocation;



public interface IndoorLocationService {
	public long createIndoorLocation(IndoorLocation IndoorLocation);
	public IndoorLocation updateIndoorLocation(IndoorLocation IndoorLocation);
	public void deleteIndoorLocation(long id);
	public List<IndoorLocation> getAllIndoorLocation();
	public IndoorLocation getIndoorLocation(long id);
    
    
    // for add
    public boolean isnameConflict(IndoorLocation IndoorLocation);
    
    
    // for edit
    public boolean editnameConflict(IndoorLocation IndoorLocation);
}
