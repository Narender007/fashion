var path = "http://192.168.1.10:2015/fashion";

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


