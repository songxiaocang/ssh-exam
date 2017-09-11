<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
<script	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>

<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.bg {
	max-width: 530px;
	padding: 15px;
	margin: 0 auto;
}
</style>
</head>
<body>
	<form role="form" class="bg"
		action="${pageContext.request.contextPath}/customer/addCustomer"
		method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="exampleInputEmail1">客户名称</label> <input
				type="text" class="form-control" name="cusName"
				placeholder="Enter Customer Name">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">客户电话</label> <input
				type="text" class="form-control" name="cusPhone"
				placeholder="Enter Customer Phone">
		</div>
		<div class="form-group">
			<label for="exampleInputFile">客户图片</label> 
			<input
				type="file" name="cusImg">
		</div>

		<button type="submit" class="btn btn-default">提交</button>
	</form>
	
</body>
</html>