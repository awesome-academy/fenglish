<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="title.admin.post.title" var="colTitle" />
<spring:message code="title.admin.post.content" var="colContent" />
<spring:message code="title.admin.post.category" var="colCategory" />
<spring:message code="title.admin.post.createtime" var="colCreatedTime" />

<spring:message code="title.button.detail" var="btnDetail" />
<spring:message code="title.button.edit" var="btnEdit" />
<spring:message code="title.button.remove" var="btnRemove" />
<spring:message code="title.admin.logout" var="logout" />
<spring:message code="title.admin.logout" var="logout" />
<spring:message code="title.admin.post.postheader" var="postHeader" />

<spring:message code="content.post.confirm" var="contentConfirm"/>
<spring:message code="msg.post.deletesuccess" var="deleteSuccess" />

<input id="contentConfirm" type="hidden" value="${contentConfirm}" >
<input id="deleteSuccess" type="hidden" value="${deleteSuccess}" >

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
		<h2>${postHeader}</h2>
	</div>

	<div class="row" style="margin-top: 20px">
		<div class="md-col-8">
			<fieldset>
				<legend> </legend>
				<table class="table table-hover table-dark">
					<thead class="bg-primary">
						<tr>
							<th scope="col">#</th>
							<th scope="col">${colTitle}</th>
							<th scope="col">${colCreatedTime}</th>
							<th scope="col">${colCategory}</th>
							<th scope="col">${btnEdit}</th>
							<th scope="col">${btnRemove}</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${posts}" var="post" varStatus="count">
							<tr>
								<th scope="row">${count.index + 1}</th>
								<td>${post.title}</td>
								<td>${post.createdTime}</td>
								<td>${post.category.id}</td>
								<td><spring:url value="/admin/posts/${post.id}/edit"
										var="editActionUrl" />
									<button class="btn btn-warning"
										onclick="location.href='${editActionUrl}'">${btnEdit}</button></td>
								<td><button class="btn btn-danger btnDeletePost">${btnRemove}</button></td>
								<td class="idPost" hidden="true">${post.id}</td>
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
