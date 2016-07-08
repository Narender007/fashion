package com.insync.fashion.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insync.fashion.model.Customer;
import com.insync.fashion.service.CustomerService;


@RestController
public class CustomerController {
	
	public CustomerController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private CustomerService  customerService;
	
	// create a customer
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createCustomer/", method = RequestMethod.POST)
	    public ResponseEntity<String> createCustomer(@RequestBody Customer customer,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("customerName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addCustomerExist(customer);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to customer
				   customer.setStatus(1);
				   customer.setCreatedBy((String) httpSession.getAttribute("customerName"));
				   customer.setCreatedDate(new java.util.Date());
				    customerService.createCustomer(customer);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "Customer Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a Customer ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateCustomer/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("customerName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editCustomerExist(customer);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to customers
				   customer.setStatus(1);
				   customer.setModifiedBy((String) httpSession.getAttribute("customerName"));
				   customer.setModifiedDate(new java.util.Date());
				   customerService.updateCustomer(customer);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "Customer Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All Customer--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllCustomer/", method = RequestMethod.GET)
	    public ResponseEntity<List<Customer>> listAllCustomer() {
	        List<Customer> customers = customerService.getAllCustomer();
	        if(customers.isEmpty()){
	            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	    }
	    
//-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getCustomerById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getCustomer(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching Customer with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("customerName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	Customer customer = customerService.getCustomer(id);
	 	        if (customer == null) {
	 	            System.out.println("Customer with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No Customer Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(customer);
	 					obj.put("customer", jsonInString);
	 				} catch (JsonProcessingException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	        }
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }	    
	   
	 // --------------------------Delete customer-----------------------------------
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/deleteCustomer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting Customer with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("customerName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	Customer customer = customerService.getCustomer(id);
	        	customer.setStatus(2);
	        	customer.setModifiedBy((String) httpSession.getAttribute("customerName"));
	        	customer.setModifiedDate(new java.util.Date());
	        	
	        	customerService.updateCustomer(customer);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", "Customer Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addCustomerExist(Customer customer){
	    	String result = "no";
	    	   if(customerService.isnameConflict(customer))
	    	   {
	    		   System.out.println("A customer with name " + customer.getName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editCustomerExist(Customer customer){
	    	String result = "no";
	    	   
	    	   if(customerService.editnameConflict(customer))
	    	   {
	    		   System.out.println("A Customer with customer name " + customer.getName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
