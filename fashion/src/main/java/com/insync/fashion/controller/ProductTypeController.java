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
import com.insync.fashion.model.ProductType;
import com.insync.fashion.service.ProductTypeService;


@RestController
public class ProductTypeController {
	
	public ProductTypeController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private ProductTypeService  typeService;
	
	// create a type
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createProductType/", method = RequestMethod.POST)
	    public ResponseEntity<String> createProductType(@RequestBody ProductType type,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addProductTypeExist(type);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to type
				   type.setStatus(1);
				   type.setCreatedBy((String) httpSession.getAttribute("userName"));
				   type.setCreatedDate(new java.util.Date());
				    typeService.createProductType(type);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "ProductType Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a ProductType ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateProductType/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateProductType(@RequestBody ProductType type,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editProductTypeExist(type);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to producttypes
				   type.setStatus(1);
				   type.setModifiedBy((String) httpSession.getAttribute("userName"));
				   type.setModifiedDate(new java.util.Date());
				   typeService.updateProductType(type);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "ProductType Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All ProductType--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllProductType/", method = RequestMethod.GET)
	    public ResponseEntity<List<ProductType>> listAllProductType() {
	        List<ProductType> producttypes = typeService.getAllProductType();
	        if(producttypes.isEmpty()){
	            return new ResponseEntity<List<ProductType>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<ProductType>>(producttypes, HttpStatus.OK);
	    }
	    
//-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getProductTypeById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getProductType(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching ProductType with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	ProductType type = typeService.getProductType(id);
	 	        if (type == null) {
	 	            System.out.println("ProductType with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No ProductType Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(type);
	 					obj.put("type", jsonInString);
	 				} catch (JsonProcessingException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	        }
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }	    
	   
	 // --------------------------Delete producttype-----------------------------------
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/deleteProductType/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteProductType(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting ProductType with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	ProductType type = typeService.getProductType(id);
	        	type.setStatus(2);
	        	type.setModifiedBy((String) httpSession.getAttribute("userName"));
	        	type.setModifiedDate(new java.util.Date());
	        	
	        	typeService.updateProductType(type);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", "ProductType Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addProductTypeExist(ProductType type){
	    	String result = "no";
	    	   if(typeService.isnameConflict(type))
	    	   {
	    		   System.out.println("A type with name " + type.getName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editProductTypeExist(ProductType type){
	    	String result = "no";
	    	   
	    	   if(typeService.editnameConflict(type))
	    	   {
	    		   System.out.println("A ProductType with producttype name " + type.getName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
