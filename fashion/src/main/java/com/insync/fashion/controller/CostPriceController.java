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
import com.insync.fashion.model.CostPrice;
import com.insync.fashion.service.CostPriceService;


@RestController
public class CostPriceController {
	
	public CostPriceController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private CostPriceService  priceService;
	
	// create a price
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createCostPrice/", method = RequestMethod.POST)
	    public ResponseEntity<String> createCostPrice(@RequestBody CostPrice price,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("sellingpriceName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addCostPriceExist(price);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to price
				   price.setStatus(1);
				   price.setCreatedBy((String) httpSession.getAttribute("sellingpriceName"));
				   price.setCreatedDate(new java.util.Date());
				    priceService.createCostPrice(price);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "CostPrice Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a CostPrice ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateCostPrice/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateCostPrice(@RequestBody CostPrice price,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("sellingpriceName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editCostPriceExist(price);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to costprices
				   price.setStatus(1);
				   price.setModifiedBy((String) httpSession.getAttribute("sellingpriceName"));
				   price.setModifiedDate(new java.util.Date());
				   priceService.updateCostPrice(price);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "CostPrice Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All CostPrice--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllCostPrice/", method = RequestMethod.GET)
	    public ResponseEntity<List<CostPrice>> listAllCostPrice() {
	        List<CostPrice> costprices = priceService.getAllCostPrice();
	        if(costprices.isEmpty()){
	            return new ResponseEntity<List<CostPrice>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<CostPrice>>(costprices, HttpStatus.OK);
	    }
	    
//-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getCostPriceById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getCostPrice(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching CostPrice with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("sellingpriceName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	CostPrice price = priceService.getCostPrice(id);
	 	        if (price == null) {
	 	            System.out.println("CostPrice with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No CostPrice Found with Given ID");
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
		@RequestMapping(value = "/deleteCostPrice/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteCostPrice(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting CostPrice with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("sellingpriceName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	CostPrice price = priceService.getCostPrice(id);
	        	price.setStatus(2);
	        	price.setModifiedBy((String) httpSession.getAttribute("sellingpriceName"));
	        	price.setModifiedDate(new java.util.Date());
	        	
	        	priceService.updateCostPrice(price);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", "CostPrice Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addCostPriceExist(CostPrice price){
	    	String result = "no";
	    	   if(priceService.isnameConflict(price))
	    	   {
	    		   //System.out.println("A price with name " + price.getCostPriceName() + " already exist"); 
	    		   return "RECORD CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editCostPriceExist(CostPrice price){
	    	String result = "no";
	    	   
	    	   if(priceService.editnameConflict(price))
	    	   {
	    		  // System.out.println("A CostPrice with sellingprice name " + price.getCostPriceName() + " already exist"); 
	    		   return "RECORD CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
