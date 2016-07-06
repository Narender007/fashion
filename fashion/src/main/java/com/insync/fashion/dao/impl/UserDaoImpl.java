package com.insync.fashion.dao.impl;

import java.util.List;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insync.fashion.dao.UserDAO;
import com.insync.fashion.model.User;
import com.insync.fashion.util.HibernateUtils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.json.simple.JSONObject;

@Repository

public class UserDaoImpl implements UserDAO {
     
	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println("UserDaoImpl constructor");
	}
	
	@Autowired
	private HibernateUtils hibernateUtils;
	
	@Override
	public long createUser(User user) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(user);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(user);
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		 User user = new User();
		  user.setId(id);
         hibernateUtils.delete(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(User.class);
	}

	@Override
	public User getUser(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, User.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByName(String userName) {
		// TODO Auto-generated method stub
		String query = "SELECT u.* FROM user u WHERE u.userName like '%"+ userName +"%'";
		return hibernateUtils.fetchAll(query);
	}

// some methords not in hiberante generic class	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean isnameConflict(User user) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM User WHERE userName =:username");
		 query.setParameter("username", user.getUserName());
		 @SuppressWarnings("unchecked")
		List<User> userList =  query.list();
		 if(userList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

	@Override
	public boolean isemailConflict(User user) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM User WHERE email =:email");
		 query.setParameter("email", user.getEmail());
		 @SuppressWarnings("unchecked")
		 List<User> userList =  query.list();
		 if(userList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

	@Override
	public boolean isuserIdConflict(User user) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM User WHERE userId =:userid");
		 query.setParameter("userid", user.getUserId());
		 @SuppressWarnings("unchecked")
		List<User> userList =  query.list();
		 if(userList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

	
	@Override
	public boolean editnameConflict(User user) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM User WHERE userName =:username and id !="+ user.getId());
		 query.setParameter("username", user.getUserName());
		 @SuppressWarnings("unchecked")
		List<User> userList =  query.list();
		 if(userList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

	@Override
	public boolean editemailConflict(User user) {
		// TODO Auto-generated method stub
		boolean result = true;
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM User WHERE email =:email and id !="+ user.getId());
		 query.setParameter("email", user.getEmail());
		 @SuppressWarnings("unchecked")
		List<User> userList =  query.list();
		 if(userList.isEmpty())
		 {
			 result = false;
		 }
		 
		return result;
	}

	@Override
	public boolean edituserIdConflict(User user) {
		// TODO Auto-generated method stub
		boolean result = true;
		System.out.println("id is----- " + user.getId());
		
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM User WHERE userId =:userid and id !="+ user.getId());
		 query.setParameter("userid", user.getUserId());
		 @SuppressWarnings("unchecked")
		List<User> userList =  query.list();
		 if(userList.isEmpty())
		 {
			 result = false;
		 }
		 else{
			 User u = userList.get(0);
			 System.out.println(u.getId());
			 System.out.println(u.getUserName());
		 }
		 
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject userLogin(String userID, String password) {
		// TODO Auto-generated method stub
		User user = null;
		JSONObject obj = new JSONObject();
		org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM User WHERE userId =:userid and password =:pass and status = 1");
		query.setParameter("userid", userID);
		query.setParameter("pass", password);
		List<User> uList = query.list();
		if(uList.isEmpty())
		{
			obj.put("msgtype", "ERROR");
			obj.put("msg", "No User Found with Given Credancials");
			user=null;
		}
		else{
			obj.put("msgtype", "SUCCESS");
			obj.put("msg", "Login Successful");
			
			// now map the user object to json
			user = uList.get(0);
			ObjectMapper mapper = new ObjectMapper();
			try {
				String jsonInString = mapper.writeValueAsString(user);
				obj.put("user", jsonInString);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return obj;
	}
	
	// ///////////////

}
