package com.insync.fashion.dao.impl;

import java.util.List;

import com.insync.fashion.dao.CustomerDao;
import com.insync.fashion.model.Customer;
import com.insync.fashion.util.HibernateUtils;

//import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository


public class CustomerDaoImpl implements CustomerDao {
	
	   public CustomerDaoImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("CustomerDaoImpl constructor");
	}
	   
	   @Autowired
		private HibernateUtils hibernateUtils;

	@Override
	public long createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return (Long) hibernateUtils.create(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return hibernateUtils.update(customer);
	}

	@Override
	public void deleteCustomer(long id) {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		  customer.setId(id);
       hibernateUtils.delete(customer);
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchAll(Customer.class);
	}

	@Override
	public Customer getCustomer(long id) {
		// TODO Auto-generated method stub
		return hibernateUtils.fetchById(id, Customer.class);
	}
 
	
	//@Autowired
	//private SessionFactory sessionFactory;
	
	 @Override
	// @SuppressWarnings("unchecked")
	public boolean isnameConflict(Customer customer) {
		// TODO Auto-generated method stub
				boolean result = true;
				/*org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM Customer WHERE name =:customername and companyName =:companyname");
				 query.setParameter("customername", customer.getName());
				 query.setParameter("companyname", customer.getCompanyName());
				List<Customer> customerList =  query.list();
				 if(customerList.isEmpty())
				 {
					 result = false;
				 }*/
				 
				return result;
	}

	@Override
	 //@SuppressWarnings("unchecked")
	public boolean editnameConflict(Customer customer) {
		// TODO Auto-generated method stub
		boolean result = true;
		/*org.hibernate.Query query = (org.hibernate.Query) sessionFactory.getCurrentSession().createQuery("FROM Customer WHERE name =:customername and companyName =:companyname and id !="+ customer.getId());
		query.setParameter("customername", customer.getName());
		query.setParameter("companyname", customer.getCompanyName());
		List<Customer> customerList =  query.list();
		 if(customerList.isEmpty())
		 {
			 result = false;
		 }*/
		 
		return result;
	}

}
