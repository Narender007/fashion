<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="javax.servlet.http.HttpSession" %>
  <%
     String s = (String) session.getAttribute("userName");
  %>
    <nav class="navbar navbar-default navbar-fixed-top">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="#">Brand</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav">
		        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
		        <li><a href="#">Link</a></li>
		        <li class="dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a href="#">Action</a></li>
		            <li><a href="#">Another action</a></li>
		            <li><a href="#">Something else here</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="#">Separated link</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a href="#">One more separated link</a></li>
		          </ul>
		        </li>

				<!-- Unit dropdown -->
				<li class="dropdown">
				<a  class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Location <span class="caret"></span></a>
				<ul class="dropdown-menu">
				<li><a ui-sref="admin.unitmastertable">Unit/Warehouse</a></li>
				<li><a ui-sref="admin.indoormastertable">Indoor  Location</a></li>
				</ul>
				</li>
				<!-- Unit dwopdown ends -->
		        
		        <!-- Location dropdown -->
		         <li class="dropdown">
		          <a  class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Location <span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a ui-sref="admin.sandwmastertable">Store/Warehouse</a></li>
		            <li><a ui-sref="admin.indoormastertable">Indoor  Location</a></li>
		          </ul>
		        </li>
		        <!-- Location dwopdown ends -->
		        
		        <!-- user dropdown -->
		         <li class="dropdown">
		          <a  class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User <span class="caret"></span></a>
		          <ul class="dropdown-menu">
		            <li><a ui-sref="admin.usermastertable">User Master</a></li>
		            <li><a ui-sref="admin.rolemastertable">Role Master</a></li>
		          </ul>
		        </li>
		        <!-- user dwopdown ends -->
		      </ul>
		      
		      <div class="nav navbar-nav navbar-right">
		        <div class="dropdown" id="adminAvatar">
		         
		          <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> 
		              <img alt="admin" src="resources/images/admin.png">
		          </a>
		          <ul class="dropdown-menu">
		            <li><a><%= s %></a></li>
		            <li role="separator" class="divider"></li>
		            <li><a ui-sref="admin.dashbord">DashBord</a></li>
		            <li><a href="#">My Profile</a></li>
		            <li role="separator" class="divider"></li>
		            <li><a ui-sref="lockScreen">Lock Screen</a></li>
		            <li><a href="/fashion/logout">Log Out</a></li>
		          </ul>
		        </div>
		      </div>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
	</nav>
	
	
   <section class="admin-section">
        <div class="has-header container admin-container" ui-view>
           
        </div>
   </section>	
	
	