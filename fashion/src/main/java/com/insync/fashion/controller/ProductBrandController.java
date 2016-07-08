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
import com.insync.fashion.model.ProductBrand;
import com.insync.fashion.service.ProductBrandService;


@RestController
public class ProductBrandController {
	
	public ProductBrandController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private ProductBrandService  brandService;
	
	// create a brand
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createProductBrand/", method = RequestMethod.POST)
	    public ResponseEntity<String> createProductBrand(@RequestBody ProductBrand brand,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("productbrandName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addProductBrandExist(brand);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to brand
				   brand.setStatus(1);
				   brand.setCreatedBy((String) httpSession.getAttribute("productbrandName"));
				   brand.setCreatedDate(new java.util.Date());
				    brandService.createProductBrand(brand);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "ProductBrand Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a ProductBrand ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateProductBrand/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateProductBrand(@RequestBody ProductBrand brand,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("productbrandName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editProductBrandExist(brand);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to productbrands
				   brand.setStatus(1);
				   brand.setModifiedBy((String) httpSession.getAttribute("productbrandName"));
				   brand.setModifiedDate(new java.util.Date());
				   brandService.updateProductBrand(brand);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "ProductBrand Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All ProductBrand--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllProductBrand/", method = RequestMethod.GET)
	    public ResponseEntity<List<ProductBrand>> listAllProductBrand() {
	        List<ProductBrand> productbrands = brandService.getAllProductBrand();
	        if(productbrands.isEmpty()){
	            return new ResponseEntity<List<ProductBrand>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<ProductBrand>>(productbrands, HttpStatus.OK);
	    }
	    
//-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getProductBrandById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getProductBrand(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching ProductBrand with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("productbrandName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	ProductBrand brand = brandService.getProductBrand(id);
	 	        if (brand == null) {
	 	            System.out.println("ProductBrand with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No ProductBrand Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(brand);
	 					obj.put("brand", jsonInString);
	 				} catch (JsonProcessingException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	        }
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }	    
	   
	 // --------------------------Delete productbrand-----------------------------------
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/deleteProductBrand/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteProductBrand(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting ProductBrand with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("productbrandName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	ProductBrand brand = brandService.getProductBrand(id);
	        	brand.setStatus(2);
	        	brand.setModifiedBy((String) httpSession.getAttribute("productbrandName"));
	        	brand.setModifiedDate(new java.util.Date());
	        	
	        	brandService.updateProductBrand(brand);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", "ProductBrand Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addProductBrandExist(ProductBrand brand){
	    	String result = "no";
	    	   if(brandService.isnameConflict(brand))
	    	   {
	    		   System.out.println("A brand with name " + brand.getName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editProductBrandExist(ProductBrand brand){
	    	String result = "no";
	    	   
	    	   if(brandService.editnameConflict(brand))
	    	   {
	    		   System.out.println("A ProductBrand with productbrand name " + brand.getName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
