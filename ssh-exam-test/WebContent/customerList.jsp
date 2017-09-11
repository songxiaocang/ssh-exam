<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.js"></script>
<script	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<title>Insert title here</title>
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
<script type="text/javascript">
	function delCustomer(cid){
		window.location="${pageContext.request.contextPath}/customer/delCustomer?id="+cid;
	}
	
	var currPage = 1;
	var pageSize = 5;
	var cid;
	function findOrder(customerid){
		cid = customerid;
		$.post("${pageContext.request.contextPath}/order/findOrder",{"customerid":customerid,"currPage":currPage,"pageSize":pageSize},function(data){
			var json = eval("("+data+")");
			var html = "";
			var jsonObj = json.currContent;
			
			//html+="<tr><td>"+jsobObj[0].orderNum+"</td><td>"+jsonObj[0].receiveInfo+"</td><td>"+jsonObj[i].price+"</td><td>"+josnObj[0].c.cusName+"</td><td><a href='${pageContext.request.contextPath}/order/delOrder?oid="+jsonObj[0].orderNum+"'>删除</a></td></tr>";
			for (var i = 0; i < jsonObj.length; i++) {
				html+="<tr><td>"+jsonObj[i].orderNum+"</td><td>"+jsonObj[i].receiveInfo+"</td><td>"+jsonObj[i].price+"</td><td>"+jsonObj[i].customer.cusName+"</td><td><a href='${pageContext.request.contextPath}/order/delOrder?oid="+jsonObj[i].orderNum+"&cid="+jsonObj[i].customer.id+"'>删除</a></td></tr>";
			}
			$("#msg").html(html);
			
			var pageHtml="<ul class=\"pagination\">";
			if(json.currPage == 1){
				pageHtml+="<li class=\"disabled\"><a href=\"#\">&laquo;</a></li>";
			}else{
				pageHtml+="<li><a href=\"#\" onclick='prePage()'>&laquo;</a></li>";
			}
			
			for (var i = 1; i <= json.totalPage; i++) {
				if (i == json.currPage) {
					pageHtml+="<li class=\"active\"><a href=\"#\" onclick='selectPage("+i+")'>"+i+"</a></li>";
				} else {
					pageHtml+="<li><a href=\"#\" onclick='selectPage("+i+")'>"+i+"</a></li>";
				}
			}
			
			if(json.currPage == json.totalPage){
				pageHtml+="<li class=\"disabled\"><a href=\"#\">&raquo;</a></li>";
			}else{
				pageHtml+="<li><a href=\"#\" onclick='nextPage()'>&raquo;</a></li>";
			}
			
			pageHtml+="</ul>";
			$("#page").html(pageHtml);
		});
	}
	
	function prePage(){
		currPage = currPage - 1;
		findOrder(cid);
	}
	
	function selectPage(pg){
		currPage = pg;
		findOrder(cid);
	}
	
	function nextPage(){
		currPage = currPage + 1;
		findOrder(cid);
	}
	
</script>
</head>
<body>
	
	<table class="table table-hover table-bordered bg">
		<tr>
			<td colspan="4">
				<!-- <button type="button" class="btn btn-primary btn-lg active btn-sm"
					onclick="addCustomer()">Add Customer</button> --> <a
				href="${pageContext.request.contextPath}/addCustomer.jsp"
				class="btn btn-primary btn-lg active" role="button">添加客户</a>
			</td>
		</tr>
		<tr>
			<td>序号</td>
			<td>客户</td>
			<td>客户名称</td>
			<td>联系电话</td>
			<td>操作</td>
		</tr>
		<s:iterator value="cs" var="c" status="vs">
			<tr>
				<td>
					<s:property value="#vs.index+1"/>
				</td>
				<td>
					<img src="<s:property value='#c.cusImgsrc' />" class="img-circle"/>
				</td>
				<td>
					<s:property value="#c.cusName"/>
				</td>
				<td>
					<s:property value="#c.cusPhone"/>
				</td>
				<td>
					<a href="javascript:void(0);" class="btn btn-primary btn-sm" onclick="findOrder(<s:property value="#c.id"/>)" data-toggle="modal" data-target="#myModal">查询订单</a>
					<a href="javascript:void(0);" class="btn btn-primary btn-sm" onclick="delCustomer(<s:property value="#c.id"/>)">删除</a>
				</td>
			</tr>
		</s:iterator>

	</table>
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">订单详情</h4>
				</div>
				<div class="modal-body">
					<table class="table table-bordered .table-hover">
						<tr>
							<td>订单编号</td>
							<td>收货地址</td>
							<td>订单价格</td>
							<td>客户名称</td>
							<td>操作</td>
						</tr>
						<tbody id="msg">

						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<nav id="page"> </nav>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>