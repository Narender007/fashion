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
import com.insync.fashion.model.AlertMaster;
import com.insync.fashion.service.AlertMasterService;


@RestController
public class AlertMasterController {
	
	public AlertMasterController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private AlertMasterService  alertService;
	
	// create a alert
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createAlertMaster/", method = RequestMethod.POST)
	    public ResponseEntity<String> createAlertMaster(@RequestBody AlertMaster alert,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("alertmasterName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addAlertMasterExist(alert);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to alert
				   alert.setStatus(1);
				   alert.setCreatedBy((String) httpSession.getAttribute("alertmasterName"));
				   alert.setCreatedDate(new java.util.Date());
				    alertService.createAlertMaster(alert);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "AlertMaster Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a AlertMaster ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateAlertMaster/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateAlertMaster(@RequestBody AlertMaster alert,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("alertmasterName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editAlertMasterExist(alert);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to alertmasters
				   alert.setStatus(1);
				   alert.setModifiedBy((String) httpSession.getAttribute("alertmasterName"));
				   alert.setModifiedDate(new java.util.Date());
				   alertService.updateAlertMaster(alert);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "AlertMaster Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All AlertMaster--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllAlertMaster/", method = RequestMethod.GET)
	    public ResponseEntity<List<AlertMaster>> listAllAlertMaster() {
	        List<AlertMaster> alertmasters = alertService.getAllAlertMaster();
	        if(alertmasters.isEmpty()){
	            return new ResponseEntity<List<AlertMaster>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<AlertMaster>>(alertmasters, HttpStatus.OK);
	    }
	    
//-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getAlertMasterById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getAlertMaster(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching AlertMaster with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("alertmasterName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	AlertMaster alert = alertService.getAlertMaster(id);
	 	        if (alert == null) {
	 	            System.out.println("AlertMaster with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No AlertMaster Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(alert);
	 					obj.put("alert", jsonInString);
	 				} catch (JsonProcessingException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	        }
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }	    
	   
	 // --------------------------Delete alertmaster-----------------------------------
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/deleteAlertMaster/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteAlertMaster(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting AlertMaster with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("alertmasterName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	AlertMaster alert = alertService.getAlertMaster(id);
	        	alert.setStatus(2);
	        	alert.setModifiedBy((String) httpSession.getAttribute("alertmasterName"));
	        	alert.setModifiedDate(new java.util.Date());
	        	
	        	alertService.updateAlertMaster(alert);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", "AlertMaster Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addAlertMasterExist(AlertMaster alert){
	    	String result = "no";
	    	   if(alertService.isnameConflict(alert))
	    	   {
	    		  // System.out.println("A alert with name " + alert.getAlertMasterName() + " already exist"); 
	    		   return "RECORD CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editAlertMasterExist(AlertMaster alert){
	    	String result = "no";
	    	   
	    	   if(alertService.editnameConflict(alert))
	    	   {
	    		   //System.out.println("A AlertMaster with alertmaster name " + alert.getAlertMasterName() + " already exist"); 
	    		   return "RECORD CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
