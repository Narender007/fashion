<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Fashion Store</title>
  
   <!-- css  -->
  <link href="resources/css/bootstrap.css" rel="stylesheet" />
  <link href="resources/css/angular-material.css" rel="stylesheet" />
  <link href="resources/css/styles.css" rel="stylesheet" />
  <link href="resources/css/font-awesome.css" rel="stylesheet" /> 
  
  <!-- datatable  -->
  <link href="resources/DataTables/datatables.min.css" rel="stylesheet" /> 
   
   <!-- JavaScript -->
   <script type="text/javascript" src="resources/js/jquery.min.js"></script>
   <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="resources/js/angular.min.js"></script>
   <script type="text/javascript" src="resources/js/angular-animate.min.js"></script>
   <script type="text/javascript" src="resources/js/angular-aria.min.js"></script>
   <script type="text/javascript" src="resources/js/angular-messages.min.js"></script>
   <script type="text/javascript" src="resources/js/angular-material.js"></script>
   <script type="text/javascript" src="resources/js/angular-ui-router.js"></script>
   <script type="text/javascript" src="resources/js/jquery.backstretch.min.js"></script>
   
   
   <!-- my js files -->
   <script type="text/javascript" src="resources/js/app.js"></script>
   <script type="text/javascript" src="resources/js/controller.js"></script>
   <script type="text/javascript" src="resources/js/service.js"></script>
   <script type="text/javascript" src="resources/js/custom.js"></script>
   
   <!-- datatable -->
   <script type="text/javascript" src="resources/DataTables/datatables.min.js"></script> 
</head>
<body ng-app="fashion_app" ng-controller="mother_controller">
      <div class="index-glass"></div>
      <div id="topView" ui-view>
        <!-- angular view will be  here -->
         
      </div>
</body>
</html>