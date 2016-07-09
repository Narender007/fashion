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
							<label for="fromunit">From Unit</label>
							<select id ="fromunit" class="form-control" name="fromUnit"  ng-model="formdata.fromUnit"  required="">
								<option ng-repeat="unit in unitData" value="{{unit.id}}">{{unit.name}}</option>
							</select>
						<!-- error msg handling  -->
							<div ng-show="userform.$submitted || userform.fromUnit.$touched">
								<div class="alert alert-danger" ng-show="userform.fromUnit.$error.required" role="alert">From unit is required </div>
							</div>
						<!-- error msg handling  -->
						</div>

						<div class="col-md-6 col-xs-12 form-group">
							<label for="tounit">To Unit</label>
							<select id ="tounit" class="form-control" name="toUnit"  ng-model="formdata.toUnit" ng-disabled="toDataFlag"  required="">
								<option ng-repeat="unit in tounitData" value="{{unit.id}}">{{unit.name}}</option>
							</select>
						<!-- error msg handling  -->
							<div ng-show="userform.$submitted || userform.fromUnit.$touched">
								<div class="alert alert-danger" ng-show="userform.toUnit.$error.required" role="alert">To unit is required </div>
							</div>
						<!-- error msg handling  -->
						</div>
					</div>
					<!-- row 1 ends -->
				  <!-- row 2 -->
					<div class="row">
						<div class="col-md-6 col-xs-12 form-group">
							<label for="name">Conversion Factor</label>
							<input type="text" class="form-control" name="conversionFactor" ng-model="formdata.conversionFactor"  ng-model-options="{ updateOn: 'blur' }"  placeholder="conversion Factor value" required="">

							<!-- error msg handling  -->
							<div ng-show="userform.$submitted || userform.conversionFactor.$touched">
								<div class="alert alert-danger" ng-show="userform.conversionFactor.$error.required" role="alert">Data  is required </div>
								<div class="alert alert-danger" ng-show="userform.conversionFactor.$error.pattern" role="alert">Data  is Invalid </div>
							</div>
						<!-- error msg handling  -->
						</div>
						<div class="col-md-6 col-xs-12 form-group">
							<label for="Description">Description</label>
							<textarea class="form-control" name="Description" ng-model="formdata.description" rows="5" ng-model-options="{ updateOn: 'blur' }" placeholder=" Description/Note" ></textarea>
						</div>

	                </div>
	              <!-- row 2 ends -->

				   <!-- row 3 -->
		             <div class="row" style="text-align:center;">
		                 <input  type="button" class="btn btn-warning" ng-click="cancel()" value="Cancel" />
		                 <input  type="button" class="btn btn-warning" ng-click="reset(userform)" value="Reset" />
                         <input type="submit" class="btn btn-primary" ng-click="save(formdata,$event)" ng-disabled="userform.$invalid" value="Submit" />
		             </div>
		             <!-- row 3 ends -->
		     </form>
	      </div>
</div>