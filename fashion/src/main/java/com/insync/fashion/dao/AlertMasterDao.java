package com.insync.fashion.dao;

import java.util.List;



import com.insync.fashion.model.AlertMaster;

public interface AlertMasterDao {
	public long createAlertMaster(AlertMaster alert);
	public AlertMaster updateAlertMaster(AlertMaster alert);
	public void deleteAlertMaster(long id);
	public List<AlertMaster> getAllAlertMaster();
	public AlertMaster getAlertMaster(long id);
    
    
    // for add
    public boolean isnameConflict(AlertMaster alert);
    
    
    // for edit
    public boolean editnameConflict(AlertMaster alert);
    
}
