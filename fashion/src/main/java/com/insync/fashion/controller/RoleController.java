package com.insync.fashion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import com.insync.fashion.model.Role;
import com.insync.fashion.service.RoleService;


@RestController
public class RoleController {
	
	public RoleController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private RoleService  roleService;
	
	// create a role
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createRole/", method = RequestMethod.POST)
	    public ResponseEntity<String> createRole(@RequestBody Role role,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addRoleExist(role);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to role
				   role.setStatus(1);
				   role.setCreatedBy((String) httpSession.getAttribute("userName"));
				   role.setCreatedDate(new java.util.Date());
				   long no =  roleService.createRole(role);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "Role Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a Role ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateRole/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateRole(@RequestBody Role role,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editRoleExist(role);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to users
				   role.setStatus(1);
				   role.setModifiedBy((String) httpSession.getAttribute("userName"));
				   role.setModifiedDate(new java.util.Date());
				   roleService.updateRole(role);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "Role Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All Role--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllRole/", method = RequestMethod.GET)
	    public ResponseEntity<List<Role>> listAllRole() {
	        List<Role> users = roleService.getAllRole();
	        if(users.isEmpty()){
	            return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Role>>(users, HttpStatus.OK);
	    }
	    
//-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getRoleById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getRole(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching Role with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	Role role = roleService.getRole(id);
	 	        if (role == null) {
	 	            System.out.println("Role with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No Role Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(role);
	 					obj.put("role", jsonInString);
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
		@RequestMapping(value = "/deleteRole/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteRole(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting Role with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	Role role = roleService.getRole(id);
	        	role.setStatus(2);
	        	role.setModifiedBy((String) httpSession.getAttribute("userName"));
	        	role.setModifiedDate(new java.util.Date());
	        	
	        	roleService.updateRole(role);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", "Role Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addRoleExist(Role role){
	    	String result = "no";
	    	   if(roleService.isnameConflict(role))
	    	   {
	    		   System.out.println("A role with name " + role.getRoleName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editRoleExist(Role role){
	    	String result = "no";
	    	   
	    	   if(roleService.editnameConflict(role))
	    	   {
	    		   System.out.println("A Role with user name " + role.getRoleName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
