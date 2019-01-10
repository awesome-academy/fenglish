<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:message code="title.admin.chat.nousermessage"
	var="noUserMessage" />
<div id="contentChat">
	<div class="tab tabchat" id="buttonchat">
		<div class="head">User</div>
	</div>
	<div class="tabcontent">
		<div class="head">${noUserMessage}</div>
	</div>
</div>