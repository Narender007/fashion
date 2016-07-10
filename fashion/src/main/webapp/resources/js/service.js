var path = "/fashion";

angular.module("fashion_services",[ ])
.service('loginService',['$http','$q',function($http,$q){
	return{
		  userlogin: function(data)
		  {
			  return $http.post(path + '/userLogin/',data)
			    .then(
			            function(response){
			                return response.data;
			            }, 
			            function(errResponse){
			                console.error('Error while loging in');
			                return $q.reject(errResponse);
			            }
			    );  
		  }
	}
}])

.service('usermasterservice',['$http','$q', function($http,$q){
  return{
	    fetchalluser: function(){
	    	 return $http.get(path + '/getAllUser/')
	    	   .then(
	    			   function(response){
	    				   return response.data;
	    			   },
	    			   function(errResponse){
	    				   console.error('Error while fetching users');
			                return $q.reject(errResponse);
	    			   });
	    },   // fetchalluser
  
       addUser: function(data){
    	   return $http.post(path + '/createUser/',data)
		    .then(
		            function(response){
		                return response.data;
		            }, 
		            function(errResponse){
		                console.error('Error while creating user');
		                return $q.reject(errResponse);
		            }
		    ); 
       },

      fetchUserByID: function (data) {
           return $http.get(path + '/getUserById/'+data)
               .then(
                   function(response){
                       return response.data;
                   },
                   function(errResponse){
                       console.error('Error while Geting user by id');
                       return $q.reject(errResponse);
                   }
               );
      }, // fetchUserByID

      editUser: function(data){
          return $http.post(path + '/updateUser/',data)
              .then(
                  function(response){
                      return response.data;
                  },
                  function(errResponse){
                      console.error('Error while updating user');
                      return $q.reject(errResponse);
                  }
              );
      },
      deleteUser: function (data) {
          return $http.get(path + '/deleteUser/'+data)
              .then(
                  function(response){
                      return response.data;
                  },
                  function(errResponse){
                      console.error('Error while Deleting  user');
                      return $q.reject(errResponse);
                  }
              );
      }, // DeleteUser
	  
  }// outer return end
}])
	
	.service('rolemasterservice',['$http','$q', function($http,$q){
		return{
			fetchallrole: function(){
				return $http.get(path + '/getAllRole/')
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while fetching role');
							return $q.reject(errResponse);
						});
			},   // fetchallrole

			addRole: function(data){
				return $http.post(path + '/createRole/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while creating Role');
							return $q.reject(errResponse);
						}
					);
			},

			fetchRoleByID: function (data) {
				return $http.get(path + '/getRoleById/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Geting Role by id');
							return $q.reject(errResponse);
						}
					);
			}, // fetchRoleByID

			editRole: function(data){
				return $http.post(path + '/updateRole/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating Role');
							return $q.reject(errResponse);
						}
					);
			},
			deleteRole: function (data) {
				return $http.get(path + '/deleteRole/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Deleting  Role');
							return $q.reject(errResponse);
						}
					);
			}, // DeleteRole

		}// outer return end
	}])

// store and warehouse services

	.service('sandwmasterservice',['$http','$q', function($http,$q){
		return{
			fetchallsandw: function(){
				return $http.get(path + '/getAllStore/')
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while fetching store');
							return $q.reject(errResponse);
						});
			},   // fetchallsandw

			addsandw: function(data){
				return $http.post(path + '/createStore/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while creating sandw');
							return $q.reject(errResponse);
						}
					);
			},

			fetchsandwByID: function (data) {
				return $http.get(path + '/getStoreById/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Geting sandw by id');
							return $q.reject(errResponse);
						}
					);
			}, // fetchRoleByID

			editsandw: function(data){
				return $http.post(path + '/updateStore/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating sandw');
							return $q.reject(errResponse);
						}
					);
			},
			deletesandw: function (data) {
				return $http.get(path + '/deleteRole/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Deleting  sandw');
							return $q.reject(errResponse);
						}
					);
			}, // Deletesandw

		}// outer return end
	}])

// store and warehouse service ends
// indoor location service

	.service('indoormasterservice',['$http','$q', function($http,$q){
		return{
			fetchalllocation: function(){
				return $http.get(path + '/getAllLocation/')
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while fetching Location');
							return $q.reject(errResponse);
						});
			},   // getAllLocation
			
			fetchallJoinlocation: function(){
				return $http.get(path + '/getAllJoinLocation/')
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while fetching Location');
							return $q.reject(errResponse);
						});
			},   // getAllJoinLocation
			

			addlocation: function(data){
				return $http.post(path + '/createLocation/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while creating location');
							return $q.reject(errResponse);
						}
					);
			},

			fetchlocationByID: function (data) {
				return $http.get(path + '/getlocationById/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Geting location by id');
							return $q.reject(errResponse);
						}
					);
			}, // fetchlocationByID

			editlocation: function(data){
				return $http.post(path + '/updateLocation/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating location');
							return $q.reject(errResponse);
						}
					);
			},
			deletelocation: function (data) {
				return $http.get(path + '/deleteLocation/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Deleting  location');
							return $q.reject(errResponse);
						}
					);
			}, // Deletelocation

		}// outer return end
	}])

