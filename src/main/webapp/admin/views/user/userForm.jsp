<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="title.user.userUpdate" var="userUpdate" />
<spring:message code="title.user.name" var="colName" />
<spring:message code="title.user.phone" var="colPhone" />
<spring:message code="title.user.gender" var="colGender" />
<spring:message code="title.user.male" var="male" />
<spring:message code="title.user.female" var="female" />

<div class="container" style="margin: 0 auto;">
	<h1>${userUpdate}</h1>
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
				<label class="col-sm-3 control-label">${colName}</label>
				<div class="col-sm-9">
					<form:input path="fullname" class="form-control" id="fullname"
						placeholder="Tên" />
					<form:errors path="fullname" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Email</label>
				<div class="col-sm-9">
					<form:input path="email" class="form-control" id="email"
						placeholder="Email" />
					<form:errors path="email" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colPhone}</label>
				<div class="col-sm-9">
					<form:input path="phone" class="form-control" id="phone"
						placeholder="Điện thoại" />
					<form:errors path="phone" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colGender}</label>
				<div class="col-sm-9">
					<label class="radio-inline"> <form:radiobutton
							path="gender" value="Nam" /> ${male}
					</label> <label class="radio-inline"> <form:radiobutton
							path="gender" value="Nu" /> ${female}
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
