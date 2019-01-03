<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="title.admin.post.title" var="colTitle" />
<spring:message code="title.admin.post.content" var="colContent" />
<spring:message code="title.admin.post.category" var="colCategory" />
<spring:message code="title.admin.post.createtime" var="colCreatedTime" />

<spring:url value="/ckeditor/ckeditor.js" var="ckeditor" />
<script type="text/javascript" src="${ckeditor}"></script>

<div class="container">
	<h1>Create Post</h1>
	<br />
	<div class="col-md-12">
		<spring:url value="/admin/posts/create" var="postActionUrl" />
		<form:form class="form-horizontal" method="post"
			modelAttribute="postForm" action="${postActionUrl}">
			<div class="form-group">
				<label class="col-sm-3 control-label">${colTitle}</label>
				<div class="col-sm-9">
					<form:input path="title" class="form-control" id="" placeholder="" />
					<form:errors path="title" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colCategory}</label>
				<div class="col-sm-9">
					<form:select class="form-control" id="element_10" name="element_10"
						path="categoryId">
						<c:forEach items="${categoryInfos}" var="categoryInfo"
							varStatus="count">
							<option value="${categoryInfo.id}">${categoryInfo.categoryName}</option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colContent}</label>
				<div class="col-sm-9">
					<form:textarea path="content" class="form-control" id="content"
						placeholder="" />
					<form:errors path="content" class="error" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary pull-right">CREATE</button>
				</div>
			</div>
		</form:form>
	</div>
</div>
