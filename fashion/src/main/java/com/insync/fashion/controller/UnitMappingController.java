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
import com.insync.fashion.model.UnitMapping;
import com.insync.fashion.service.UnitMappingService;


@RestController
public class UnitMappingController {
	
	public UnitMappingController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private UnitMappingService  unitMappingService;
	
	// create a unitMapping
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createUnitMapping/", method = RequestMethod.POST)
	    public ResponseEntity<String> createUnitMapping(@RequestBody UnitMapping unitMapping,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addUnitMappingExist(unitMapping);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to unitMapping
				   unitMapping.setStatus(1);
				   unitMapping.setCreatedBy((String) httpSession.getAttribute("unit MappingName"));
				   unitMapping.setCreatedDate(new java.util.Date());
				    unitMappingService.createUnitMapping(unitMapping);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "UnitMapping Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a UnitMapping ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateUnitMapping/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateUnitMapping(@RequestBody UnitMapping unitMapping,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editUnitMappingExist(unitMapping);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to unit Mappings
				   unitMapping.setStatus(1);
				   unitMapping.setModifiedBy((String) httpSession.getAttribute("unit MappingName"));
				   unitMapping.setModifiedDate(new java.util.Date());
				   unitMappingService.updateUnitMapping(unitMapping);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "UnitMapping Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All UnitMapping--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllUnitMapping/", method = RequestMethod.GET)
	    public ResponseEntity<List<UnitMapping>> listAllUnitMapping() {
	        List<UnitMapping> unitMappings = unitMappingService.getAllUnitMapping();
	        if(unitMappings.isEmpty()){
	            return new ResponseEntity<List<UnitMapping>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<UnitMapping>>(unitMappings, HttpStatus.OK);
	    }
	    
//-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getUnitMappingById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getUnitMapping(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching UnitMapping with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", "You session has expired please login again");
			   }else{
	        	UnitMapping unitMapping = unitMappingService.getUnitMapping(id);
	 	        if (unitMapping == null) {
	 	            System.out.println("UnitMapping with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No UnitMapping Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(unitMapping);
	 					obj.put("unitMapping", jsonInString);
	 				} catch (JsonProcessingException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	        }
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }	    
	   
	 // --------------------------Delete unit Mapping-----------------------------------
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/deleteUnitMapping/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteUnitMapping(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting UnitMapping with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", "You session has expired please login again");
			   }else{
	        	UnitMapping unitMapping = unitMappingService.getUnitMapping(id);
	        	unitMapping.setStatus(2);
	        	unitMapping.setModifiedBy((String) httpSession.getAttribute("unit MappingName"));
	        	unitMapping.setModifiedDate(new java.util.Date());
	        	
	        	unitMappingService.updateUnitMapping(unitMapping);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", "UnitMapping Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addUnitMappingExist(UnitMapping unitMapping){
	    	String result = "no";
	    	   if(unitMappingService.isnameConflict(unitMapping))
	    	   {
	    		   //System.out.println("A unit Mapping with name " + unitMapping.getUnitMappingName() + " already exist"); 
	    		   return " CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editUnitMappingExist(UnitMapping unitMapping){
	    	String result = "no";
	    	   
	    	   if(unitMappingService.editnameConflict(unitMapping))
	    	   {
	    		  // System.out.println("A UnitMapping with unit Mapping name " + unitMapping.getUnitMappingName() + " already exist"); 
	    		   return " CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
