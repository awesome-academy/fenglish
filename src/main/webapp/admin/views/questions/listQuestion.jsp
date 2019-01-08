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
	var="colCorrectAnswer" />
<spring:message code="title.question.levelId" var="colLevel" />
<spring:message code="title.question.subject" var="colSubject" />

<spring:message code="title.button.detail" var="btnDetail" />
<spring:message code="title.button.edit" var="btnEdit" />
<spring:message code="title.button.remove" var="btnRemove" />
<spring:message code="title.button.search" var="btnSearch" />
<spring:message code="title.admin.logout" var="logout" />
<spring:message code="title.admin.logout" var="logout" />
<spring:message code="title.admin.menuQuestion" var="questionHeader" />
<spring:message code="title.admin.questions.import" var="questionImport" />

<spring:message code="content.question.confirm" var="contentConfirm" />
<spring:message code="msg.question.deletesuccess" var="deleteSuccess" />

<input id="contentConfirm" type="hidden" value="${contentConfirm}">
<input id="deleteSuccess" type="hidden" value="${deleteSuccess}">

<div class="container" ng-app="searchApp"
	ng-controller="searchController as search">
	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong><spring:message code="${msg}" /></strong>
		</div>
	</c:if>
	<div class="row">
		<div class="col-md-12">
			<div class="col-md-4">
				<h2>${questionHeader}</h2>
			</div>
			<form id="search_question" class="form-horizontal"
				action="${pageContext.request.contextPath}/admin/questions/search"
				method="POST">
				<div class="col-md-4">
					<input class="form-control" type="text" placeholder="${btnSearch}" id="text_search"
						name="name" ng-model="name" ng-keyup="complete($event)" autocomplete="off">
					<ul class="list-group list-complete" ng-model="hide" ng-hide="hide">
						<li class="list-group-item" ng-repeat="row in questions"
							ng-click="selectQuestion(row)">{{row.question}}</li>
					</ul>
				</div>
				<div class="col-md-2">
					<select class="form-control" name="subject"
						ng-model="subject" ng-change="changeSubject()">
						<option value="">--${colSubject}--</option>
						<option ng-repeat="row in subjects" value="{{row.id}}">{{row.subjectName}}</option>
					</select>
				</div>
				<div class="col-md-2">
					<select class="form-control" name="level"
						ng-model="level" ng-change="changeLevel()">
						<option value="">--${colLevel}--</option>
						<option ng-repeat="row in levels" value="{{row.id}}">{{row.name}}</option>
					</select>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<form:form method="POST"
			action="${pageContext.request.contextPath}/admin/questions/upload"
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
	<div class="row">
		<div class="md-col-8">
			<fieldset>
				<legend> </legend>
				<table class="table table-hover table-dark"
					ng-table="search.tableParams" id="myTable">
					<tr ng-repeat="row in $data">
						<td data-title="'Id'">{{row.id}}</td>
						<td data-title="'${colQuestion}'">{{row.question}}</td>
						<td data-title="'${colTypeQuestion}'">{{row.typeQuestion}}</td>
						<td data-title="'${colOption1}'">{{row.option1}}</td>
						<td data-title="'${colOption2}'">{{row.option2}}</td>
						<td data-title="'${colOption3}'">{{row.option3}}</td>
						<td data-title="'${colOption4}'">{{row.option4}}</td>
						<td data-title="'${colCorrectAnswer}'">{{row.correctAnswer}}</td>
						<td>
							<button class="btn btn-warning" ng-click="editQuestion(row.id)">${btnEdit}</button>
						</td>
						<td>
							<button class="btn btn-danger" ng-click="removeQuestion(row.id)">${btnRemove}</button>
						</td>
						<td class="idQuestion" hidden="true">{{row.id}}</td>
					</tr>
				</table>
			</fieldset>
		</div>
	</div>
</div>
