package com.insync.fashion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insync.fashion.dao.CustomerDao;
import com.insync.fashion.model.Customer;
import com.insync.fashion.service.CustomerService;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	   public CustomerServiceImpl() {
		// TODO Auto-generated constructor stub
		   System.out.println("CustomerServiceImpl constructor");
	}
	   
	   @Autowired
		 private CustomerDao customerDao;

	@Override
	public long createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.createCustomer(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.updateCustomer(customer);
	}

	@Override
	public void deleteCustomer(long id) {
		// TODO Auto-generated method stub
		customerDao.deleteCustomer(id);
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerDao.getAllCustomer();
	}

	@Override
	public Customer getCustomer(long id) {
		// TODO Auto-generated method stub
		return customerDao.getCustomer(id);
	}

	@Override
	public boolean isnameConflict(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.isnameConflict(customer);
	}

	@Override
	public boolean editnameConflict(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.editnameConflict(customer);
	}

}
