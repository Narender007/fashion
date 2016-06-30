package com.insync.fashion.service.impl;

import java.util.List;

import com.insync.fashion.dao.UserDAO;
import com.insync.fashion.model.User;
import com.insync.fashion.service.UserService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
     
	 public UserServiceImpl() {
		// TODO Auto-generated constructor stub
		 System.out.println("UserServiceImpl constructor");
	}
	 
	 @Autowired
	 private UserDAO userDao;
	 
	@Override
	public long createUser(User user) {
		// TODO Auto-generated method stub
		return userDao.createUser(user);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
         userDao.deleteUser(id);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}

	@Override
	public User getUser(long id) {
		// TODO Auto-generated method stub
		return userDao.getUser(id);
	}

	@Override
	public List<User> getUserByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.getUserByName(userName);
	}

	@Override
	public boolean isnameConflict(User user) {
		// TODO Auto-generated method stub
		return userDao.isnameConflict(user);
	}

	@Override
	public boolean isemailConflict(User user) {
		// TODO Auto-generated method stub
		return userDao.isemailConflict(user);
	}

	@Override
	public boolean isuserIdConflict(User user) {
		// TODO Auto-generated method stub
		return userDao.isuserIdConflict(user);
	}

	@Override
	public boolean editnameConflict(User user) {
		// TODO Auto-generated method stub
		return userDao.editnameConflict(user);
	}

	@Override
	public boolean editemailConflict(User user) {
		// TODO Auto-generated method stub
		return userDao.editemailConflict(user);
	}

	@Override
	public boolean edituserIdConflict(User user) {
		// TODO Auto-generated method stub
		return userDao.edituserIdConflict(user);
	}

	@Override
	public JSONObject userLogin(String userID, String password) {
		// TODO Auto-generated method stub
		return userDao.userLogin(userID, password);
	}

}
