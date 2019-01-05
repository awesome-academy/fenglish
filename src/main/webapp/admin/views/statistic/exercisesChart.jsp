<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:message code="title.admin.statistic.title" var="statisticTitle" />
<spring:message code="title.admin.statistic.title.axisX"
	var="axisXTitle" />
<spring:message code="title.admin.statistic.title.axisY"
	var="axisYTitle" />

<input type="hidden" id="statisticTitleId" value="${statisticTitle}">
<input type="hidden" id="axisXTitleId" value="${axisXTitle}">
<input type="hidden" id="axisYTitleId" value="${axisYTitle}">
<div class="container">
	<div class="row">
		<div id="chartContainer"></div>
	</div>
</div>