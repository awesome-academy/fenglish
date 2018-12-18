<%@ page language="java" contentType="text/html; charset=UTF-8" %>
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
		<spring:url value="/admin/subjects/create" var="addActionUrl" />
		<form:form class="form-horizontal" method="POST"
			modelAttribute="subjectForm" action="${addActionUrl}">
			<form:hidden path="id" />
			<div class="form-group">
				<label class="col-sm-3 control-label">${colName}</label>
				<div class="col-sm-9">
					<form:input path="subjectName" class="form-control" id="subjectName"
						placeholder="Tên" />
					<form:errors path="subjectName" class="error" />
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
