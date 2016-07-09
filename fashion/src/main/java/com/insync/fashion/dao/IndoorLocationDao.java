package com.insync.fashion.dao;

import java.util.List;

import org.json.simple.JSONArray;

import com.insync.fashion.model.IndoorLocation;



public interface IndoorLocationDao {
	public long createIndoorLocation(IndoorLocation IndoorLocation);
	public IndoorLocation updateIndoorLocation(IndoorLocation IndoorLocation);
	public void deleteIndoorLocation(long id);
	public List<IndoorLocation> getAllIndoorLocation();
	public IndoorLocation getIndoorLocation(long id);
	public JSONArray getJoinLocation();
    // for add
    public boolean isnameConflict(IndoorLocation IndoorLocation);
    
    
    // for edit
    public boolean editnameConflict(IndoorLocation IndoorLocation);
}
