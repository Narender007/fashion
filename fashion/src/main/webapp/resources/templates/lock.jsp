    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>

            <%
     String s = (String) session.getAttribute("userName");
  %>

        <div class="lock-backdrop">


        </div>

        <div class="center-form-lock">
              <img src="resources/images/admin.png" />
              <h3> <%= s %> </h3>
        <input type="password" class="form-control" name="password" ng-model="formdata.password"  ng-model-options="{ updateOn: 'blur' }" placeholder="password" required="">
        </div>