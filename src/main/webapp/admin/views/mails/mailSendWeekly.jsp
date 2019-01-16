<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:message code="title.subject.createSubject" var="addSubject" />
<spring:message code="title.subject.subjectName" var="colName" />
<spring:message code="title.button.create" var="btnCreate" />

<div class="container" style="margin: 0 auto;">
	<h1>${userUpdate}</h1>
	<br />
	<div class="col-md-6" style="margin: 0 auto;">
		<spring:url value="/admin/mail/sendMail" var="actionUrl" />
		<form:form class="form-horizontal" method="POST"
			modelAttribute="emailForm" action="${actionUrl}">
			<div class="form-group">
				<label class="col-sm-3 control-label">Titi</label>
				<div class="col-sm-9">
					<form:input path="title" class="form-control" placeholder="Title" />
					<form:errors path="title" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colName}</label>
				<div class="col-sm-9">
					<form:textarea path="content" class="form-control" id="content"
						placeholder="" />
					<form:errors path="content" class="error" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary pull-right">${btnCreate}</button>
				</div>
			</div>
		</form:form>
	</div>
</div>
