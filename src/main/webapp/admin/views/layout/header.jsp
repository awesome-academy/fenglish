<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/admin/" var="urlHome" />
<spring:url value="/admin/subjects" var="urlSubject" />
<spring:url value="/admin/logout" var="logoutActionUrl" />
<spring:url value="/admin/assets/images/flag-vi.png" var="flagVi" />
<spring:url value="/admin/assets/images/flag-en.png" var="flagEn" />
<spring:url value="/admin/questions/page=1" var="listQuestion" />
<spring:url value="/admin/questions/new" var="createQuestion" />
<spring:message code="title.admin.language" var="language" />
<spring:message code="title.admin.menuUser" var="menuUser" />
<spring:message code="title.admin.menuQuestion" var="menuQuestion" />
<spring:message code="title.admin.menuListQuestion"
	var="menuListQuestion" />
<spring:message code="title.admin.menuCreateQuestion"
	var="menuCreateQuestion" />
<spring:message code="title.admin.menuPost" var="menuPost" />
<spring:message code="title.admin.menuSubject" var="menuSubject" />
<spring:message code="title.admin.logout" var="logout" />

<nav>
	<div class="container-fluid">
		<div class="nav navbar-nav navbar-right" style="margin-right: -5em;">
			${language}: <a href="?lang=vn"><img src="${flagVi}" alt=""
				style="width: 15%"></a> | <a href="?lang=en"><img
				src="${flagEn}" alt="" style="width: 15%"></a>
		</div>
	</div>
</nav>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">FEnglish</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="${urlHome}">${menuUser}</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">${menuQuestion}<span
					class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="${listQuestion}">${menuListQuestion}</a></li>
					<li><a href="${createQuestion}">${menuCreateQuestion}</a></li>
				</ul></li>
			<li><a href="${urlSubject}">${menuSubject}</a></li>
			<li><a href="#">${menuPost}</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#">Hi, Admin</a></li>
			<li><a href="${logoutActionUrl}"><span
					class="glyphicon glyphicon-log-out"></span> ${logout}</a></li>
		</ul>
	</div>
</nav>