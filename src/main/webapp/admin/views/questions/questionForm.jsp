<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="title.question.question" var="colQuestion" />
<spring:message code="title.question.typeQuestion" var="colTypeQuestion" />
<spring:message code="title.question.mp3Question" var="colMp3Question" />
<spring:message code="title.question.imgQuestion" var="colImgQuestion" />
<spring:message code="title.question.option1" var="colOption1" />
<spring:message code="title.question.option2" var="colOption2" />
<spring:message code="title.question.option3" var="colOption3" />
<spring:message code="title.question.option4" var="colOption4" />
<spring:message code="title.question.correctAnswes"
	var="colCorrectAnswes" />
<spring:message code="title.question.levelId" var="colLevelId" />
<spring:message code="title.question.subject" var="colSubject" />
<spring:message code="title.admin.questionEdit" var="questionUpdate" />

<div class="container" style="margin: 0 auto;">
	<h1>${questionUpdate}</h1>
	<br />
	<div class="col-md-6" style="margin: 0 auto;">
		<spring:url value="/admin/question/update" var="questionActionUrl" />
		<form:form class="form-horizontal" method="post"
			modelAttribute="questionForm" action="${questionActionUrl}">
			<form:hidden path="id" />
			<div class="form-group">
				<label class="col-sm-3 control-label">${colQuestion}</label>
				<div class="col-sm-9">
					<form:input path="question" class="form-control" id="fullname"
						placeholder="Question" />
					<form:errors path="fullname" class="error" />
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
