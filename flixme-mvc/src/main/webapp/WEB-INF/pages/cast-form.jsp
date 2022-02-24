<%@ include file="./header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<form action="/add-new-cast" id="usrform" method="post">

	<input type="text" name= "movieId" value="${movieName}" hidden="true">
  Name: ${movieName} 
  <br><br>
  <textarea rows="10" cols="50" name="castlist" form="usrform" placeholder="Enter Cast List" ></textarea>
  <input type="submit">
</form>


<%@ include file="./footer.jspf"%>