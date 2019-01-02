<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
	<hr>
	<footer>
		<p>&copy; Footer layout</p>
	</footer>
</div>

<spring:message code="title.user.confirm" var="titleConfirm" />
<spring:message code="content.user.canceled" var="contentCanceled" />
<spring:message code="title.user.message" var="titleMessage"/>
<spring:message code="title.button.confirm" var="btnConfirm" />
<spring:message code="title.button.cancel" var="btnCancel" />
<spring:message code="title.button.ok" var="btnOk" />

<input id="titleConfirm" type="hidden" value="${titleConfirm}" >
<input id="contentCanceled" type="hidden" value="${contentCanceled}" >
<input id="titleMessage" type="hidden" value="${titleMessage}" >
<input id="btnConfirm" type="hidden" value="${btnConfirm}" >
<input id="btnCancel" type="hidden" value="${btnCancel}" >
<input id="btnOk" type="hidden" value="${btnOk}" >