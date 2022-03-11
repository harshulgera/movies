<%@ include file="./header.jspf"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<h3>Add new Review for ${movie.title}</h3>


<div class="w-25">
	<sf:form modelAttribute="review" method="post">
		<sf:hidden path="reviewId" />

		<div class="mb-3">
			<label for="views" class="form-label">Enter Review</label>
			<sf:input class="form-control" path="views" />
		</div>

		<div class="mb-3">
			<label for="rating" class="form-label">Enter Rating (out of 10)</label>
			<sf:input class="form-control" path="rating" />
		</div>
		
		<input type="Submit" value="submit" class="btn btn-primary">
	</sf:form>
</div>

<%@ include file="./footer.jspf"%>