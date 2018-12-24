<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="home">
	<div class="home_background_container prlx_parent">
		<div class="home_background prlx"></div>
	</div>
</div>

<spring:message code="title.user.userDetail" var="userDetail" />
<spring:message code="title.user.name" var="colName" />
<spring:message code="title.user.phone" var="colPhone" />

<div class="container">
	<div class="row">
		<div
			class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
			<c:if test="${not empty msg}">
				<div class="alert alert-${css} alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Đóng">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>${msg}</strong>
				</div>
			</c:if>
			<c:if test="${empty msg}">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title user-title">Thông tin cá nhân</h3>
						<a href="${pageContext.request.contextPath}/users/edit">Chỉnh sửa</a>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<spring:url value="/client/assets/images/default-avatar.png" var="defaultAvatar" />
								<img alt="User Pic"
									src="${defaultAvatar}"
									class="img-circle img-responsive">
							</div>
							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>Tên</td>
											<td>${user.fullname}</td>
										</tr>
										<tr>
											<td>Email</td>
											<td>${user.email}</td>
										</tr>
										<tr>
											<td>Điện thoại</td>
											<td>${user.phone}</td>
										</tr>
										<tr>
											<td>Giới tính</td>
											<td>${user.gender}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="panel-footer" style="height: 50px; !important"></div>
				</div>
			</c:if>
		</div>
	</div>
</div>