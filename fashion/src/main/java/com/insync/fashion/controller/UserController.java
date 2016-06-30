package com.insync.fashion.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpRequest;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.insync.fashion.model.User;
import com.insync.fashion.service.UserService;

@RestController
public class UserController {
	
	public UserController() {
		// TODO Auto-generated constructor stub
		System.out.println("UserController Constructor");
	}
	
	@Autowired
	private UserService  userService;
	
	// create a user
	   @SuppressWarnings("unchecked")
	@RequestMapping(value = "/createUser/", method = RequestMethod.POST)
	    public ResponseEntity<String> createUser(@RequestBody User user,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addUserExist(user);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to users
				   user.setStatus(1);
				   user.setCreatedBy((String) httpSession.getAttribute("userName"));
				   user.setCreatedDate(new java.util.Date());
				  long no =  userService.createUser(user);
				  System.out.println("user created ===" + no);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "User Created Successfully");
			   }
		   }
	     String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a user ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateUser/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateUser(@RequestBody User user,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editUserExist(user);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to users
				   user.setStatus(1);
				   user.setModifiedBy((String) httpSession.getAttribute("userName"));
				   user.setModifiedDate(new java.util.Date());
				   userService.updateUser(user);
				  System.out.println("user updated ===" );
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "User Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
// //-------------------Retrieve All Users--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllUser/", method = RequestMethod.GET)
	    public ResponseEntity<List<User>> listAllUsers() {
	        List<User> users = userService.getAllUser();
	        if(users.isEmpty()){
	            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	    }
	    
 //-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getUser(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching User with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	 User user = userService.getUser(id);
	 	        if (user == null) {
	 	            System.out.println("User with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No User Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(user);
	 					obj.put("user", jsonInString);
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
		@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteUser(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting User with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	User user = userService.getUser(id);
	        	user.setStatus(2);
	        	user.setModifiedBy((String) httpSession.getAttribute("userName"));
	        	user.setModifiedDate(new java.util.Date());
	        	
	        	userService.updateUser(user);
	        	obj.put("msgtype", "SUCCESS");
 				obj.put("msg", "User Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    
// --------------------------login-----------------------------------
	    @RequestMapping(value = "/userLogin/",method = RequestMethod.POST)
	     public ResponseEntity<String> userLogin(@RequestBody String data,HttpServletRequest httpRequest)
	     {  
	    	HttpSession httpSession  = httpRequest.getSession(true);
	    	
	    	String u = null;
	    	try {
	    	JSONParser parser = new JSONParser();
	    	JSONObject obj;
			
				obj = (JSONObject) parser.parse(data);
			
	    	String userid = (String) obj.get("userid");
	    	String password = (String) obj.get("password");
	    	System.out.println("userid:" + userid);
	    	System.out.println("password:" + password);
	        JSONObject user = userService.userLogin(userid, password);
	         u = user.toJSONString();
	           if(user.get("msgtype") == "SUCCESS"){
	        	   //parse the user to get back name
	        	   ObjectMapper mapper = new ObjectMapper();
	        	    String rr = (String) user.get("user");
	        	   User reuser = mapper.readValue(rr, User.class);
	        	   httpSession.setAttribute("userName", reuser.getUserName());
	        	   httpSession.setAttribute("userRole", reuser.getUserRole());
	           }
	    	} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 return new ResponseEntity<String>(u, HttpStatus.OK);
	     }
	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addUserExist(User user){
	    	String result = "no";
	    	   if(userService.isnameConflict(user))
	    	   {
	    		   System.out.println("A User with name " + user.getUserName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   else if(userService.isemailConflict(user))
	    	   {
	    		   System.out.println("A User with email " + user.getEmail() + " already exist"); 
	    		   return "EMAIL CONFLICT";
	    	   }
	    	   else if(userService.isuserIdConflict(user))
	    	   {
	    		   System.out.println("A User with userId " + user.getUserId() + " already exist"); 
	    		   return "UserID CONFLICT";
	    	   }
	    	   
	    	return result;
	    }
	    
	    private String editUserExist(User user){
	    	String result = "no";
	    	   if(userService.edituserIdConflict(user))
	    	   {
	    		   System.out.println("A User with userId " + user.getUserId() + " already exist"); 
	    		   return "UserID CONFLICT";
	    	   }
	    	   else if(userService.editnameConflict(user))
	    	   {
	    		   System.out.println("A User with user name " + user.getUserName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   else if(userService.editemailConflict(user))
	    	   {
	    		   System.out.println("A User with email " + user.getEmail() + " already exist"); 
	    		   return "EMAIL CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
