<%@ include file="./header.jspf"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>
	

	<c:choose>
		<c:when test="${disabledEmail==true}">
		Edit User Details
		</c:when>

		<c:otherwise>
		Add New User Details
		</c:otherwise>
	</c:choose>
	
</h3>


<div class="w-25">
	<sf:form modelAttribute="user" method="post">
		<sf:hidden path="userId" />
		<div class="mb-3">
			<label for="email" class="form-label"> Email Id: </label>



			<c:choose>
				<c:when test="${disabledEmail==true}">
				${loggedInUser.email}
				<sf:hidden path="email" />
				</c:when>

				<c:otherwise>
					<sf:input class="form-control" path="email" />
				</c:otherwise>
			</c:choose>
		</div>
		<div class="mb-3">
			<label for="name" class="form-label">Name</label>
			<sf:input class="form-control" path="name" />
		</div>

		<div class="mb-3">
			<label for="number" class="form-label">Number</label>
			<sf:input class="form-control" path="number" placeholder="7854123690" />
		</div>



		<div class="mb-3">
			<label for="password" class="form-label">Password</label>
			<sf:password class="form-control" path="password" />
		</div>

		<div class="mb-3">
			<label for="password" class="form-label">Confirm Password</label>
			<sf:password class="form-control" name="confirmPassword" path="" />
		</div>


		<div class="mb-3">
			<label for="balance" class="form-label">Balance</label>
			<sf:input class="form-control col-lg-3" path="balance" />
		</div>

		<sf:hidden path="isAdmin" />

		<input type="Submit" <c:choose>
		<c:when test="${disabledEmail==true}">
		value="Update"
		</c:when>

		<c:otherwise>
		value="Register"
		</c:otherwise>
	</c:choose>
	
	 class="btn btn-success">
	</sf:form>
</div>

<%@ include file="./footer.jspf"%>