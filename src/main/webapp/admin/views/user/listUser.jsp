<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container" style="margin-top: 30px">
	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong><spring:message code="${msg}"/></strong>
		</div>
	</c:if>

	<div class="row md-col-8">
		<h2>Quản lí User</h2>
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
								<td><spring:url value="/admin/users/detail/${user.id}"
										var="detailActionUrl" />
									<button class="btn btn-info"
										onclick="location.href='${detailActionUrl}'">Chi tiết</button>
									<spring:url value="/admin/users/edit/${user.id}"
										var="editActionUrl" />
									<button class="btn btn-warning"
										onclick="location.href='${editActionUrl}'">Sửa</button>
									<spring:url value="/admin/users/delete/${user.id}"
										var="deleteActionUrl" />
									<button class="btn btn-danger"
										onclick="location.href='${deleteActionUrl}'">Xóa</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<tag:paginate offset="${offset}" count="${count}"
					uri="${pageContext.request.contextPath}/admin/users" next="&raquo;"
					previous="&laquo;" />
			</fieldset>
		</div>
	</div>
</div>
