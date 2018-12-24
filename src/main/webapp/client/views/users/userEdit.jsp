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

<div class="container edit-form" style="margin: 0 auto;">
	<h1>Chỉnh sửa thông tin</h1>
	<br />
	<div class="col-md-6" style="margin: 0 auto;">
		<spring:url value="/users/update" var="userActionUrl" />
		<form class="form-horizontal" method="post" action="${userActionUrl}"
			enctype="multipart/form-data">
			<input hidden="true" name="id" value="${user.id}"> <input
				hidden="true" name="passwordHash" value="${user.passwordHash}">
			<input hidden="true" name="passwordResetToken"
				value="${user.passwordResetToken}"> <input hidden="true"
				name="birthday" value="${user.birthday}"> <input
				hidden="true" name="role" value="${user.role}">
			<div class="col-md-3 col-lg-3 " align="center">
				<spring:url value="/client/assets/images/default-avatar.png"
					var="defaultAvatar" />
				<div>
					<img alt="User Pic" src="${defaultAvatar}"
						class="img-circle img-responsive"> <input type="file"
						name="avatar">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Tên</label>
				<div class="col-sm-9">
					<input name="fullname" class="form-control" id="fullname"
						placeholder="Tên" value="${user.fullname}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Email</label>
				<div class="col-sm-9">
					<input name="email" class="form-control" id="email"
						placeholder="Email" value="${user.email}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Điện thoại</label>
				<div class="col-sm-9">
					<input name="phone" class="form-control" id="phone"
						placeholder="Điện thoại" value="${user.phone}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Giới tính</label>
				<div class="col-sm-9">
					<c:choose>
						<c:when test='${user.gender == "Nam"}'>
							<label class="radio-inline"> <input type="radio"
								name="gender" value="Nam" checked="checked" /> Nam
							</label>
							<label class="radio-inline"> <input type="radio"
								name="gender" value="Nu" /> Nữ
							</label>
						</c:when>
						<c:otherwise>
							<label class="radio-inline"> <input type="radio"
								name="gender" value="Nam" /> Nam
							</label>
							<label class="radio-inline"> <input type="radio"
								name="gender" value="Nu" checked="checked" /> Nữ
							</label>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary pull-right">Cập
						nhật</button>
				</div>
			</div>
		</form>
	</div>
</div>
