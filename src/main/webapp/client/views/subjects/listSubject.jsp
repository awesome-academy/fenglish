<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="home">
	<div class="home_background_container prlx_parent">
		<div class="home_background prlx"></div>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="md-col-8">
			<fieldset>
				<legend> </legend>
				<div class="col_box_test">
					<div class="head-list-read">
						<h2>Danh sách chủ đề</h2>
					</div>
					<div class="row">
						<c:forEach items="${subjects}" var="subject">
							<div class="col-md-6">
								<ul class="list_test">
									<li>
										<div>
											<a href="${pageContext.request.contextPath}/exercises/create/${subject.id}">${subject.subjectName}</a>
										</div>
									</li>
								</ul>
							</div>
						</c:forEach>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
</div>