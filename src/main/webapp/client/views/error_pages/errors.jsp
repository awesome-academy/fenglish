<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="${pageContext.response.locale}">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<spring:url value="/client/assets/images/logo.png" var="imgLogo" />
<link rel="icon" type="text/x-icon" href="${imgLogo}">
<title>Lỗi</title>

<spring:url value="/client/assets/styles/errors/font_montserrat.css" var="fontCss" />
<spring:url value="/client/assets/styles/errors/styles.css" var="styleCss"/>
<link href="${fontCss}" type="text/css" rel="stylesheet">
<link href="${styleCss}" type="text/css" rel="stylesheet"/>

</head>
<body>
	<spring:message code="${code}" var="errorCode" />
	<spring:message code="${content}" var="errorContent" />
	<div id="notfound">
		<div class="notfound">
			<div class="notfound-404">
				<h1>Oops!</h1>
			</div>
			<h2>${errorCode}</h2>
			<p>${errorContent}</p>
			<a href="${pageContext.request.contextPath}">Quay về trang chủ</a>
		</div>
	</div>
</body>
</html>
