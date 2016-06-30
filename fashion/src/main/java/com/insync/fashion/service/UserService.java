package com.insync.fashion.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.insync.fashion.model.User;

public interface UserService {
	public long createUser(User user);
	public User updateUser(User user);
	public void deleteUser(long id);
	public List<User> getAllUser();
	public User getUser(long id);
    public List<User> getUserByName(String userName);
    

    // for add
    public boolean isnameConflict(User user);
    public boolean isemailConflict(User user);
    public boolean isuserIdConflict(User user);
    
    // for edit
    public boolean editnameConflict(User user);
    public boolean editemailConflict(User user);
    public boolean edituserIdConflict(User user);
    
    // login
    public JSONObject userLogin(String userID,String password);
}

