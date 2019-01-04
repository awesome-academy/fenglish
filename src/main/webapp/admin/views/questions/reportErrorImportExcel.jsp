<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container">
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
		<h2>${questionHeader}</h2>
	</div>
	<div class="row">
		<form:form method="POST" action="/fenglish/admin/questions/upload"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td>${questionImport}</td>
				</tr>
				<tr>
					<td><input type="file" name="multipartFile"
						class="btn-success"
						accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit" class="btn-success" /></td>
				</tr>
			</table>
		</form:form>
	</div>
	<div class="row">
		<div class="md-col-8">
			<fieldset>
				<legend> </legend>
				<table class="table table-hover table-dark">
					<thead class="bg-primary">
						<tr>
							<th scope="col">Row</th>
							<th scope="col">Error</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${mapError}" var="error" varStatus="count">
							<tr>
								<td>${error.key}</td>
								<td>${error.value}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</fieldset>
		</div>
	</div>
</div>