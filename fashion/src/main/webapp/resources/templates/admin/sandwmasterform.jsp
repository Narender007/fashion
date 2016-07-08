<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<form name="userform" class="user-form" novalidate>
		  <div class="panel panel-default md-whiteframe-12dp formPanel">
				  <div class="panel-heading">
					{{sandwOption}} Store
				  </div>
				  <div class="panel-body">

						  <!-- row 1 -->
							 <div class="row">
								<div class="col-md-6 col-xs-12 form-group">
									<label for="name">Name</label>
									<input type="text" class="form-control" name="name" ng-model="formdata.name"  ng-model-options="{ updateOn: 'blur' }"  placeholder="Store/Warehouse Name" required="">

									<!-- error msg handling  -->
									<div ng-show="userform.$submitted || userform.name.$touched">
									  <div class="alert alert-danger" ng-show="userform.name.$error.required" role="alert">Name is required </div>
									  <div class="alert alert-danger" ng-show="userform.name.$error.pattern" role="alert">Name  is Invalid </div>
									</div>
								   <!-- error msg handling  -->
								</div>

								<div class="col-md-6 col-xs-12 form-group">
									<label for="Type">Type</label>
									<select class="form-control" name="type" ng-model="formdata.type" ng-model-options="{ updateOn: 'blur' }" required="">
										<option value="store">Store</option>
										<option value="warehouse">WareHouse</option>
									</select>
								 <!-- error msg handling  -->
									<div ng-show="userform.$submitted || userform.type.$touched">
									  <div class="alert alert-danger" ng-show="userform.type.$error.required" role="alert">type is required </div>
									</div>
								   <!-- error msg handling  -->
								</div>
							 </div>
						  <!-- row 1 ends -->

						  <!-- row 2 -->
							 <div class="row">
								<div class="col-md-6 col-xs-12 form-group">
								   <label for="flourCount">Flour Count</label>
								   <input type="number" class="form-control" name="flourCount" ng-model="formdata.flourCount"  ng-model-options="{ updateOn: 'blur' }" placeholder="flour Count" required="">

								  <!-- error msg handling  -->
									<div ng-show="userform.$submitted || userform.Password.$touched">
									  <div class="alert alert-danger" ng-show="userform.flourCount.$error.required" role="alert">flour Count  is required </div>
									</div>
								   <!-- error msg handling  -->
								</div>
								<div class="col-md-6 col-xs-12 form-group">
								   <label for="Description">Description</label>
								   <textarea class="form-control" name="Description" ng-model="formdata.description" rows="5" ng-model-options="{ updateOn: 'blur' }" placeholder="Store/Warehouse Description" ></textarea>

								</div>
							 </div>
						  <!-- row 2 ends -->


				  </div>
		</div>

		<div class="panel panel-default md-whiteframe-12dp formPanel">
	         <div class="panel-heading">
				Address Detail
	          </div>
	          <div class="panel-body">

				<!-- row 1 -->
				<div class="row">
					<div class="col-md-6 col-xs-12 form-group">
						<label for="Address Line 1">Address line 1 </label>
						<input type="text" class="form-control" name="addressline1" ng-model="formdata.address.addressline1"  ng-model-options="{ updateOn: 'blur' }"  placeholder="Address Line 1" >
					</div>

					<div class="col-md-6 col-xs-12 form-group">
						<label for="Address Line 2">Address Line 2</label>
						<input type="text" class="form-control" name="addressline2" ng-model="formdata.address.addressline2"  ng-model-options="{ updateOn: 'blur' }"  placeholder="Address Line 2" >
					</div>
				</div>
				<!-- row 1 ends -->

				<!-- row 2 -->
				<div class="row">
					<div class="col-md-6 col-xs-12 form-group">
						<label for="Street">Street</label>
						<input type="text" class="form-control" name="Street" ng-model="formdata.address.street"  ng-model-options="{ updateOn: 'blur' }" placeholder="Street" >
					</div>
					<div class="col-md-6 col-xs-12 form-group">
						<label for="City">City</label>
						<input type="text" class="form-control" name="City" ng-model="formdata.address.city"  ng-model-options="{ updateOn: 'blur' }" placeholder="City">
					</div>
				</div>
				<!-- row 2 ends -->

				<!-- row 3 -->
				<div class="row">
					<div class="col-md-6 col-xs-12 form-group">
						<label for="State">Country</label>
						<input type="text" class="form-control" name="State" ng-model="formdata.address.state"  ng-model-options="{ updateOn: 'blur' }" placeholder="State" >
					</div>
					<div class="col-md-6 col-xs-12 form-group">
						<label for="ZipCode">ZipCode</label>
						<input type="tel" class="form-control" name="ZipCode" ng-model="formdata.address.zipcode"  ng-model-options="{ updateOn: 'blur' }" placeholder="ZipCode">
					</div>
				</div>
				<!-- row 3 ends -->

	          </div>
		</div>

	    <div class="panel panel-default md-whiteframe-12dp formPanel">
	           <div class="panel-body">
					<!-- row 4 -->
					<div class="row" style="text-align:center;">
					<input  type="button" class="btn btn-warning" ng-click="cancel()" value="Cancel" />
					<input  type="button" class="btn btn-warning" ng-click="reset(userform)" value="Reset" />
					<input type="submit" class="btn btn-primary" ng-click="save(formdata,$event)" ng-disabled="userform.$invalid" value="Submit" />
					</div>
					<!-- row 4 ends -->
	           </div>
	    </div>

	</form>