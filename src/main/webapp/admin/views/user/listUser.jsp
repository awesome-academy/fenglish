<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container" style="margin-top: 30px">
	<div class="row md-col-8">
		<h2>Quản lí User</h2>
		<form action="searchActionUrl" method="GET" class="form-horizontal">
			<table>
				<tr>
					<th>Tên&nbsp;&nbsp;</th>
					<td><input name="fullname" class="form-control" /></td>
					<th style="padding-left: 28px;">Email&nbsp;&nbsp;</th>
					<td><input name="price" class="form-control" /></td>
					<td style="padding-left: 28px;"><button type="submit"
							class="btn">Tìm kiếm</button></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="row" style="margin-top: 20px">
		<div class="md-col-8">
			<fieldset>
				<legend> </legend>
				<table class="table table-hover table-dark">
					<thead class="bg-primary">
						<tr>
							<th scope="col">#</th>
							<th scope="col">Tên</th>
							<th scope="col">Email</th>
							<th scope="col">Điện thoại</th>
							<th scope="col">Giới tính</th>
							<th scope="col">Ngày sinh</th>
							<th scope="col">Online?</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<th scope="row">${user.id}</th>
								<td>${user.fullname}</td>
								<td>${user.email}</td>
								<td>${user.phone}</td>
								<td>${user.gender}</td>
								<td>${user.birthday}</td>
								<td>Yes</td>
								<td><spring:url value="/admin/users/${user.id}/detail"
										var="detailActionUrl" />
									<button class="btn btn-info"
										onclick="location.href='${detailActionUrl}'">Chi tiết</button>
									<spring:url value="/admin/users/${user.id}/delete" var="deleteActionUrl" />
									<button class="btn btn-danger"
										onclick="location.href='${deleteActionUrl}'">Xóa</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<tag:paginate offset="${offset}" count="${count}"
					uri="${pageContext.request.contextPath}/admin/users"
					next="&raquo;" previous="&laquo;" />
			</fieldset>
		</div>
	</div>
</div>
