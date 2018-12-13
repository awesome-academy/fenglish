<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container" style="margin: 0 auto;">
	<h1>Cập nhật User</h1>
	<br />
	<div class="col-md-6" style="margin: 0 auto;">
		<spring:url value="/admin/users/update" var="userActionUrl" />
		<form:form class="form-horizontal" method="post"
			modelAttribute="userForm" action="${userActionUrl}">
			<form:hidden path="id" />
			<form:hidden path="passwordHash"/>
			<form:hidden path="passwordResetToken"/>
			<form:hidden path="avatar"/>
			<form:hidden path="birthday"/>
			<form:hidden path="role"/>
			<div class="form-group">
				<label class="col-sm-2 control-label">Tên</label>
				<div class="col-sm-10">
					<form:input path="fullname" class="form-control" id="fullname"
						placeholder="Tên" />
					<form:errors path="fullname" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<form:input path="email" class="form-control" id="email"
						placeholder="Email" />
					<form:errors path="email" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Điện thoại</label>
				<div class="col-sm-10">
					<form:input path="phone" class="form-control" id="phone"
						placeholder="Điện thoại" />
					<form:errors path="phone" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Giới tính</label>
				<div class="col-sm-10">
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
