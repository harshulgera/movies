<%@ include file="./header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>Movie details</h3>

<table class="table w-50">
	<tr>
		<td>Movie Id:</td>
		<td class="lead">${movie.movieId}</td>
	</tr>
	<tr>
		<td>Movie Title:</td>
		<td class="lead">${movie.title}</td>
	</tr>
	<tr>
		<td>Release Year:</td>
		<td class="lead">${movie.year}</td>
	</tr>
	<tr>
		<td>Movie Genre:</td>
		<td class="lead">${movie.genre}</td>
	</tr>
	<tr>
		<td>Movie Cast:</td>
		<td class="lead">
		<c:forEach items="${castList}" var="cast">
		${cast}<br>
	</c:forEach>
		</td>
	</tr>
	
	<tr>
		<td>Movie Reviews:</td>
		<td class="lead">
		<c:forEach items="${reviewsList}" var="review">
		Rating- ${review.views}<br>
		${review.rating}<br>
		By User-${review.user.name}<br>
		<br>
	</c:forEach>
		</td>
	</tr>
	
	
</table>
<c:choose>
<c:when test="${loggedInUser==null}">
</c:when>

<c:when test="${loggedInUser.isAdmin==true}">
<a class="btn btn-primary" href="./edit-movie?id=${movie.movieId}">Edit</a>
<a class="btn btn-danger" href="./delete-movie?id=${movie.movieId}">Delete Movie</a>
<a class="btn btn-warning" href="./edit-movie-review?id=${movie.movieId}">Edit Reviews</a>
</c:when>

<c:otherwise>
	<a class="btn btn-success" href="./add-new-review?id=${movie.movieId}">Add Review</a>
</c:otherwise>

</c:choose>


<%@ include file="./footer.jspf"%>