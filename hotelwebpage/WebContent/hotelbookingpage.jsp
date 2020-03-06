<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bookingpage.css"></link>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

<!-- Include Date Range Picker -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

<script>
    $(document).ready(function(){
        var date_input=$('input[name="date"]'); //our date input has the name "date"
        var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
        date_input.datepicker({
            format: 'yyyy/mm/dd',
            container: container,
            todayHighlight: true,
            autoclose: true,
        })
    })
    
</script>
</head>
<body id="grad">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
<div class="media border p-3">
<img src="image/download(1).jpg"  style="width:25px;"></div>
  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="userwelcome.jsp">hotel</a>
    </li>
    <li class="nav-item">
      <a class="nav-link"  href="userbookingdispalyservlet">my bookings</a>
    </li>

    <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="profile.jsp" id="navbardrop" data-toggle="dropdown">
        user profile
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="userprofile.jsp">view profile</a>
        <a class="dropdown-item" href="userlogout.jsp">logout</a>
        <a class="dropdown-item" href="userdelete.jsp">delete account</a>
      </div>
    </li>
  </ul>
</nav>
<div class="main">
<form name="form1" action="bookingdetailsservlet" method="post">
<label for ="first_name" >Hotel id:</label>
<input type="text" name="hotel_id"class="form-control" value="<%=request.getParameter("hotelname") %>" readonly>
<label for ="first_name" >user id:</label>
<input type="text" name="user_id"class="form-control" value= "<%=session.getAttribute("user_id") %>" readonly>
<label for ="number of persons" class="control-label" >Total number adults:</label>
<input type="text" name= "total_number_of_pax" class="form-control"placeholder="enter the number of pax">
       
 <div class="form-group"> <!-- Date input -->
        <label class="control-label" for="date">Check-in </label>
        <input class="form-control" id="date" name="date" placeholder='yyyy/mm/dd' type="text"/>
      </div>
       <div class="form-group"> <!-- Date input -->
        <label class="control-label" for="date">Check-out</label>
        <input class="form-control" id="date" name="date" placeholder='yyyy/mm/dd' type="text"/>
      </div>
      <label class="contro-label" for="radio">Type of Room</label>
     <div class="radio-inline">
     <input type="radio" name="type_of_room"  value="double deluxe"checked> Double Deluxe room
      <input type="radio" name="type_of_room" value="Single bed">Single Bed
    </div>
    <div class="form-group"> <!-- Submit button -->
        <input type="submit" class="submit" value="confirm booking">
      </div> 
    </form>
    </div>
</body>
</html>