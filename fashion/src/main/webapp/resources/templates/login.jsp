<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <div class="container">
       <div class="panel panel-default md-whiteframe-12dp loginformPanel">
		  <div class="panel-heading">User Login</div>
		  <div class="panel-body">
		  <!-- error msg handling  -->
		    <div ng-show="loginform.$submitted || loginform.userid.$touched">
		      <div class="alert alert-danger" ng-show="loginform.userid.$error.required" role="alert">UserId is required for login</div>
		     
		    </div>
		    
		    <div ng-show="loginform.$submitted || loginform.password.$touched">
		      <div class="alert alert-danger" ng-show="loginform.password.$error.required" role="alert">Password is required for login</div>
		    </div>
		    
		  <!-- error msg handling ends  -->
		  
		      <form name="loginform" class="login-form" novalidate>
		          <div class="form-group">
				    <label for="userID">UserID</label>
				    <input type="text" class="form-control" name="userid" ng-model="formdata.userid"  ng-model-options="{ updateOn: 'blur' }" placeholder="userid" required="">
				  </div>
				  <div class="form-group">
				    <label for="userID">Password</label>
				    <input type="password" class="form-control" name="password" ng-model="formdata.password"  ng-model-options="{ updateOn: 'blur' }" placeholder="password" required="">
				  </div>
				  
				  <div>
				      <input  type="button" class="btn btn-warning" ng-click="reset(loginform)" value="Reset" />
                      <input type="submit" class="btn btn-primary" ng-click="login(formdata,$event)" value="Login" />
				  </div>
		      
		      </form>
		  
		  </div>
		</div>
    
    
    </div>

    
    