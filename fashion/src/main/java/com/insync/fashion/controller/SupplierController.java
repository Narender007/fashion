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
import com.insync.fashion.model.Supplier;
import com.insync.fashion.service.SupplierService;


@RestController
public class SupplierController {
	
	public SupplierController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private SupplierService  supplierService;
	
	// create a supplier
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createSupplier/", method = RequestMethod.POST)
	    public ResponseEntity<String> createSupplier(@RequestBody Supplier supplier,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("supplierName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addSupplierExist(supplier);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to supplier
				   supplier.setStatus(1);
				   supplier.setCreatedBy((String) httpSession.getAttribute("supplierName"));
				   supplier.setCreatedDate(new java.util.Date());
				    supplierService.createSupplier(supplier);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "Supplier Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a Supplier ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateSupplier/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateSupplier(@RequestBody Supplier supplier,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("supplierName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editSupplierExist(supplier);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to suppliers
				   supplier.setStatus(1);
				   supplier.setModifiedBy((String) httpSession.getAttribute("supplierName"));
				   supplier.setModifiedDate(new java.util.Date());
				   supplierService.updateSupplier(supplier);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "Supplier Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All Supplier--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllSupplier/", method = RequestMethod.GET)
	    public ResponseEntity<List<Supplier>> listAllSupplier() {
	        List<Supplier> suppliers = supplierService.getAllSupplier();
	        if(suppliers.isEmpty()){
	            return new ResponseEntity<List<Supplier>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Supplier>>(suppliers, HttpStatus.OK);
	    }
	    
//-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getSupplierById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getSupplier(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching Supplier with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("supplierName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	Supplier supplier = supplierService.getSupplier(id);
	 	        if (supplier == null) {
	 	            System.out.println("Supplier with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No Supplier Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(supplier);
	 					obj.put("supplier", jsonInString);
	 				} catch (JsonProcessingException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	        }
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }	    
	   
	 // --------------------------Delete supplier-----------------------------------
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/deleteSupplier/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteSupplier(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting Supplier with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("supplierName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	Supplier supplier = supplierService.getSupplier(id);
	        	supplier.setStatus(2);
	        	supplier.setModifiedBy((String) httpSession.getAttribute("supplierName"));
	        	supplier.setModifiedDate(new java.util.Date());
	        	
	        	supplierService.updateSupplier(supplier);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", "Supplier Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addSupplierExist(Supplier supplier){
	    	String result = "no";
	    	   if(supplierService.isnameConflict(supplier))
	    	   {
	    		   System.out.println("A supplier with name " + supplier.getName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editSupplierExist(Supplier supplier){
	    	String result = "no";
	    	   
	    	   if(supplierService.editnameConflict(supplier))
	    	   {
	    		   System.out.println("A Supplier with supplier name " + supplier.getName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
