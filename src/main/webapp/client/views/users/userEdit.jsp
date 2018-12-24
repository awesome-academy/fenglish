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
		<form:form class="form-horizontal" method="POST"
			action="${userActionUrl}" enctype="multipart/form-data"
			modelAttribute="userForm">
			<form:hidden path="id" />
			<form:hidden path="passwordHash" />
			<form:hidden path="passwordResetToken" />
			<form:hidden path="avatar" />
			<form:hidden path="role" />
			<div class="col-md-3 col-lg-3 " align="center">
				<div>
					<c:choose>
						<c:when test='${userForm.avatar == null || "".equals(userForm.avatar)}'>
							<spring:url value="/client/assets/images/default-avatar.png"
								var="defaultAvatar" />
							<img alt="User Pic" src="${defaultAvatar}"
								class="img-circle img-responsive">
						</c:when>
						<c:otherwise>
							<img alt="User Pic" src="${userForm.avatar}"
								class="img-circle img-responsive">
						</c:otherwise>
					</c:choose>
					<input type="file" name="imgAvatar" accept="image/*" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Tên</label>
				<div class="col-sm-9">
					<form:input path="fullname" class="form-control" id="fullname"
						placeholder="Tên" autocomplete="off" />
					<form:errors path="fullname" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Email</label>
				<div class="col-sm-9">
					<form:input path="email" class="form-control" id="email"
						placeholder="Email" autocomplete="off" />
					<form:errors path="email" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Điện thoại</label>
				<div class="col-sm-9">
					<form:input path="phone" class="form-control" id="phone"
						placeholder="Điện thoại" autocomplete="off" />
					<form:errors path="phone" cssClass="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Ngày sinh</label>
				<div class="col-sm-9">
					<form:input path="birthday" class="form-control" id="birthday"
						type="text" autocomplete="off" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Giới tính</label>
				<div class="col-sm-9">
					<label class="radio-inline"> <form:radiobutton
							path="gender" value="Nam" /> Nam
					</label> <label class="radio-inline"> <form:radiobutton
							path="gender" value="Nu" /> Nữ
					</label>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary pull-right">Cập
						nhật</button>
				</div>
			</div>
		</form:form>
	</div>
</div>
