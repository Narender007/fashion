<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <div class="panel panel-default md-whiteframe-12dp formPanel">
	      <div class="panel-heading">
	        {{productOption}} Product
	      </div>
		  <div class="panel-body">
		    <form name="userform" class="user-form" novalidate>
				  <!-- row 1 -->
		             <div class="row">
		                <div class="col-md-6 col-xs-12 form-group">
		                    <label for="BrandName">Product Name</label>
				            <input type="text" class="form-control" name="ProductName" ng-model="formdata.name"  ng-model-options="{ updateOn: 'blur' }" placeholder="Product name" required="">
		               
		                 <!-- error msg handling  -->
						    <div ng-show="userform.$submitted || userform.ProductName.$touched">
						      <div class="alert alert-danger" ng-show="userform.ProductName.$error.required" role="alert">Product name is required </div>
						    </div>
						   <!-- error msg handling  --> 
		                </div>
						<div class="col-md-6 col-xs-12 form-group">
							<label for="BrandName">Product Type</label>
							<select class="form-control" name="productType" ng-model="formdata.productType"  required="">
								<option ng-repeat="type in productType" value="{{type.id}}">{{type.name}}</option>
							</select>
							<!-- error msg handling  -->
							<div ng-show="userform.$submitted || userform.productType.$touched">
							<div class="alert alert-danger" ng-show="userform.productType.$error.required" role="alert">Field is required </div>
							</div>
						</div>
		             </div>
				  <!-- row 1 ends -->
					<!-- row 2 -->
					<div class="row">
						<div class="col-md-6 col-xs-12 form-group">
							<label for="Product Brand"> Product Brand</label>
							<select class="form-control" name="productbrand" ng-model="formdata.productBran">
								<option ng-repeat="brand in productBrand" value="{{brand.id}}">{{brand.name}}</option>
							</select>
							<!-- error msg handling  -->
							<div ng-show="userform.$submitted || userform.productBran.$touched">
								<div class="alert alert-danger" ng-show="userform.productBran.$error.required" role="alert">Field  is required </div>
							</div>
							<!-- error msg handling  -->
						</div>
						<div class="col-md-6 col-xs-12 form-group">
							<label for="Description">Description</label>
							<textarea class="form-control" name="Description" ng-model="formdata.description" rows="5" ng-model-options="{ updateOn: 'blur' }" placeholder="Product Description/Note" ></textarea>
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