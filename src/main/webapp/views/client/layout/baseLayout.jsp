<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Course Project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="text/x-icon" href="views/client/assets/images/logo.png">

<spring:url
	value="/views/client/assets/styles/bootstrap4/bootstrap.min.css"
	var="bootstrapCss" />
<spring:url
	value="/views/client/assets/plugins/fontawesome-free-5.0.1/css/fontawesome-all.css"
	var="fontCss" />
<spring:url value="/views/client/assets/styles/elements_styles.css"
	var="styleCss" />
<spring:url value="/views/client/assets/styles/elements_responsive.css"
	var="responsiveCss" />

<link rel="stylesheet" type="text/css" href="${bootstrapCss}">
<link href="${fontCss}" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${styleCss}">
<link rel="stylesheet" type="text/css" href="${responsiveCss}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<div class="super-container">
		<tiles:insertAttribute name="header" />

		<tiles:insertAttribute name="body" />

		<tiles:insertAttribute name="footer" />
	</div>

	<script src="views/client/assets/js/jquery-3.2.1.min.js"></script>
	<script src="views/client/assets/styles/bootstrap4/popper.js"></script>
	<script src="views/client/assets/styles/bootstrap4/bootstrap.min.js"></script>
	<script src="views/client/assets/plugins/greensock/TweenMax.min.js"></script>
	<script src="views/client/assets/plugins/greensock/TimelineMax.min.js"></script>
	<script src="views/client/assets/plugins/scrollmagic/ScrollMagic.min.js"></script>
	<script src="views/client/assets/plugins/greensock/animation.gsap.min.js"></script>
	<script src="views/client/assets/plugins/greensock/ScrollToPlugin.min.js"></script>
	<script src="views/client/assets/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
	<script src="views/client/assets/plugins/scrollTo/jquery.scrollTo.min.js"></script>
	<script src="views/client/assets/plugins/easing/easing.js"></script>
	<script src="views/client/assets/js/custom.js"></script>
</body>
</html>