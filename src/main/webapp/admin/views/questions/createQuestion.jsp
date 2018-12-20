<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
<spring:message code="title.question.correctAnswer"
	var="colCorrectAnswer" />
<spring:message code="title.question.levelId" var="colLevelId" />
<spring:message code="title.question.subject" var="colSubject" />
<spring:message code="title.admin.menuCreateQuestion" var="questionCreate" />

<div class="container" style="margin: 0 auto;">
	<h1>${questionCreate}</h1>
	<br />
	<div class="col-md-6" style="margin: 0 auto;">
		<spring:url value="/admin/questions/create" var="questionActionUrl" />
		<form:form class="form-horizontal" method="post"
			modelAttribute="questionForm" action="${questionActionUrl}">
			<div class="form-group">
				<label class="col-sm-3 control-label">${colQuestion}</label>
				<div class="col-sm-9">
					<form:input path="question" class="form-control" id="question"
						placeholder="Question" />
					<form:errors path="question" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colTypeQuestion}</label>
				<div class="col-sm-9">
					<form:input path="typeQuestion" class="form-control"
						id="typeQuestion" placeholder="Loai cau hoi" />
					<form:errors path="typeQuestion" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colOption1}</label>
				<div class="col-sm-9">
					<form:input path="option1" class="form-control" id="option1"
						placeholder="" />
					<form:errors path="option1" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colOption2}</label>
				<div class="col-sm-9">
					<form:input path="option2" class="form-control" id="option2"
						placeholder="" />
					<form:errors path="option2" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colOption3}</label>
				<div class="col-sm-9">
					<form:input path="option3" class="form-control" id="option3"
						placeholder="" />
					<form:errors path="option3" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colOption4}</label>
				<div class="col-sm-9">
					<form:input path="option4" class="form-control" id="option4"
						placeholder="" />
					<form:errors path="option4" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colCorrectAnswer}</label>
				<div class="col-sm-9">
					<form:select class="form-control" id="element_10"
						name="element_10" path="correctAnswer">
						<option value="${questionForm.correctAnswer}" selected="selected">${questionForm.correctAnswer}</option>
							varStatus="count">
						<option value="1">${colOption1}</option>
						<option value="2">${colOption2}</option>
						<option value="3">${colOption3}</option>
						<option value="4">${colOption4}</option>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colSubject}</label>
				<div class="col-sm-9">
					<form:select class="form-control" id="element_10"
						name="element_10" path="subjectId">
						<option value="questionForm.subject.id" selected="selected">${questionForm.subject.subjectName}</option>
						<c:forEach items="${listSubjectInfo}" var="subjectInfo"
							varStatus="count">
							<option value="${subjectInfo.id}">${subjectInfo.subjectName}</option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colSubject}</label>
				<div class="col-sm-9">
					<form:select class="form-control" id="element_10"
						name="element_10" path="levelId">
						<option value="questionForm.level.id" selected="selected">${questionForm.level.name}</option>
						<c:forEach items="${listLevelInfo}" var="levelInfo"
							varStatus="count">
							<option value="${levelInfo.id}">${levelInfo.name}</option>
						</c:forEach>
					</form:select>
				</div>
			</div>



			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary pull-right">${questionCreate}</button>
				</div>
			</div>
		</form:form>
	</div>
</div>
