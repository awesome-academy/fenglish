<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="title.user.name" var="colName" />
<spring:message code="title.user.phone" var="colPhone" />
<spring:message code="title.user.gender" var="colGender" />
<spring:message code="title.user.birthday" var="colBirthday" />
<spring:message code="title.button.detail" var="btnDetail" />
<spring:message code="title.button.edit" var="btnEdit" />
<spring:message code="title.button.remove" var="btnRemove" />
<spring:message code="title.admin.logout" var="logout" />
<spring:message code="title.admin.menuUser" var="userHeader" />

<spring:message code="content.user.confirm" var="contentConfirm"/>
<spring:message code="msg.user.deletesuccess" var="deleteSuccess" />

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
		<h2>${userHeader}</h2>
	</div>
	<div class="row" style="margin-top: 20px">
		<div class="md-col-8">
			<fieldset>
				<legend> </legend>
				<table class="table table-hover table-dark">
					<thead class="bg-primary">
						<tr>
							<th scope="col">#</th>
							<th scope="col">${colName}</th>
							<th scope="col">Email</th>
							<th scope="col">${colPhone}</th>
							<th scope="col">${colGender}</th>
							<th scope="col">${colBirthday}</th>
							<th scope="col">Online?</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user" varStatus="count">
							<tr>
								<th scope="row">${count.index + 1}</th>
								<td>${user.fullname}</td>
								<td>${user.email}</td>
								<td>${user.phone}</td>
								<td>${user.gender}</td>
								<td>${user.birthday}</td>
								<td>Yes</td>
								<td><spring:url value="/admin/users/${user.id}"
										var="detailActionUrl" />
									<button class="btn btn-info"
										onclick="location.href='${detailActionUrl}'">${btnDetail}</button>
									<spring:url value="/admin/users/${user.id}/edit"
										var="editActionUrl" />
									<button class="btn btn-warning"
										onclick="location.href='${editActionUrl}'">${btnEdit}</button>
									<button class="btn btn-danger btnDeleteUser">${btnRemove}</button></td>
								<td class="idUser" hidden="true">${user.id}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<tag:paginate offset="${offset}" count="${count}"
					uri="${pageContext.request.contextPath}/admin/users" next="&raquo;"
					previous="&laquo;" />
			</fieldset>
		</div>
	</div>
</div>