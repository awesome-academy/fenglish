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
<spring:message code="title.admin.questionEdit" var="questionUpdate" />

<div class="container" style="margin: 0 auto;">
	<h1>${questionUpdate}</h1>
	<br />
	<div class="col-md-6" style="margin: 0 auto;">
		<spring:url value="/admin/questions/update" var="questionActionUrl" />
		<form:form class="form-horizontal" method="post"
			modelAttribute="questionForm" action="${questionActionUrl}">
			<form:hidden path="id" />
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
						id="typeQuestion" placeholder="Điện thoại" />
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
					<button type="submit" class="btn-lg btn-primary pull-right">Cập
						nhật</button>
				</div>
			</div>
		</form:form>
		<%-- <form:form id="form_41408" class="appnitro"
			enctype="multipart/form-data" class="form-horizontal" method="post"
			modelAttribute="questionForm" action="${questionActionUrl}">
			<div class="form_description">
				<h2>Untitled Form</h2>
				<p>This is your form description. Click here to edit.</p>
			</div>
			<ul>

				<li id="li_1"><label class="description" for="element_1">Question
				</label>
					<div>
						<textarea id="element_1" name="element_1"
							class="element textarea medium" ></textarea>
					</div></li>
				<li id="li_9"><label class="description" for="element_9">Type
						Question </label> <span> <input id="element_9_1" name="element_9"
						class="element radio" type="radio" value="1"> <label
						class="choice" for="element_9_1">First option</label> <input
						id="element_9_2" name="element_9" class="element radio"
						type="radio" value="2"> <label class="choice"
						for="element_9_2">Second option</label> <input id="element_9_3"
						name="element_9" class="element radio" type="radio" value="3">
						<label class="choice" for="element_9_3">Third option</label>

				</span></li>
				<li id="li_2"><label class="description" for="element_2">mp3Question
				</label>
					<div>
						<input id="element_2" name="element_2" class="element file"
							type="file">
					</div></li>
				<li id="li_3"><label class="description" for="element_3">imgQuestion
				</label>
					<div>
						<input id="element_3" name="element_3" class="element file"
							type="file">
					</div></li>
				<li id="li_4"><label class="description" for="element_4">option1
				</label>
					<div>
						<input id="element_4" name="element_4" class="element text large"
							type="text" maxlength="255" value="">
					</div></li>
				<li id="li_5"><label class="description" for="element_5">option2
				</label>
					<div>
						<input id="element_5" name="element_5" class="element text large"
							type="text" maxlength="255" value="">
					</div></li>
				<li id="li_6"><label class="description" for="element_6">option3
				</label>
					<div>
						<input id="element_6" name="element_6" class="element text large"
							type="text" maxlength="255" value="">
					</div></li>
				<li id="li_7"><label class="description" for="element_7">option4
				</label>
					<div>
						<input id="element_7" name="element_7" class="element text large"
							type="text" maxlength="255" value="">
					</div></li>
				<li id="li_10"><label class="description" for="element_10">level
				</label>
					<div>
						<select class="element select medium" id="element_10"
							name="element_10">
							<option value="" selected="selected"></option>
							<option value="1">First option</option>
							<option value="2">Second option</option>
							<option value="3">Third option</option>

						</select>
					</div></li>
				<li id="li_11"><label class="description" for="element_11">subject
				</label>
					<div>
						<select class="element select medium" id="element_11"
							name="element_11">
							<option value="" selected="selected"></option>
							<option value="1">First option</option>
							<option value="2">Second option</option>
							<option value="3">Third option</option>

						</select>
					</div></li>
				<li id="li_8"><label class="description" for="element_8">correctAnswer
				</label>
					<div>
						<input id="element_8" name="element_8" class="element text medium"
							type="text" maxlength="255" value="">
					</div></li>

				<li class="buttons"><input type="hidden" name="form_id"
					value="41408"> <input id="saveForm" class="button_text"
					type="submit" name="submit" value="Submit"></li>
			</ul>
		</form:form> --%>
	</div>
</div>
