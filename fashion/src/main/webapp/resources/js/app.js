
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
		  
		    
	  
	});


