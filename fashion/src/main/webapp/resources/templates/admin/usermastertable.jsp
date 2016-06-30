<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
     
     <md-button class="md-fab md-primary navbar-right" aria-label="Use Android" ng-click="editUser(-1)">
          <i class="fa fa-user-plus"></i>
     </md-button>
 
  <div class="panel panel-default md-whiteframe-12dp tablePanel">
	      <div class="panel-heading">
	        All Users 
	      </div>
		  <div class="panel-body">
		     <table id="userTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
		        <thead>
		            <tr>
		                <th>Name</th>
		                <th>UserId</th>
		                <th>Role</th>
		                <th>Phone</th>
		                <th>Email</th>
		                <th>Action</th>
		             </tr>
		        </thead>
		        <tbody>
		        
		        </tbody>
		      </table>  
		  
	      </div>
</div>
  		  