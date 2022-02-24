<%@ include file="./header.jspf"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<h3>Add new Movie Details</h3>


<div class="w-25">
	<sf:form modelAttribute="movie" method="post" action="./add-new-movie">
		<div class="mb-3">
			<label for="name" class="form-label">ID</label>
			<sf:input class="form-control" path="movieId" autofocus="true" disabled="true" />
			<sf:errors path="movieId" class="text-danger" />
		</div>

		<div class="mb-3">
			<label for="title" class="form-label">Movie Title</label>
			<sf:input class="form-control" path="title" />
		</div>

		<div class="mb-3">
			<label for="year" class="form-label">Release Year</label>
			<sf:input class="form-control" path="year" />
		</div>
		
		<div class="mb-3">
			<label for="genre" class="form-label">Movie Genre</label>
			<sf:input class="form-control" path="genre" />
		</div>
		
		<div class="mb-3">
			<label for="casts" class="form-label">Movie Cast</label>
			<sf:textarea class="form-control col-lg-3" path="casts" />
		</div>
		
		<input type="Submit" value="submit" class="btn btn-primary">
	</sf:form>
</div>

<%@ include file="./footer.jspf"%>