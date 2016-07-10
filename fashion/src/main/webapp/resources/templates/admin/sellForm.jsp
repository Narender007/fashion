<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <div class="panel panel-default md-whiteframe-12dp formPanel">
	      <div class="panel-heading">
	        {{sellingOption}} Selling Price
	      </div>
		  <div class="panel-body">
		    <form name="userform" class="user-form" novalidate>
				  <!-- row 1 -->
		             <div class="row">
		                <div class="col-md-6 col-xs-12 form-group">
		                    <label for="Product">Product</label>
							<select class="form-control" name="product" ng-model="formdata.productId"  required="">
								<option ng-repeat="data in product" value="{{data.id}}">{{data.name}}</option>
							</select>
							<!-- error msg handling  -->
							<div ng-show="userform.$submitted || userform.product.$touched">
								<div class="alert alert-danger" ng-show="userform.product.$error.required" role="alert">Field is required </div>
							</div>
							<!-- error msg handling  -->
		                </div>
						<div class="col-md-6 col-xs-12 form-group">
							<label for="unit">Unit</label>
							<select class="form-control" name="unit" ng-model="formdata.unit"  required="">
								<option ng-repeat="data in unitData" value="{{data.id}}">{{data.name}}</option>
							</select>
						<!-- error msg handling  -->
							<div ng-show="userform.$submitted || userform.unit.$touched">
								<div class="alert alert-danger" ng-show="userform.unit.$error.required" role="alert">Field is required </div>
							</div>
						<!-- error msg handling  -->
						</div>
		             </div>
				  <!-- row 1 ends -->
					<!-- row 2 -->
						<div class="col-md-6 col-xs-12 form-group">
							<label for="price">Price (Per unit)</label>
							<input type="text" class="form-control" name="price" ng-model="formdata.Price"  ng-model-options="{ updateOn: 'blur' }" placeholder="Price" required="">

						<!-- error msg handling  -->
							<div ng-show="userform.$submitted || userform.price.$touched">
								<div class="alert alert-danger" ng-show="userform.Price.$error.required" role="alert">Price is required </div>
							</div>
						<!-- error msg handling  -->
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