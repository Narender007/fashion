package com.insync.fashion.controller;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insync.fashion.model.StoreAndWarehouse;
import com.insync.fashion.service.StoreAndWarehouseService;


@RestController
public class StoreAndWarehouseController {
	
	public StoreAndWarehouseController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private StoreAndWarehouseService  swService;
	
	// create a role
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createStore/", method = RequestMethod.POST)
	    public ResponseEntity<String> createStore(@RequestBody StoreAndWarehouse sw,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addSWExist(sw);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to role
				   sw.setStatus(1);
				   sw.setCreatedBy((String) httpSession.getAttribute("userName"));
				   sw.setCreatedDate(new java.util.Date());
				    swService.createStore(sw);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg","New " + sw.getType() + " Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a Store ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateStore/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateStore(@RequestBody StoreAndWarehouse sw,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editSWExist(sw);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to users
				   sw.setStatus(1);
				   sw.setModifiedBy((String) httpSession.getAttribute("userName"));
				   sw.setModifiedDate(new java.util.Date());
				   swService.updateStore(sw);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", sw.getType() + " Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All Store--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllStore/", method = RequestMethod.GET)
	    public ResponseEntity<List<StoreAndWarehouse>> listAllStore() {
	        List<StoreAndWarehouse> users = swService.getAllStore();
	        if(users.isEmpty()){
	            return new ResponseEntity<List<StoreAndWarehouse>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<StoreAndWarehouse>>(users, HttpStatus.OK);
	    }
	    
//-------------------Retrieve Single Store--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getStoreById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getStore(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching Store with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	StoreAndWarehouse sw = swService.getStore(id);
	 	        if (sw == null) {
	 	            System.out.println("Store with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No Store or Warehouse Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(sw);
	 					obj.put("store", jsonInString);
	 				} catch (JsonProcessingException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	        }
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }	    
	   
	 // --------------------------Delete Store-----------------------------------
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/deleteStore/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteStore(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting Store with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	StoreAndWarehouse sw = swService.getStore(id);
	        	sw.setStatus(2);
	        	sw.setModifiedBy((String) httpSession.getAttribute("userName"));
	        	sw.setModifiedDate(new java.util.Date());
	        	
	        	swService.updateStore(sw);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", sw.getType() + " Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addSWExist(StoreAndWarehouse sw){
	    	String result = "no";
	    	   if(swService.isnameConflict(sw))
	    	   {
	    		   System.out.println("A "+sw.getType() +"  with name " + sw.getName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editSWExist(StoreAndWarehouse sw){
	    	String result = "no";
	    	   
	    	   if(swService.editnameConflict(sw))
	    	   {
	    		   System.out.println("A "+sw.getType() +"  with name " + sw.getName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
