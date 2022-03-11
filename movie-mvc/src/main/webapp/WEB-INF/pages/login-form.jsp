<%@ include file="./header.jspf" %>

<form method="post">
  <div class="mb-3 col-4">
    <label for="exampleInputEmail1" class="form-label">Email address</label>
    <input type="email" class="form-control col-sm-4" name="email" aria-describedby="emailHelp">
  
  </div>
  <div class="mb-3 col-4">
    <label for="exampleInputPassword1" class="form-label col-xs-4">Password</label>
    <input type="password" class="form-control" name="password">
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
  
</form>


<%@ include file="./footer.jspf" %>