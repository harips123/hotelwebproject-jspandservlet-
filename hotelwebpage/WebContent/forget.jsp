<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/loginpanel.css"></link>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body id="grad">
<ul>
<li><a href="home.jsp">Home</a></li>
<li><a href="aboutus.jsp">About us</a></li>
<li class="register">
<a href="javascript:void(0)" class="dropbtn">Login</a>
<div class="register-content">
<a href="hoteloginpage.jsp">hotel Login</a>
</div>
</li>
<li class="register">
<a href="javascript:void(0)" class="dropbtn">Register</a>
<div class="register-content">
<a href="hotelregpage.jsp">hotel</a>
<a href="userregpage.jsp">user</a>
</div>
</li>
</ul>
 <div class="main">
    <p class="sign" align="center">Reset your password</p>
    <form class="form1" action="resetpasswordservlet" method="post">
      <input class="un" type="text"  placeholder="enter your Username" name="user_name" required>
      <input class="un" type="text"  placeholder="enter your registered contact number" name="reg_number" required>
      <input class="pass" type="password"  placeholder="enter your new password" name="reset_pass" required>  
      <input type="submit" class="submit"  value="Reset" > 
      <input type="reset" class="clear"  value="Clear" > <br>
       
     </form>  
     </div>
</body>
</html>