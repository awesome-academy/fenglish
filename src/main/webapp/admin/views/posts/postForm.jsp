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

<div class="container" style="margin: 0 auto;">
	<h1>Edit Post</h1>
	<br />
	<div class="col-md-12" style="margin: 0 auto;">
		<spring:url value="/admin/posts/update" var="postActionUrl" />
		<form:form class="form-horizontal" method="post"
			modelAttribute="postForm" action="${postActionUrl}">
			<form:hidden path="id" />
			<div class="form-group">
				<label class="col-sm-3 control-label">${colTitle}</label>
				<div class="col-sm-9">
					<form:input path="title" class="form-control" id="" placeholder="" />
					<form:errors path="title" class="error" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colContent}</label>
			</div>
			<div>
				<div class="col-md-12">
					<form:textarea path="content" class="form-control" id="content"
						placeholder="" />
					<form:errors path="content" class="error" />
				</div>
				<script type="text/javascript">
					CKEDITOR.replace('content');
					CKEDITOR.config.height = 500;
				</script>
			</div>
			<%-- <div class="form-group">
				<label class="col-sm-3 control-label">${colCreatedTime}</label>
				<div class="col-sm-9">
					<form:input type="datetime" path="createdTime" class="form-control"
						id="createdTime" placeholder="" />
					<form:errors path="createdTime" class="error" />
				</div>
			</div> --%>
			<div class="form-group">
				<label class="col-sm-3 control-label">${colCategory}</label>
				<div class="col-sm-9">
					<form:select class="form-control" id="element_10" name="element_10"
						path="">
						<option value="" selected="selected"></option>
						<c:forEach items="" var="levelInfo" varStatus="count">
							<option value=""></option>
						</c:forEach>
					</form:select>
				</div>
			</div>



			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn-lg btn-primary pull-right">UPDATE</button>
				</div>
			</div>
		</form:form>
	</div>
</div>
