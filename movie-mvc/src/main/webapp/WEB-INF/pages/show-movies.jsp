<%@ include file="./header.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>Movies List</h3>

<ul>
	<c:forEach items="${movies}" var="movie">
		<li><a href="./movie-details?id=${movie.movieId}"> ${movie.title} </a></li>
	</c:forEach>
</ul>


<%@ include file="./footer.jspf"%>