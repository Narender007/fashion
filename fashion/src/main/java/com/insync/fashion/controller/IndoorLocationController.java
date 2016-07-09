package com.insync.fashion.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
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
import com.insync.fashion.model.IndoorLocation;
import com.insync.fashion.service.IndoorLocationService;


@RestController
public class IndoorLocationController {
	
	public IndoorLocationController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private IndoorLocationService  indoorService;
	
	// create a role
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createLocation/", method = RequestMethod.POST)
	    public ResponseEntity<String> createLocation(@RequestBody IndoorLocation il,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addILExist(il);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to role
				   il.setStatus(1);
				   il.setCreatedBy((String) httpSession.getAttribute("userName"));
				   il.setCreatedDate(new java.util.Date());
				    indoorService.createIndoorLocation(il);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "Indoor Location Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a Role ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateLocation/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateLocation(@RequestBody IndoorLocation il,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editILExist(il);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to users
				   il.setStatus(1);
				   il.setModifiedBy((String) httpSession.getAttribute("userName"));
				   il.setModifiedDate(new java.util.Date());
				   indoorService.updateIndoorLocation(il);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "Indoor Location Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All Role--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllLocation/", method = RequestMethod.GET)
	    public ResponseEntity<List<IndoorLocation>> listAllLocation() {
	        List<IndoorLocation> users = indoorService.getAllIndoorLocation();
	        if(users.isEmpty()){
	            return new ResponseEntity<List<IndoorLocation>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<IndoorLocation>>(users, HttpStatus.OK);
	    }
	    
////-------------------Retrieve All Indoor location join query with place id--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllJoinLocation/", method = RequestMethod.GET)
	    public ResponseEntity<String> getAllJoinLocation() {
	        JSONArray location = indoorService.getJoinLocation();
	        
	        String u = (String) location.toJSONString();
	        
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }	    
	    
//-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getlocationById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getLocation(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching Role with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	IndoorLocation il = indoorService.getIndoorLocation(id);
	 	        if (il == null) {
	 	            System.out.println("Location with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No Indoor Location Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(il);
	 					obj.put("location", jsonInString);
	 				} catch (JsonProcessingException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	        }
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }	    
	   
	 // --------------------------Delete user-----------------------------------
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/deleteLocation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteLocation(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting Location with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	IndoorLocation indoorLocation = indoorService.getIndoorLocation(id);
	        	indoorLocation.setStatus(2);
	        	indoorLocation.setModifiedBy((String) httpSession.getAttribute("userName"));
	        	indoorLocation.setModifiedDate(new java.util.Date());
	        	
	        	indoorService.updateIndoorLocation(indoorLocation);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", "Indoor Location Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addILExist(IndoorLocation location){
	    	String result = "no";
	    	   if(indoorService.isnameConflict(location))
	    	   {
	    		   System.out.println("A Location with  name " + location.getName() + " already exist in same Store/Warehouse"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editILExist(IndoorLocation location){
	    	String result = "no";
	    	   
	    	   if(indoorService.editnameConflict(location))
	    	   {
	    		   System.out.println("A Location with  name " + location.getName() + " already exist in same Store/Warehouse"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
