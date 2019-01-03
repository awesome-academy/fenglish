<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="home">
	<div class="home_background_container prlx_parent">
		<div class="home_background prlx"></div>
	</div>
</div>

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
						<a href="${pageContext.request.contextPath}/users/edit">Chỉnh
							sửa</a>
						<span> | </span>
						<a href="${pageContext.request.contextPath}/exercises" >Lịch sử làm bài</a>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3" align="center">
								<c:choose>
									<c:when
										test='${current_user.avatar == null || "".equals(current_user.avatar)}'>
										<spring:url value="/client/assets/images/default-avatar.png"
											var="defaultAvatar" />
										<img alt="User Pic" src="${defaultAvatar}"
											class="img-circle img-responsive">
									</c:when>
									<c:otherwise>
										<img alt="User Pic" src="${current_user.avatar}"
											class="img-circle img-responsive">
									</c:otherwise>
								</c:choose>
							</div>
							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>Tên</td>
											<td>${current_user.fullname}</td>
										</tr>
										<tr>
											<td>Email</td>
											<td>${current_user.email}</td>
										</tr>
										<tr>
											<td>Điện thoại</td>
											<td>${current_user.phone}</td>
										</tr>
										<tr>
											<td>Ngày sinh</td>
											<td>${current_user.birthday}</td>
										</tr>
										<tr>
											<td>Giới tính</td>
											<td>${current_user.gender}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</div>