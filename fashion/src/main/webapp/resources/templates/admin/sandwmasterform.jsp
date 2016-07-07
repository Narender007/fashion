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
									<label for="userID">UserID</label>
									<input type="text" class="form-control" name="userid" ng-model="formdata.userId"  ng-model-options="{ updateOn: 'blur' }"  placeholder="userid" required="">

									<!-- error msg handling  -->
									<div ng-show="userform.$submitted || userform.userid.$touched">
									  <div class="alert alert-danger" ng-show="userform.userid.$error.required" role="alert">UserId is required </div>
									  <div class="alert alert-danger" ng-show="userform.userid.$error.pattern" role="alert">UserID  is Invalid </div>
									</div>
								   <!-- error msg handling  -->
								</div>

								<div class="col-md-6 col-xs-12 form-group">
									<label for="userName">User Name</label>
									<input type="text" class="form-control" name="userName" ng-model="formdata.userName"  ng-model-options="{ updateOn: 'blur' }" placeholder="username" required="">

								 <!-- error msg handling  -->
									<div ng-show="userform.$submitted || userform.userName.$touched">
									  <div class="alert alert-danger" ng-show="userform.userName.$error.required" role="alert">User name is required </div>
									</div>
								   <!-- error msg handling  -->
								</div>
							 </div>
						  <!-- row 1 ends -->

						  <!-- row 2 -->
							 <div class="row">
								<div class="col-md-6 col-xs-12 form-group">
								   <label for="Password">Password</label>
								   <input type="password" class="form-control" name="Password" ng-model="formdata.password"  ng-model-options="{ updateOn: 'blur' }" placeholder="Password" required="">

								  <!-- error msg handling  -->
									<div ng-show="userform.$submitted || userform.Password.$touched">
									  <div class="alert alert-danger" ng-show="userform.Password.$error.required" role="alert">Password  is required </div>
									</div>
								   <!-- error msg handling  -->
								</div>
								<div class="col-md-6 col-xs-12 form-group">
								   <label for="email">Email</label>
								   <input type="email" class="form-control" name="email" ng-model="formdata.email"  ng-model-options="{ updateOn: 'blur' }" placeholder="Email" required="">

								   <!-- error msg handling  -->
									<div ng-show="userform.$submitted || userform.email.$touched">
									  <div class="alert alert-danger" ng-show="userform.email.$error.required" role="alert">Email is required </div>
								   <div class="alert alert-danger" ng-show="userform.email.$error.email" role="alert">Email is invalid </div>
									</div>
								   <!-- error msg handling  -->
								</div>
							 </div>
						  <!-- row 2 ends -->

						  <!-- row 3 -->
							 <div class="row">
								<div class="col-md-6 col-xs-12 form-group">
									<label for="phone">Phone</label>
									<input type="tel" class="form-control" name="phone" ng-model="formdata.phone"  ng-model-options="{ updateOn: 'blur' }"  placeholder="Phone" required="">

								<!-- error msg handling  -->
									<div ng-show="userform.$submitted || userform.phone.$touched">
									  <div class="alert alert-danger" ng-show="userform.phone.$error.required" role="alert">Phone is required </div>
								   <div class="alert alert-danger" ng-show="userform.phone.$error.pattern" role="alert">Phone is invalid </div>
									</div>
								   <!-- error msg handling  -->

								</div>
								<div class="col-md-6 col-xs-12 form-group">
								  <label for="role">User Role</label>
								  <select class="form-control" name="role" ng-model="formdata.userRole" ng-model-options="{ updateOn: 'blur' }" required="">
									  <option value="1">1</option>
									  <option value="2">2</option>
									  <option value="3">3</option>
									  <option value="4">4</option>
									  <option value="4">5</option>
								  </select>

								  <!-- error msg handling  -->
									<div ng-show="userform.$submitted || userform.role.$touched">
									  <div class="alert alert-danger" ng-show="userform.role.$error.required" role="alert">User Role is required </div>
									</div>
								   <!-- error msg handling  -->
								</div>
							 </div>
						  <!-- row 3 ends -->
				  </div>
		</div>

		<div class="panel panel-default md-whiteframe-12dp formPanel">
	         <div class="panel-heading">
				{{sandwOption}} Store
	          </div>
	          <div class="panel-body">

				<!-- row 1 -->
				<div class="row">
				<div class="col-md-6 col-xs-12 form-group">
				<label for="userID">UserID</label>
				<input type="text" class="form-control" name="userid" ng-model="formdata.userId"  ng-model-options="{ updateOn: 'blur' }"  placeholder="userid" required="">

				<!-- error msg handling  -->
				<div ng-show="userform.$submitted || userform.userid.$touched">
				<div class="alert alert-danger" ng-show="userform.userid.$error.required" role="alert">UserId is required </div>
				<div class="alert alert-danger" ng-show="userform.userid.$error.pattern" role="alert">UserID  is Invalid </div>
				</div>
				<!-- error msg handling  -->
				</div>

				<div class="col-md-6 col-xs-12 form-group">
				<label for="userName">User Name</label>
				<input type="text" class="form-control" name="userName" ng-model="formdata.userName"  ng-model-options="{ updateOn: 'blur' }" placeholder="username" required="">

				<!-- error msg handling  -->
				<div ng-show="userform.$submitted || userform.userName.$touched">
				<div class="alert alert-danger" ng-show="userform.userName.$error.required" role="alert">User name is required </div>
				</div>
				<!-- error msg handling  -->
				</div>
				</div>
				<!-- row 1 ends -->

				<!-- row 2 -->
				<div class="row">
				<div class="col-md-6 col-xs-12 form-group">
				<label for="Password">Password</label>
				<input type="password" class="form-control" name="Password" ng-model="formdata.password"  ng-model-options="{ updateOn: 'blur' }" placeholder="Password" required="">

				<!-- error msg handling  -->
				<div ng-show="userform.$submitted || userform.Password.$touched">
				<div class="alert alert-danger" ng-show="userform.Password.$error.required" role="alert">Password  is required </div>
				</div>
				<!-- error msg handling  -->
				</div>
				<div class="col-md-6 col-xs-12 form-group">
				<label for="email">Email</label>
				<input type="email" class="form-control" name="email" ng-model="formdata.email"  ng-model-options="{ updateOn: 'blur' }" placeholder="Email" required="">

				<!-- error msg handling  -->
				<div ng-show="userform.$submitted || userform.email.$touched">
				<div class="alert alert-danger" ng-show="userform.email.$error.required" role="alert">Email is required </div>
				<div class="alert alert-danger" ng-show="userform.email.$error.email" role="alert">Email is invalid </div>
				</div>
				<!-- error msg handling  -->
				</div>
				</div>
				<!-- row 2 ends -->

				<!-- row 3 -->
				<div class="row">
				<div class="col-md-6 col-xs-12 form-group">
				<label for="phone">Phone</label>
				<input type="tel" class="form-control" name="phone" ng-model="formdata.phone"  ng-model-options="{ updateOn: 'blur' }"  placeholder="Phone" required="">

				<!-- error msg handling  -->
				<div ng-show="userform.$submitted || userform.phone.$touched">
				<div class="alert alert-danger" ng-show="userform.phone.$error.required" role="alert">Phone is required </div>
				<div class="alert alert-danger" ng-show="userform.phone.$error.pattern" role="alert">Phone is invalid </div>
				</div>
				<!-- error msg handling  -->

				</div>
				<div class="col-md-6 col-xs-12 form-group">
				<label for="role">User Role</label>
				<select class="form-control" name="role" ng-model="formdata.userRole" ng-model-options="{ updateOn: 'blur' }" required="">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="4">5</option>
				</select>

				<!-- error msg handling  -->
				<div ng-show="userform.$submitted || userform.role.$touched">
				<div class="alert alert-danger" ng-show="userform.role.$error.required" role="alert">User Role is required </div>
				</div>
				<!-- error msg handling  -->
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