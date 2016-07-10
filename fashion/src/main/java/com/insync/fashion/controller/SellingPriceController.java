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
import com.insync.fashion.model.SellingPrice;
import com.insync.fashion.service.SellingPriceService;


@RestController
public class SellingPriceController {
	
	public SellingPriceController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private SellingPriceService  priceService;
	
	// create a price
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createSellingPrice/", method = RequestMethod.POST)
	    public ResponseEntity<String> createSellingPrice(@RequestBody SellingPrice price,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addSellingPriceExist(price);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to price
				   price.setStatus(1);
				   price.setCreatedBy((String) httpSession.getAttribute("userName"));
				   price.setCreatedDate(new java.util.Date());
				    priceService.createSellingPrice(price);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "SellingPrice Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a SellingPrice ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateSellingPrice/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateSellingPrice(@RequestBody SellingPrice price,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editSellingPriceExist(price);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to sellingprices
				   price.setStatus(1);
				   price.setModifiedBy((String) httpSession.getAttribute("userName"));
				   price.setModifiedDate(new java.util.Date());
				   priceService.updateSellingPrice(price);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "SellingPrice Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All SellingPrice--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllSellingPrice/", method = RequestMethod.GET)
	    public ResponseEntity<List<SellingPrice>> listAllSellingPrice() {
	        List<SellingPrice> sellingprices = priceService.getAllSellingPrice();
	        if(sellingprices.isEmpty()){
	            return new ResponseEntity<List<SellingPrice>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<SellingPrice>>(sellingprices, HttpStatus.OK);
	    }
	    
//-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getSellingPriceById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getSellingPrice(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching SellingPrice with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	SellingPrice price = priceService.getSellingPrice(id);
	 	        if (price == null) {
	 	            System.out.println("SellingPrice with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No SellingPrice Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(price);
	 					obj.put("price", jsonInString);
	 				} catch (JsonProcessingException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	        }
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }	    
	   
	 // --------------------------Delete sellingprice-----------------------------------
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/deleteSellingPrice/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteSellingPrice(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting SellingPrice with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	SellingPrice price = priceService.getSellingPrice(id);
	        	price.setStatus(2);
	        	price.setModifiedBy((String) httpSession.getAttribute("userName"));
	        	price.setModifiedDate(new java.util.Date());
	        	
	        	priceService.updateSellingPrice(price);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", "SellingPrice Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addSellingPriceExist(SellingPrice price){
	    	String result = "no";
	    	   if(priceService.isnameConflict(price))
	    	   {
	    		   //System.out.println("A price with name " + price.getSellingPriceName() + " already exist"); 
	    		   return "RECORD CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editSellingPriceExist(SellingPrice price){
	    	String result = "no";
	    	   
	    	   if(priceService.editnameConflict(price))
	    	   {
	    		  // System.out.println("A SellingPrice with sellingprice name " + price.getSellingPriceName() + " already exist"); 
	    		   return "RECORD CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
