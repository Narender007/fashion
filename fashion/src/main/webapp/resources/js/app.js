
var app = angular.module("fashion_app",['ui.router','ngMaterial','fashion_controller','fashion_services']);

app.config(function($stateProvider, $urlRouterProvider) {
	  //
	  // For any unmatched url, redirect to /state1
	  $urlRouterProvider.otherwise("/login");
	  //
	  // Now set up the states
	  $stateProvider
	  
	    .state('login', {
	      url: "/login",
	      templateUrl: "resources/templates/login.jsp",
	      controller:"loginCtrl"
	    })

          .state('lockScreen', {
              url: "/lockScreen",
              templateUrl: "resources/templates/lock.jsp",
              controller:"lockCtrl"
          })
	    
	    .state('admin', {
		      url: "/admin",
		      abstract:true,
		      templateUrl: "resources/templates/admin.jsp"
		    })
	  
	    .state('admin.dashbord', {
		      url: "/adminDash",
		      templateUrl: "resources/templates/admin/dashbord.jsp",
		      controller:"adminDashCtrl"
		    })
		
		.state('admin.usermastertable',{
			  url:"/userMaster",
			  templateUrl:"resources/templates/admin/usermastertable.jsp",
			  controller:"userMasterTableCtrl"
		})
		
		.state('admin.userAddEdit',{
			  url:"/userForm/:id",
			  templateUrl:"resources/templates/admin/usermasterform.jsp",
			  controller:"userMasterFormCtrl"
		})

		  .state('admin.rolemastertable',{
			  url:"/roleMaster",
			  templateUrl:"resources/templates/admin/rolemastertable.jsp",
			  controller:"roleMasterTableCtrl"
		  })

		  .state('admin.roleAddEdit',{
			  url:"/roleForm/:id",
			  templateUrl:"resources/templates/admin/rolemasterform.jsp",
			  controller:"roleMasterFormCtrl"
		  })

     //// warehouse and store controlling views
		  .state('admin.sandwmastertable',{
			  url:"/sandwMaster",
			  templateUrl:"resources/templates/admin/sandwmastertable.jsp",
			  controller:"sandwMasterTableCtrl"
		  })

		  .state('admin.sandwAddEdit',{
			  url:"/sandwForm/:id",
			  templateUrl:"resources/templates/admin/sandwmasterform.jsp",
			  controller:"sandwMasterFormCtrl"
		  })

// state difinition for indoorLocation templates
          .state('admin.indoormastertable',{
              url:"/indoorMaster",
              templateUrl:"resources/templates/admin/indoormastertable.jsp",
              controller:"indoorMasterTableCtrl"
          })

          .state('admin.indoorAddEdit',{
              url:"/indoorForm/:id",
              templateUrl:"resources/templates/admin/indoormasterform.jsp",
              resolve:{
            	  simpleObj:  function(){
                      return {value: 'simple!'};
                   },
                   storeData:  function($http,sandwmasterservice){
                       return sandwmasterservice.fetchallsandw()
                       .then (function (data) {
                           return {store:data};
                       },
                       function(errResponse){
                            return {store:{ }};
                       });
                    }, 
              },
              controller:"indoorMasterFormCtrl"
          })




});