// indoor location service ends
// unit mapping service start
	.service('unitMappingmasterservice',['$http','$q', function($http,$q){
		return{
			fetchallunitMapping: function(){
				return $http.get(path + '/getAllUnitMapping/')
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while fetching unit Mapping');
							return $q.reject(errResponse);
						});
			},   // fetchallunit

			addUnitMapping: function(data){
				return $http.post(path + '/createUnitMapping/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while creating unit Mapping');
							return $q.reject(errResponse);
						}
					);
			},

			fetchUnitMappingByID: function (data) {
				return $http.get(path + '/getUnitMappingById/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Geting Unit Mapping by id');
							return $q.reject(errResponse);
						}
					);
			}, // fetchRoleByID

			editUnitMapping: function(data){
				return $http.post(path + '/updateUnitMapping/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating Unit Mapping');
							return $q.reject(errResponse);
						}
					);
			},
			deleteUnitMapping: function (data) {
				return $http.get(path + '/deleteUnitMapping/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Deleting  Unit Mapping');
							return $q.reject(errResponse);
						}
					);
			}, // DeleteMapping

		}// outer return end
	}])

// unit mapping master ends
// unit  service starts
	.service('unitmasterservice',['$http','$q', function($http,$q){
		return{
			fetchallunit: function(){
				return $http.get(path + '/getAllUnit/')
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while fetching unit');
							return $q.reject(errResponse);
						});
			},   // fetchallunit

			addUnit: function(data){
				return $http.post(path + '/createUnit/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while creating unit');
							return $q.reject(errResponse);
						}
					);
			},

			fetchUnitByID: function (data) {
				return $http.get(path + '/getUnitById/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Geting Unit by id');
							return $q.reject(errResponse);
						}
					);
			}, // fetchRoleByID

			editUnit: function(data){
				return $http.post(path + '/updateUnit/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating Unit');
							return $q.reject(errResponse);
						}
					);
			},
			deleteUnit: function (data) {
				return $http.get(path + '/deleteUnit/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Deleting  Unit');
							return $q.reject(errResponse);
						}
					);
			}, // DeleteRole

		}// outer return end
	}])

// unit  ends
// brand service start
	.service('brandService',['$http','$q', function($http,$q){
		return{
			fetchallbrand: function(){
				return $http.get(path + '/getAllProductBrand/')
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while fetching Product Brand');
							return $q.reject(errResponse);
						});
			},   // fetchallunit

			addBrand: function(data){
				return $http.post(path + '/createProductBrand/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while creating Product Brand');
							return $q.reject(errResponse);
						}
					);
			},

			fetchBrandByID: function (data) {
				return $http.get(path + '/getProductBrandById/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Geting Product Brand by id');
							return $q.reject(errResponse);
						}
					);
			}, // fetchRoleByID

			editBrand: function(data){
				return $http.post(path + '/updateProductBrand/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating Product Brand');
							return $q.reject(errResponse);
						}
					);
			},
			deleteBrand: function (data) {
				return $http.get(path + '/deleteProductBrand/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Deleting  Product Brand');
							return $q.reject(errResponse);
						}
					);
			}, // DeleteBrand

		}// outer return end
	}])
//brand service ends

// brand type start
	.service('productTypeService',['$http','$q', function($http,$q){
		return{
			fetchallproductType: function(){
				return $http.get(path + '/getAllProductType/')
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while fetching Product Type');
							return $q.reject(errResponse);
						});
			},   // fetchallunit

			addProductType: function(data){
				return $http.post(path + '/createProductType/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while creating Product Type');
							return $q.reject(errResponse);
						}
					);
			},

			fetchProductTypeByID: function (data) {
				return $http.get(path + '/getProductTypeById/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Geting Product Type by id');
							return $q.reject(errResponse);
						}
					);
			}, // fetchRoleByID

			editProductType: function(data){
				return $http.post(path + '/updateProductType/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating Product Type');
							return $q.reject(errResponse);
						}
					);
			},
			deleteProductType: function (data) {
				return $http.get(path + '/deleteProductType/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Deleting  Product Type');
							return $q.reject(errResponse);
						}
					);
			}, // DeleteBrandType

		}// outer return end
	}])
// brand type service ends

// product service starts

	.service('productService',['$http','$q', function($http,$q){
		return{
			fetchallproduct: function(){
				return $http.get(path + '/getAllProduct/')
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while fetching Product');
							return $q.reject(errResponse);
						});
			},   // fetchallproduct

			addProduct: function(data){
				return $http.post(path + '/createProduct/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while creating Product ');
							return $q.reject(errResponse);
						}
					);
			},

			fetchProductByID: function (data) {
				return $http.get(path + '/getProductById/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Geting Product  by id');
							return $q.reject(errResponse);
						}
					);
			}, // fetchRoleByID

			editProduct: function(data){
				return $http.post(path + '/updateProduct/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating Product');
							return $q.reject(errResponse);
						}
					);
			},

			deleteProduct: function (data) {
				return $http.get(path + '/deleteProduct/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Deleting  Product ');
							return $q.reject(errResponse);
						}
					);
			}, // DeleteBrandType

		}// outer return end
	}])

// product service ends
//product sell service start
	.service('sellService',['$http','$q', function($http,$q){
		return{
			fetchall: function(){
				return $http.get(path + '/getAllSellingPrice/')
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while fetching Selling Price');
							return $q.reject(errResponse);
						});
			},   // fetchallproduct

			add: function(data){
				return $http.post(path + '/createSellingPrice/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while creating Selling Price ');
							return $q.reject(errResponse);
						}
					);
			},

			fetchByID: function (data) {
				return $http.get(path + '/getSellingPriceById/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Geting Product Selling Price  by id');
							return $q.reject(errResponse);
						}
					);
			}, // fetchRoleByID

			edit: function(data){
				return $http.post(path + '/updateSellingPrice/',data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while updating SellingPricecd');
							return $q.reject(errResponse);
						}
					);
			},

			delete: function (data) {
				return $http.get(path + '/deleteSellingPrice/'+data)
					.then(
						function(response){
							return response.data;
						},
						function(errResponse){
							console.error('Error while Deleting  SellingPrice ');
							return $q.reject(errResponse);
						}
					);
			}, // DeleteBrandType

		}// outer return end
	}])
//product sell service ends