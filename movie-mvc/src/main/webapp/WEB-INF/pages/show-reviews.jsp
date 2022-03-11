<%@ include file="./header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>All Reviews</h3>

<table class="table w-50">

<tr>
		<td>All Reviews:</td>
		<td class="lead">
		<c:forEach items="${reviewsList}" var="review">
		Rating- ${review.views}<br>
		${review.rating}<br>
		By User-${review.user.name}<br>
		For Movie- ${review.movie.title}
		<br>
		<br>
		<c:choose>
		
		<c:when test="${loggedInUser.isAdmin==true}">
			<a class="btn btn-danger" href="./delete-movie-review?id=${review.reviewId}">Delete Review</a>
		</c:when>
		
		<c:otherwise>
		
		<a class="btn btn-success" href="./edit-review?id=${review.reviewId}">Edit Review</a>
		<a class="btn btn-danger" href="./delete-review?id=${review.reviewId}">Delete Review</a>
		</c:otherwise>
		
		</c:choose>
		<br><br>
	</c:forEach>
		</td>
</tr>

</table>

<%@ include file="./footer.jspf"%>