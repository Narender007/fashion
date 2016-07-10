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
import com.insync.fashion.model.Product;
import com.insync.fashion.service.ProductService;


@RestController
public class ProductController {
	
	public ProductController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private ProductService  productService;
	
	// create a product
	   @SuppressWarnings("unchecked")
	   @RequestMapping(value = "/createProduct/", method = RequestMethod.POST)
	    public ResponseEntity<String> createProduct(@RequestBody Product product,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = addProductExist(product);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to product
				   product.setStatus(1);
				   product.setCreatedBy((String) httpSession.getAttribute("userName"));
				   product.setCreatedDate(new java.util.Date());
				    productService.createProduct(product);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "Product Created Successfully");
			   }
		   }
	         String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	
		// Update a Product ================
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateProduct/", method = RequestMethod.POST)
	    public ResponseEntity<String> updateProduct(@RequestBody Product product,    HttpServletRequest httpRequest) {
		   JSONObject obj  = new JSONObject();
		   HttpSession httpSession  = httpRequest.getSession(false);
		   if(httpSession.getAttribute("userName") == null){
			   obj.put("msgtype", "ERROR");
			   obj.put("msg", "You session has expired please login again");
		   }
		   else{
			   String checkResult = editProductExist(product);
			   if(checkResult != "no"){
				   obj.put("msgtype", "ERROR");
				   obj.put("msg", checkResult);
			   }
			   else{
				   // add missing parameters to products
				   product.setStatus(1);
				   product.setModifiedBy((String) httpSession.getAttribute("userName"));
				   product.setModifiedDate(new java.util.Date());
				   productService.updateProduct(product);
				   obj.put("msgtype", "SUCCESS");
				   obj.put("msg", "Product Updated Successfully");
			   }
		   }
	      String u = (String)   obj.toJSONString();
	        return new ResponseEntity<String>(u,HttpStatus.OK);
	    }
	   
	   
////-------------------Retrieve All Product--------------------------------------------------------
	      
	    @RequestMapping(value = "/getAllProduct/", method = RequestMethod.GET)
	    public ResponseEntity<List<Product>> listAllProduct() {
	        List<Product> products = productService.getAllProduct();
	        if(products.isEmpty()){
	            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	    }
	    
//-------------------Retrieve Single User--------------------------------------------------------
	      
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/getProductById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> getProduct(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Fetching Product with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	Product product = productService.getProduct(id);
	 	        if (product == null) {
	 	            System.out.println("Product with id " + id + " not found");
	 	            obj.put("msgtype", "ERROR");
	 				obj.put("msg", "No Product Found with Given ID");
	 	        }
	 	        else{
	 	        	obj.put("msgtype", "SUCCESS");
	 				obj.put("msg", "Fetch Success");
	 				
	 				ObjectMapper mapper = new ObjectMapper();
	 				try {
	 					String jsonInString = mapper.writeValueAsString(product);
	 					obj.put("product", jsonInString);
	 				} catch (JsonProcessingException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	 	        }
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }	    
	   
	 // --------------------------Delete product-----------------------------------
	    @SuppressWarnings("unchecked")
		@RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id, HttpServletRequest httpRequest) {
	    	JSONObject obj = new JSONObject();
	        System.out.println("Deleting Product with id " + id);
	        
	        HttpSession httpSession = httpRequest.getSession(false);
	        if(httpSession.getAttribute("userName") == null)
	        {
	        	obj.put("msgtype", "ERROR");
				obj.put("msg", "Your Session has expired please login to continue");
	        }else{
	        	Product product = productService.getProduct(id);
	        	product.setStatus(2);
	        	product.setModifiedBy((String) httpSession.getAttribute("userName"));
	        	product.setModifiedDate(new java.util.Date());
	        	
	        	productService.updateProduct(product);
	        	obj.put("msgtype", "SUCCESS");
				obj.put("msg", "Product Successfully Deleted");
	        }
	        String u = (String) obj.toJSONString();
	        return new ResponseEntity<String>(u, HttpStatus.OK);
	    }
	    

	    
	   
	   // extra functions ===================-----------------------------------------
	    private String addProductExist(Product product){
	    	String result = "no";
	    	   if(productService.isnameConflict(product))
	    	   {
	    		   System.out.println("A product with name " + product.getName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	   
	    	return result;
	    }
	    
	    private String editProductExist(Product product){
	    	String result = "no";
	    	   
	    	   if(productService.editnameConflict(product))
	    	   {
	    		   System.out.println("A Product with product name " + product.getName() + " already exist"); 
	    		   return "NAME CONFLICT";
	    	   }
	    	   
	    	return result;
	    }	    
	

}
