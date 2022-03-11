<%@ include file="./header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>User details</h3>

<table class="table w-50">
	
	<tr>
		<td>Name:</td>
		<td class="lead">${user.name}</td>
	</tr>
	<tr>
		<td>Number:</td>
		<td class="lead">${user.number}</td>
	</tr>
	<tr>
		<td>Email:</td>
		<td class="lead">${user.email}</td>
	</tr>
	<tr>
		<td>Your Current Balance:</td>
		<td class="lead">${user.balance}</td>
	</tr>
	
	
	<%-- <tr>
		<td>Your Movie Reviews:</td>
		<td class="lead">
		<c:forEach items="${reviewList}" var="review">
		Rating- ${review.views}<br>
		${review.rating}<br>
		By User-${review.movie.name}<br>
		<br>
	</c:forEach>
		</td>
	</tr> --%>
	
	
</table>

<a class="btn btn-primary" href="./edit-user?id=${user.userId}">Edit Your Details</a>
<%@ include file="./footer.jspf"%>