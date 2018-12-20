<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
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
	var="colCorrectAnswes" />
<spring:message code="title.question.levelId" var="colLevelId" />
<spring:message code="title.question.subject" var="colSubject" />

<spring:message code="title.button.detail" var="btnDetail" />
<spring:message code="title.button.edit" var="btnEdit" />
<spring:message code="title.button.remove" var="btnRemove" />
<spring:message code="title.admin.logout" var="logout" />
<spring:message code="title.admin.logout" var="logout" />
<spring:message code="title.admin.menuQuestion" var="questionHeader" />

<spring:message code="title.admin.questions.import" var="questionImport" />

<div class="container" style="margin-top: 30px">
	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong><spring:message code="${msg}" /></strong>
		</div>
	</c:if>

	<div class="row md-col-8">
		<h2>${questionHeader}</h2>
	</div>
	<div class="row" style="margin-top: 20px">
		<form:form method="POST" action="/fenglish/admin/questions/upload"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td>${questionImport}</td>
				</tr>
				<tr>
					<td><input type="file" name="multipartFile"
						class="btn-success"
						accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" class="btn-success" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<div class="row" style="margin-top: 20px">
		<div class="md-col-8">
			<fieldset>
				<legend> </legend>
				<table class="table table-hover table-dark">
					<thead class="bg-primary">
						<tr>
							<th scope="col">#</th>
							<th scope="col">${colQuestion}</th>
							<th scope="col">${colTypeQuestion}</th>
							<th scope="col">${colMp3Question}</th>
							<th scope="col">${colImgQuestion}</th>
							<th scope="col">${colOption1}</th>
							<th scope="col">${colOption2}</th>
							<th scope="col">${colOption3}</th>
							<th scope="col">${colOption4}</th>
							<th scope="col">${colCorrectAnswes}</th>
							<th scope="col">${colSubject}</th>
							<th scope="col">${colLevelId}</th>
							<th scope="col">${btnEdit}</th>
							<th scope="col">${btnRemove}</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listQuestion}" var="question"
							varStatus="count">
							<tr>
								<th scope="row">${count.index + 1}</th>
								<td>${question.question}</td>
								<td>${question.typeQuestion}</td>
								<td>${question.mp3Question}</td>
								<td>${question.imgQuestion}</td>
								<td>${question.option1}</td>
								<td>${question.option2}</td>
								<td>${question.option3}</td>
								<td>${question.option4}</td>
								<td>${question.correctAnswer}</td>
								<td>${question.subject.subjectName}</td>
								<td>${question.level.name}</td>
								<td><spring:url
										value="/admin/questions/${question.id}/edit"
										var="editActionUrl" />
									<button class="btn btn-warning"
										onclick="location.href='${editActionUrl}'">${btnEdit}</button></td>
								<td><button class="btn btn-danger btnDeleteQuestion">${btnRemove}</button></td>
								<td class="idQuestion" hidden="true">${question.id}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<tag:paginate offset="${pageNumber}" count="${count}"
					uri="${pageContext.request.contextPath}/admin/questions"
					next="&raquo;" previous="&laquo;" />
			</fieldset>
		</div>
	</div>
</div>
