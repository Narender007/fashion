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
// unit service start
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


