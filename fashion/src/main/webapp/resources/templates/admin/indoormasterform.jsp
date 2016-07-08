<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <div class="panel panel-default md-whiteframe-12dp formPanel">
	      <div class="panel-heading">
	        {{locationOption}} Indoor Location
	      </div>
		  <div class="panel-body">
		    <form name="userform" class="user-form" novalidate>
				  <!-- row 1 -->
		             <div class="row">
		                <div class="col-md-6 col-xs-12 form-group">
		                    <label for="name">Name</label>
				            <input type="text" class="form-control" name="name" ng-model="formdata.name"  ng-model-options="{ updateOn: 'blur' }"  placeholder="Location Name" required="">
		                
		                    <!-- error msg handling  -->
						    <div ng-show="userform.$submitted || userform.name.$touched">
						      <div class="alert alert-danger" ng-show="userform.name.$error.required" role="alert">Name  is required </div>
						      <div class="alert alert-danger" ng-show="userform.name.$error.pattern" role="alert">Name  is Invalid </div>
						    </div>
						   <!-- error msg handling  --> 
		                </div>
		                
		                <div class="col-md-6 col-xs-12 form-group">
							<label for="placeId">Location Belongs To : </label>
							<select class="form-control" name="placeId" ng-model="formdata.placeId"  required="">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="4">5</option>
							</select>
		                 <!-- error msg handling  -->
						    <div ng-show="userform.$submitted || userform.placeId.$touched">
						      <div class="alert alert-danger" ng-show="userform.placeId.$error.required" role="alert">Field is required </div>
						    </div>
						   <!-- error msg handling  --> 
		                </div>
		             </div>
				  <!-- row 1 ends -->
				  
				  <!-- row 2 -->
		             <div class="row">
		                <div class="col-md-6 col-xs-12 form-group">
							<label for="floorNo">Location is on Floor : </label>
							<select class="form-control" name="floorNo" ng-model="formdata.floorNo"  required="">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="4">5</option>
							</select>
		                  <!-- error msg handling  -->
						    <div ng-show="userform.$submitted || userform.floorNo.$touched">
						      <div class="alert alert-danger" ng-show="userform.floorNo.$error.required" role="alert">Field  is required </div>
						    </div>
						   <!-- error msg handling  --> 
		                </div>
		                <div class="col-md-6 col-xs-12 form-group">
							<label for="Description">Description</label>
							<textarea class="form-control" name="Description" ng-model="formdata.description" rows="5" ng-model-options="{ updateOn: 'blur' }" placeholder="Location Description/Note" ></textarea>
		                </div>
		             </div>
				  <!-- row 2 ends -->
				  

				  
				   <!-- row 4 -->
		             <div class="row" style="text-align:center;">
		                 <input  type="button" class="btn btn-warning" ng-click="cancel()" value="Cancel" />
		                 <input  type="button" class="btn btn-warning" ng-click="reset(userform)" value="Reset" />
                         <input type="submit" class="btn btn-primary" ng-click="save(formdata,$event)" ng-disabled="userform.$invalid" value="Submit" />
		             </div>
		             <!-- row 4 ends -->
		     </form>
	      </div>
</div>