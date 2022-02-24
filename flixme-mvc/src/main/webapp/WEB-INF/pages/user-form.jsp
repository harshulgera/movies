<%@ include file="./header.jspf"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<h3>Add new Movie Details</h3>


<div class="w-25">
	<sf:form modelAttribute="user" method="post" action="./add-new-user">
		<div class="mb-3">
			<label for="name" class="form-label">ID</label>
			<sf:input class="form-control" path="userId" autofocus="true" disabled="true" />
			<sf:errors path="userId" class="text-danger" />
		</div>

		<div class="mb-3">
			<label for="name" class="form-label">Name</label>
			<sf:input class="form-control" path="name" />
		</div>

		<div class="mb-3">
			<label for="number" class="form-label">Number</label>
			<sf:input class="form-control" path="number" />
		</div>
		
		<div class="mb-3">
			<label for="email" class="form-label">Email Id</label>
			<sf:input class="form-control" path="email" />
		</div>
		
		<div class="mb-3">
			<label for="password" class="form-label">Password</label>
			<sf:input class="form-control" path="password" />
		</div>
		
		
		<div class="mb-3">
			<label for="balance" class="form-label">Balance</label>
			<sf:textarea class="form-control col-lg-3" path="balance" />
		</div>
		
		<input type="Submit" value="submit" class="btn btn-primary">
	</sf:form>
</div>

<%@ include file="./footer.jspf"%>