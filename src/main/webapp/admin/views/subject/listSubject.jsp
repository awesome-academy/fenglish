<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="title.user.name" var="colName" />
<spring:message code="title.button.detail" var="btnDetail" />
<spring:message code="title.button.edit" var="btnEdit" />
<spring:message code="title.button.remove" var="btnRemove" />
<spring:message code="title.button.addSubject" var="btnCreateSubject" />
<spring:message code="title.admin.logout" var="logout" />
<spring:message code="title.admin.menuSubject" var="subjectHeader" />

<spring:message code="content.subject.confirm" var="contentConfirm"/>
<spring:message code="msg.subject.deletesuccess" var="deleteSuccess" />

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
		<h2>${subjectHeader}</h2>
	</div>
	<div class="row" style="margin-top: 20px">
		<div class="md-col-8">
			<fieldset>
				<legend> </legend>
				<div style="padding-bottom: 1.5em">
					<spring:url value="/admin/subjects/create" var="createAction" />
					<button class="btn btn-success"
						onclick="location.href='${createAction}'">${btnCreateSubject}</button>
				</div>
				<table class="table table-hover table-dark">
					<thead class="bg-primary">
						<tr>
							<th scope="col">#</th>
							<th scope="col">${colName}</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${subjects}" var="subject" varStatus="count">
							<tr>
								<th scope="row">${count.index + 1}</th>
								<td>${subject.subjectName}</td>
								<td>
									<button class="btn btn-danger btnDeleteSubject">${btnRemove}</button>
								</td>
								<td class="idSubject" hidden="true">${subject.id}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<tag:paginate offset="${offset}" count="${count}"
					uri="${pageContext.request.contextPath}/admin/subjects"
					next="&raquo;" previous="&laquo;" />
			</fieldset>
		</div>
	</div>
</div>