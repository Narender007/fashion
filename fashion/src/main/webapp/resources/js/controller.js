angular.module("fashion_controller",[ ])
.controller('mother_controller',['$scope','$mdDialog', '$mdMedia','$state',function($scope,$mdDialog, $mdMedia,$state){
	console.log("mother");
	// define sone regex for ng-pattern
	$scope.alphanumeric_nospecial = '/^[a-zA-Z0-9]+$/';
	$scope.number8 = '/^([0-9]{8})?$/';
	
	// Define Global alert function
	$scope.masterAlert = function(type,msg,ev){
		$mdDialog.show(
			      $mdDialog.alert()
			        .clickOutsideToClose(false)
			        .title(type)
			        .textContent(msg)
			        .ariaLabel(type)
			        .ok('Got it!')
			        .targetEvent(ev)
			    );
	};
	// alert with callback
	$scope.masterCallbackAlert = function(type,msg,ev,callback){
		$mdDialog.show(
			      $mdDialog.alert()
			        .clickOutsideToClose(false)
			        .title(type)
			        .textContent(msg)
			        .ariaLabel(type)
			        .ok('Got it!')
			        .targetEvent(ev)
			    ).then(function() {
			      callback(true);
			    });
	};
	
	$scope.masterConf = function(msg,msgBody,callback){
			    var confirm = $mdDialog.confirm()
			          .title(msg)
			          .textContent(msgBody)
			          .ariaLabel(msg)
			          .ok('OK')
			          .cancel('Cancel');
			    $mdDialog.show(confirm).then(function() {
			      callback(true);
			    }, function() {
			      callback(false);
			    });
	};
	
	
}])

.controller('loginCtrl',['$scope','loginService','$state',function($scope,loginService,$state){
	console.log("login ctrl");
	$scope.userData = {};
	
	$scope.reset = function(form){
		if (form) {
		      form.$setPristine();
		      form.$setUntouched();
		    }
		    $scope.formdata = angular.copy($scope.userData);
	}
	
    $scope.login = function(userdata,ev){
    	$scope.userData = angular.copy(userdata);
    	//console.log($scope.userData);
    	loginService.userlogin($scope.userData)
	   	 .then(
	   	            function(responseData){
	   	                if(responseData.msgtype == "ERROR")
	   	                	{
	   	                	 // error in login
	   	                	 $scope.masterAlert(responseData.msgtype,responseData.msg,ev);
	   	                	}
	   	                else{
	   	                	console.log("Login successful");
	   	                	var user = JSON.parse(responseData.user);
	   	                	if(user.userRole == "1")
	   	                		{
	   	                		  $state.go('admin.dashbord');
	   	                		}
	   	                }
	   	            }, 
	   	            function(errResponse){
	   	              $scope.masterAlert("ERROR","Unexpected Error Occured",ev);
	   	            }
	   	    );
    }
	
  	
}])

.controller('adminDashCtrl',['$scope',function($scope){
	console.log("adminDashCtrl");
	
	/*$( ".dropdown" ).click(function() {
		  console.log($(this).find(".dropdown-menu"));
		  //$(this).find(".dropdown-menu").toggle();
		});
	*/
	
}])

.controller('userMasterTableCtrl',['$scope','usermasterservice','$state',function($scope,usermasterservice,$state){
	 console.log('userMasterTableCtrl');
	
	 $scope.initEvent = function(){
		 $('#userTable tbody').on( 'click', '.tdelete', function () {
				console.log("delete called");
				var data = $scope.userTable.row( $(this).parents('tr') ).data();
				// call master confirm 
				var msg = "Delete User";
				var msgBody = "Are You Sure you want to delete user " + data.userName;
				$scope.masterConf(msg,msgBody,function(result){
					if(result){
						$scope.deleteUser(data.id);
					}else{
						// you decided not to delete
					}
				});
				
			});
		 
		 $('#userTable tbody').on( 'click', '.tedit', function () {
				console.log("edit called");
				var data = $scope.userTable.row( $(this).parents('tr') ).data();
				  $scope.editUser(data.id);
			});
	 }
	 
	 // function for edit
	 $scope.editUser = function(userid){
		 // go to add or edit screen
		 var param = {};
		 param.id = userid;
		 $state.go('admin.userAddEdit',param);
	 }
	 
	 $scope.deleteUser = function(id){
		 console.log(id);
		 usermasterservice.deleteUser(id)
			 .then(function(responseData){
					 if(responseData.msgtype == "ERROR")
					 {
						 $scope.masterAlert(responseData.msgtype,responseData.msg,null);
					 }
					 else{
						 console.log("Deleted successful");
						 $scope.masterCallbackAlert(responseData.msgtype,responseData.msg,null,function(result){
							 $scope.fetchAllUser();
						 });
					 }
				 },
				 function(errResponse){
					 $scope.masterAlert("ERROR","Unexpected Error Occured",null);
				 });
		 
	 }
	 
	 
	 
	   $scope.userTable = null;
	   $scope.fetchAllUser = function(){
		  if($scope.userTable != null)
			  {
			  $scope.userTable.destroy();
			  }
		  // call service methord
		  usermasterservice.fetchalluser()
		  .then(
	   	            function(responseData){
	   	            	console.log(responseData);
	   	            	$scope.userTable = $('#userTable').DataTable( {
	   						"data":   responseData ,
	   						"destroy": true,
	   						"columns": [
	   						            { "data": "userName" },
	   						            { "data": "userId" },
	   						            { "data": "userRole" },
	   						            { "data": "phone" },
	   						            { "data": "email" },
	   						            { "data": null }
	   						          ],
	   						"columnDefs": [ {
	   							"targets": -1,
	   							"data": null,
	   							"defaultContent": "<button class='tedit'>Edit</button><button class='tdelete'>Delete</button>"
	   						}
	   						]
	   						
	   						
	   					});
	   	         	// Now initialize click event for edit and delete
	   	            	$scope.initEvent();
	   	            }, 
	   	            function(errResponse){
	   	              $scope.masterAlert("ERROR","Unexpected Error Occured",null);
	   	            }
	   	    );
	 };
	 
	 $scope.fetchAllUser();
	 
}]) // user master table control

