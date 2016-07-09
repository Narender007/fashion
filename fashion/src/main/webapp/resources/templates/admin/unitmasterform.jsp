<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <div class="panel panel-default md-whiteframe-12dp formPanel">
	      <div class="panel-heading">
	        {{rleOption}} Role
	      </div>
		  <div class="panel-body">
		    <form name="userform" class="user-form" novalidate>
				  <!-- row 1 -->
		             <div class="row">
		                <div class="col-md-6 col-xs-12 form-group">
		                    <label for="userName">Unit Name</label>
				            <input type="text" class="form-control" name="name" ng-model="formdata.name"  ng-model-options="{ updateOn: 'blur' }" placeholder="Unit name" required="">
		               
		                 <!-- error msg handling  -->
						    <div ng-show="userform.$submitted || userform.name.$touched">
						      <div class="alert alert-danger" ng-show="userform.name.$error.required" role="alert">Unit name is required </div>
						    </div>
						   <!-- error msg handling  --> 
		                </div>
						<div class="col-md-6 col-xs-12 form-group">
							<label for="Description">Description</label>
							<textarea class="form-control" name="Description" ng-model="formdata.description" rows="5" ng-model-options="{ updateOn: 'blur' }" placeholder="Unit Description" ></textarea>
						</div>

					</div>
				  <!-- row 1 ends -->

				   <!-- row 2 -->
		             <div class="row" style="text-align:center;">
		                 <input  type="button" class="btn btn-warning" ng-click="cancel()" value="Cancel" />
		                 <input  type="button" class="btn btn-warning" ng-click="reset(userform)" value="Reset" />
                         <input type="submit" class="btn btn-primary" ng-click="save(formdata,$event)" ng-disabled="userform.$invalid" value="Submit" />
		             </div>
		             <!-- row 2 ends -->
		     </form>
	      </div>
</div>