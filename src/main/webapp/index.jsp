<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<title>Login Page</title>	
</head>
<body>
<div class="container main-section">
	<div class="row">
		<div class="col-md-12 text-center user-login-header">
			<h1>Login Form</h1>
			<p>Jakarta EE 8<span>Login Form</span></p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4 col-sm-8 col-xs-12 col-md-offset-4 col-sm-offset-2 login-image-main text-center">
			<div class="row">				
				 <form action="initread.do" method="POST">
				  <div class="col-md-12 col-sm-12 col-xs-12 user-login-box">
					<div class="form-group">
				  		<input type="text" name="username" class="form-control" placeholder="User Name" />
					</div>
					<div class="form-group">
				  		<input type="text" name="password" class="form-control" placeholder="Password" />
					</div>
					<input type="submit" value="LOGIN"/>
				  </div>
			  	 </form>
				<div class="col-md-12 col-sm-12 col-xs-12 last-part">
					<p>Not registered?<a href="#"> Create an account</a></p>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>