.controller('userMasterFormCtrl',['$scope','usermasterservice','$stateParams','$state',function($scope,usermasterservice,$stateParams,$state){
	console.log("userMasterFormCtrl");
	$scope.userData = {};
	if($stateParams.id == -1){
		$scope.userOption = "Add";
	}
	else{
		$scope.userOption = "Edit";
		usermasterservice.fetchUserByID($stateParams.id)
			.then(
				function(responseData){
					if(responseData.msgtype == "ERROR")
					{
						$scope.masterCallbackAlert(responseData.msgtype,responseData.msg,null,function(result){
							$state.go('admin.usermastertable');
						});
					}
					else{
						 var data = JSON.parse(responseData.user);
						console.log(data);
						$scope.formdata = angular.copy(data);
						setTimeout(function(){
							$scope.$apply();
						},300)
					}
				},
				function(errResponse){
					$scope.masterAlert("ERROR","Unexpected Error Occured",null);
				}
			);
	}
	
	// Reset Function
	$scope.reset  = function(userform)
	{
		if (userform) {
			userform.$setPristine();
			userform.$setUntouched();
		    }
		    $scope.formdata = angular.copy($scope.userData);
	};
	
	$scope.cancel = function(){
		var msg = "Cancel";
		var msgBody =  "Are you sure You want to cancel " +$scope.userOption + " process";
		// call the master confirm dialog  
		$scope.masterConf(msg,msgBody,function(result){
			console.log(result);
			if(result){
				$state.go('admin.usermastertable');
			}else{
				// you decided not to cancel the process
			}
		});
	};
	
	$scope.addUser  = function(user,ev){
		// add user
		//call the add service
		usermasterservice.addUser(user)
		.then(
   	            function(responseData){
   	            	if(responseData.msgtype == "ERROR")
	                	{
	                	 // error in login
	                	 $scope.masterAlert(responseData.msgtype,responseData.msg,ev);
	                	}
	                else{
	                	console.log("Add successful");
	                	$scope.masterCallbackAlert(responseData.msgtype,responseData.msg,ev,function(result){
	                		$state.go('admin.usermastertable');
	                	});
	                }
   	            }, 
   	            function(errResponse){
   	              $scope.masterAlert("ERROR","Unexpected Error Occured",ev);
   	            }
   	    );
	};

	// edit user
	$scope.editUser  = function(user,ev){
		// edit user
		//call the add service
		usermasterservice.editUser(user)
			.then(
				function(responseData){
					if(responseData.msgtype == "ERROR")
					{
						// error in login
						$scope.masterAlert(responseData.msgtype,responseData.msg,ev);
					}
					else{
						console.log("Edit successful");
						$scope.masterCallbackAlert(responseData.msgtype,responseData.msg,ev,function(result){
							$state.go('admin.usermastertable');
						});
					}
				},
				function(errResponse){
					$scope.masterAlert("ERROR","Unexpected Error Occured",ev);
				}
			);
	};
	
	
	/// submit function
	$scope.save = function(user,ev){
		$scope.userdata = angular.copy(user);
		console.log($scope.userdata);
		if($scope.userOption == "Add"){
			$scope.addUser($scope.userdata,ev);
		}
		else{
			$scope.editUser($scope.userdata,ev);
		}
	}
	
}]) // user master form control 