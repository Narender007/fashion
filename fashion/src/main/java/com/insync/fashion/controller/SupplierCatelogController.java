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
import com.insync.fashion.model.SupplierCatelog;
import com.insync.fashion.service.SupplierCatelogService;


@RestController
public class SupplierCatelogController {
	
	public SupplierCatelogController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private SupplierCatelogService  catelogService;
	
	// create a catelog
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createSupplierCatelog/", method = RequestMethod.POST)
	    public ResponseEntity<String> createSupplierCatelog(@RequestBody SupplierCatelog catelog,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addSupplierCatelogExist(catelog);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to catelog
				   catelog.setStatus(1);
				   catelog.setCreatedBy((String) httpSession.getAttribute("userName"));
				   catelog.setCreatedDate(new java.util.Date());
				    catelogService.createSupplierCatelog(catelog);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "SupplierCatelog Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a SupplierCatelog ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateSupplierCatelog/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateSupplierCatelog(@RequestBody SupplierCatelog catelog,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editSupplierCatelogExist(catelog);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to suppliercatelogs
				   catelog.setStatus(1);
				   catelog.setModifiedBy((String) httpSession.getAttribute("userName"));
				   catelog.setModifiedDate(new java.util.Date());
				   catelogService.updateSupplierCatelog(catelog);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "SupplierCatelog Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All SupplierCatelog--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllSupplierCatelog/", method = RequestMethod.GET)
	    public ResponseEntity<List<SupplierCatelog>> listAllSupplierCatelog() {
	        List<SupplierCatelog> suppliercatelogs = catelogService.getAllSupplierCatelog();
	        if(suppliercatelogs.isEmpty()){
	            return new ResponseEntity<List<SupplierCatelog>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<SupplierCatelog>>(suppliercatelogs, HttpStatus.OK);
	    }
	    
//-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getSupplierCatelogById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getSupplierCatelog(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching SupplierCatelog with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	SupplierCatelog catelog = catelogService.getSupplierCatelog(id);
	 	        if (catelog == null) {
	 	            System.out.println("SupplierCatelog with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No SupplierCatelog Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(catelog);
	 					obj.put("catelog", jsonInString);
	 				} catch (JsonProcessingException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	        }
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }	    
	   
	 // --------------------------Delete suppliercatelog-----------------------------------
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/deleteSupplierCatelog/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteSupplierCatelog(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting SupplierCatelog with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	SupplierCatelog catelog = catelogService.getSupplierCatelog(id);
	        	catelog.setStatus(2);
	        	catelog.setModifiedBy((String) httpSession.getAttribute("userName"));
	        	catelog.setModifiedDate(new java.util.Date());
	        	
	        	catelogService.updateSupplierCatelog(catelog);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", "SupplierCatelog Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addSupplierCatelogExist(SupplierCatelog catelog){
	    	String result = "no";
	    	   if(catelogService.isnameConflict(catelog))
	    	   {
	    		  // System.out.println("A catelog with name " + catelog.getSupplierCatelogName() + " already exist"); 
	    		   return "RECORD CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editSupplierCatelogExist(SupplierCatelog catelog){
	    	String result = "no";
	    	   
	    	   if(catelogService.editnameConflict(catelog))
	    	   {
	    		   //System.out.println("A SupplierCatelog with suppliercatelog name " + catelog.getSupplierCatelogName() + " already exist"); 
	    		   return "RECORD CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
