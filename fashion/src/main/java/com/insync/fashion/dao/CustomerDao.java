package com.insync.fashion.dao;

import java.util.List;
import com.insync.fashion.model.Customer;

public interface CustomerDao {
	public long createCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public void deleteCustomer(long id);
	public List<Customer> getAllCustomer();
	public Customer getCustomer(long id);
    
    
    // for add
    public boolean isnameConflict(Customer customer);
    
    
    // for edit
    public boolean editnameConflict(Customer customer);
    
}
