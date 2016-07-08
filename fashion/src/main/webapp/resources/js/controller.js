angular.module("fashion_controller",[ ])
.controller('mother_controller',['$scope','$mdDialog', '$mdMedia','$state','$rootScope',function($scope,$mdDialog, $mdMedia,$state,$rootScope){
	console.log("mother");
	// define sone regex for ng-pattern
	$scope.alphanumeric_nospecial = '/^[a-zA-Z0-9]+$/';
	$scope.number8 = '/^([0-9]{8})?$/';
	$rootScope.currentUser;
	
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

.controller('loginCtrl',['$scope','loginService','$state','$rootScope',function($scope,loginService,$state,$rootScope){
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
	   	                	$rootScope.currentUser = user;
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

.controller('lockCtrl',['$scope','$rootScope','$window',function($scope,$rootScope,$window){
	console.log("lockCtrl");
	//console.log($rootScope.currentUser);
	console.log("Locked user is " + $rootScope.currentUser.password);
	   
	 $( ".passwordlock" ).keyup(function() {
		  $scope.match($rootScope.currentUser.password,$scope.passwordll);
		});
	 $scope.match = function(cpass,pass){
		 console.log(cpass);
		  console.log(pass);
		  if(cpass == pass){
			  $window.history.back();
		  }
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


    .controller('roleMasterTableCtrl',['$scope','rolemasterservice','$state',function($scope,rolemasterservice,$state){
        console.log('roleMasterTableCtrl');

        $scope.initEvent = function(){
            $('#userTable tbody').on( 'click', '.tdelete', function () {
                console.log("delete called");
                var data = $scope.userTable.row( $(this).parents('tr') ).data();
                // call master confirm
                var msg = "Delete Role";
                var msgBody = "Are You Sure you want to delete Role " + data.roleName;
                $scope.masterConf(msg,msgBody,function(result){
                    if(result){
                        $scope.deleteRole(data.id);
                    }else{
                        // you decided not to delete
                    }
                });

            });

            $('#userTable tbody').on( 'click', '.tedit', function () {
                console.log("edit called");
                var data = $scope.userTable.row( $(this).parents('tr') ).data();
                $scope.editRole(data.id);
            });
        }

        // function for edit
        $scope.editRole = function(userid){
            // go to add or edit screen
            var param = {};
            param.id = userid;
            $state.go('admin.roleAddEdit',param);
        }

        $scope.deleteRole = function(id){
            console.log(id);
            rolemasterservice.deleteRole(id)
                .then(function(responseData){
                        if(responseData.msgtype == "ERROR")
                        {
                            $scope.masterAlert(responseData.msgtype,responseData.msg,null);
                        }
                        else{
                            console.log("Deleted successful");
                            $scope.masterCallbackAlert(responseData.msgtype,responseData.msg,null,function(result){
                                $scope.fetchAllRole();
                            });
                        }
                    },
                    function(errResponse){
                        $scope.masterAlert("ERROR","Unexpected Error Occured",null);
                    });

        }



        $scope.userTable = null;
        $scope.fetchAllRole = function(){
            if($scope.userTable != null)
            {
                $scope.userTable.destroy();
            }
            // call service methord
            rolemasterservice.fetchallrole()
                .then(
                    function(responseData){
                        console.log(responseData);
                        $scope.userTable = $('#userTable').DataTable( {
                            "data":   responseData ,
                            "destroy": true,
                            "columns": [
                                { "data": "id" },
                                { "data": "roleName" },
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

        $scope.fetchAllRole();

    }]) // user master table control

    .controller('roleMasterFormCtrl',['$scope','rolemasterservice','$stateParams','$state',function($scope,rolemasterservice,$stateParams,$state){
        console.log("roleMasterFormCtrl");
        $scope.roleData = {};
        if($stateParams.id == -1){
            $scope.roleOption = "Add";
        }
        else{
            $scope.roleOption = "Edit";
            rolemasterservice.fetchRoleByID($stateParams.id)
                .then(
                    function(responseData){
                        if(responseData.msgtype == "ERROR")
                        {
                            $scope.masterCallbackAlert(responseData.msgtype,responseData.msg,null,function(result){
                                $state.go('admin.rolemastertable');
                            });
                        }
                        else{
                            var data = JSON.parse(responseData.role);
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
            $scope.formdata = angular.copy($scope.roleData);
        };

        $scope.cancel = function(){
            var msg = "Cancel";
            var msgBody =  "Are you sure You want to cancel " +$scope.roleOption + " process";
            // call the master confirm dialog
            $scope.masterConf(msg,msgBody,function(result){
                console.log(result);
                if(result){
                    $state.go('admin.rolemastertable');
                }else{
                    // you decided not to cancel the process
                }
            });
        };

        $scope.addRole  = function(user,ev){
            // add user
            //call the add service
            rolemasterservice.addRole(user)
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
                                $state.go('admin.rolemastertable');
                            });
                        }
                    },
                    function(errResponse){
                        $scope.masterAlert("ERROR","Unexpected Error Occured",ev);
                    }
                );
        };

        // edit user
        $scope.editRole  = function(user,ev){
            // edit role
            //call the add service
            rolemasterservice.editRole(user)
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
                                $state.go('admin.rolemastertable');
                            });
                        }
                    },
                    function(errResponse){
                        $scope.masterAlert("ERROR","Unexpected Error Occured",ev);
                    }
                );
        };


        /// submit function
        $scope.save = function(role,ev){
            $scope.roledata = angular.copy(role);
            console.log($scope.roledata);
            if($scope.roleOption == "Add"){
                $scope.addRole($scope.roledata,ev);
            }
            else{
                $scope.editRole($scope.roledata,ev);
            }
        }

    }]) // role master form control

  // store and warehouse controllers

	.controller('sandwMasterTableCtrl',['$scope','sandwmasterservice','$state',function($scope,sandwmasterservice,$state){
		console.log('sandwMasterTableCtrl');

		$scope.initEvent = function(){
			$('#userTable tbody').on( 'click', '.tdelete', function () {
				console.log("delete called");
				var data = $scope.userTable.row( $(this).parents('tr') ).data();
				// call master confirm
				var msg = "Delete Information";
				var msgBody = "Are You Sure you want to delete  " + data.type  + " " +data.name;
				$scope.masterConf(msg,msgBody,function(result){
					if(result){
						$scope.deletesandw(data.id);
					}else{
						// you decided not to delete
					}
				});

			});

			$('#userTable tbody').on( 'click', '.tedit', function () {
				console.log("edit called");
				var data = $scope.userTable.row( $(this).parents('tr') ).data();
				$scope.editsandw(data.id);
			});
		}

		// function for edit
		$scope.editsandw = function(userid){
			// go to add or edit screen
			var param = {};
			param.id = userid;
			$state.go('admin.sandwAddEdit',param);
		}

		$scope.deletesandw = function(id){
			console.log(id);
			sandwmasterservice.deletesandw(id)
				.then(function(responseData){
						if(responseData.msgtype == "ERROR")
						{
							$scope.masterAlert(responseData.msgtype,responseData.msg,null);
						}
						else{
							console.log("Deleted successful");
							$scope.masterCallbackAlert(responseData.msgtype,responseData.msg,null,function(result){
								$scope.fetchAllsandw();
							});
						}
					},
					function(errResponse){
						$scope.masterAlert("ERROR","Unexpected Error Occured",null);
					});

		}



		$scope.userTable = null;
		$scope.fetchAllsandw = function(){
			if($scope.userTable != null)
			{
				$scope.userTable.destroy();
			}
			// call service methord
			sandwmasterservice.fetchallsandw()
				.then(
					function(responseData){
						console.log(responseData);
						$scope.userTable = $('#userTable').DataTable( {
							"data":   responseData ,
							"destroy": true,
							"columns": [
								{ "data": "name" },
								{ "data": "type" },
								{ "data": "flourCount" },
								{ "data": "address.addressline1" },
								{ "data": "address.city" },
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

		$scope.fetchAllsandw();

	}]) // user master table control

	.controller('sandwMasterFormCtrl',['$scope','sandwmasterservice','$stateParams','$state',function($scope,sandwmasterservice,$stateParams,$state){
		console.log("sandwMasterFormCtrl");
		$scope.sandwData = {};
		if($stateParams.id == -1){
			$scope.sandwOption = "Add";
		}
		else{
			$scope.sandwOption = "Edit";
			sandwmasterservice.fetchsandwByID($stateParams.id)
				.then(
					function(responseData){
						if(responseData.msgtype == "ERROR")
						{
							$scope.masterCallbackAlert(responseData.msgtype,responseData.msg,null,function(result){
								$state.go('admin.sandwmastertable');
							});
						}
						else{
							var data = JSON.parse(responseData.store);
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
			$scope.formdata = angular.copy($scope.sandwData);
		};

		$scope.cancel = function(){
			var msg = "Cancel";
			var msgBody =  "Are you sure You want to cancel " +$scope.sandwOption + " process";
			// call the master confirm dialog
			$scope.masterConf(msg,msgBody,function(result){
				console.log(result);
				if(result){
					$state.go('admin.sandwmastertable');
				}else{
					// you decided not to cancel the process
				}
			});
		};

		$scope.addsandw  = function(user,ev){
			// add sandw
			//call the add service
			console.log("add store form submit data");
			  console.log(user);
			sandwmasterservice.addsandw(user)
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
								$state.go('admin.sandwmastertable');
							});
						}
					},
					function(errResponse){
						$scope.masterAlert("ERROR","Unexpected Error Occured",ev);
					}
				);
		};

		// edit sandw
		$scope.editsandw  = function(user,ev){
			// edit role
			//call the add service
			sandwmasterservice.editsandw(user)
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
								$state.go('admin.sandwmastertable');
							});
						}
					},
					function(errResponse){
						$scope.masterAlert("ERROR","Unexpected Error Occured",ev);
					}
				);
		};


		/// submit function
		$scope.save = function(role,ev){
			$scope.sandwdata = angular.copy(role);
			console.log($scope.sandwdata);
			if($scope.sandwOption == "Add"){
				$scope.addsandw($scope.sandwdata,ev);
			}
			else{
				$scope.editsandw($scope.sandwdata,ev);
			}
		}

	}]) // store and warehouse master form control

// Indoor controllers

	.controller('indoorMasterTableCtrl',['$scope','indoormasterservice','$state',function($scope,indoormasterservice,$state){
		console.log('indoorMasterTableCtrl');

		$scope.initEvent = function(){
			$('#userTable tbody').on( 'click', '.tdelete', function () {
				console.log("delete called");
				var data = $scope.userTable.row( $(this).parents('tr') ).data();
				// call master confirm
				var msg = "Delete Indoor Location";
				var msgBody = "Are You Sure you want to delete Indoor Location " + data.name;
				$scope.masterConf(msg,msgBody,function(result){
					if(result){
						$scope.deleteLocation(data.id);
					}else{
						// you decided not to delete
					}
				});

			});

			$('#userTable tbody').on( 'click', '.tedit', function () {
				console.log("edit called");
				var data = $scope.userTable.row( $(this).parents('tr') ).data();
				$scope.editLocation(data.id);
			});
		}

		// function for edit
		$scope.editLocation = function(userid){
			// go to add or edit screen
			var param = {};
			param.id = userid;
			$state.go('admin.indoorAddEdit',param);
		}

		$scope.deleteLocation = function(id){
			console.log(id);
			indoormasterservice.deletelocation(id)
				.then(function(responseData){
						if(responseData.msgtype == "ERROR")
						{
							$scope.masterAlert(responseData.msgtype,responseData.msg,null);
						}
						else{
							console.log("Deleted successful");
							$scope.masterCallbackAlert(responseData.msgtype,responseData.msg,null,function(result){
								$scope.fetchAllLocation();
							});
						}
					},
					function(errResponse){
						$scope.masterAlert("ERROR","Unexpected Error Occured",null);
					});

		}



		$scope.userTable = null;
		$scope.fetchAllLocation = function(){
			if($scope.userTable != null)
			{
				$scope.userTable.destroy();
			}
			// call service methord
			indoormasterservice.fetchalllocation()
				.then(
					function(responseData){
						console.log(responseData);
						$scope.userTable = $('#userTable').DataTable( {
							"data":   responseData ,
							"destroy": true,
							"columns": [
								{ "data": "id" },
								{ "data": "name" },
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

		$scope.fetchAllLocation();

	}]) // user master table control

	.controller('indoorMasterFormCtrl',['$scope','indoormasterservice','$stateParams','$state',function($scope,indoormasterservice,$stateParams,$state){
		console.log("indoorMasterFormCtrl");
		$scope.locationData = {};
		if($stateParams.id == -1){
			$scope.locationOption = "Add";
		}
		else{
			$scope.locationOption = "Edit";
			indoormasterservice.fetchlocationByID($stateParams.id)
				.then(
					function(responseData){
						if(responseData.msgtype == "ERROR")
						{
							$scope.masterCallbackAlert(responseData.msgtype,responseData.msg,null,function(result){
								$state.go('admin.indoormastertable');
							});
						}
						else{
							var data = JSON.parse(responseData.location);
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
			$scope.formdata = angular.copy($scope.locationData);
		};

		$scope.cancel = function(){
			var msg = "Cancel";
			var msgBody =  "Are you sure You want to cancel " +$scope.locationOption + " process";
			// call the master confirm dialog
			$scope.masterConf(msg,msgBody,function(result){
				console.log(result);
				if(result){
					$state.go('admin.indoormastertable');
				}else{
					// you decided not to cancel the process
				}
			});
		};

		$scope.addLocation  = function(user,ev){
			// add user
			//call the add service
			indoormasterservice.addlocation(user)
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
								$state.go('admin.indoormastertable');
							});
						}
					},
					function(errResponse){
						$scope.masterAlert("ERROR","Unexpected Error Occured",ev);
					}
				);
		};

		// edit user
		$scope.editLocation  = function(user,ev){
			// edit role
			//call the add service
			indoormasterservice.editlocation(user)
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
								$state.go('admin.indoormastertable');
							});
						}
					},
					function(errResponse){
						$scope.masterAlert("ERROR","Unexpected Error Occured",ev);
					}
				);
		};


		/// submit function
		$scope.save = function(role,ev){
			$scope.roledata = angular.copy(role);
			console.log($scope.roledata);
			if($scope.roleOption == "Add"){
				$scope.addLocation($scope.roledata,ev);
			}
			else{
				$scope.editLocation($scope.roledata,ev);
			}
		}

	}]) // role master form control

