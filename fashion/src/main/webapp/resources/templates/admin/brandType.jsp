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
		                    <label for="BrandName">Brand Type Name</label>
				            <input type="text" class="form-control" name="BrandTypeName" ng-model="formdata.name"  ng-model-options="{ updateOn: 'blur' }" placeholder="Type name" required="">
		               
		                 <!-- error msg handling  -->
						    <div ng-show="userform.$submitted || userform.BrandTypeName.$touched">
						      <div class="alert alert-danger" ng-show="userform.BrandTypeName.$error.required" role="alert">Brand name is required </div>
						    </div>
						   <!-- error msg handling  --> 
		                </div>
						<div class="col-md-6 col-xs-12 form-group">
							<label for="subTypeOf">SubTypeOf : </label>
							<select class="form-control" name="SubTypeOf" ng-model="formdata.SubTypeOf"  required="">
								<option  value="0">Not Applicable</option>
								<option ng-repeat="type in productType" value="{{type.id}}">{{type.name}}</option>
							</select>
							<!-- error msg handling  -->
							<div ng-show="userform.$submitted || userform.SubTypeOf.$touched">
								<div class="alert alert-danger" ng-show="userform.SubTypeOf.$error.required" role="alert">Field is required </div>
							</div>
							<!-- error msg handling  -->
						</div>
		             </div>
				  <!-- row 1 ends -->

					<!-- row 2 -->
					<div class="row">
						<div class="col-md-6 col-xs-12 form-group">
							<label for="Description">Description</label>
							<textarea class="form-control" name="Description" ng-model="formdata.description" rows="5" ng-model-options="{ updateOn: 'blur' }" placeholder=" Description/Note" ></textarea>
						</div>
					</div>
					<!-- row 2 ends -->

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