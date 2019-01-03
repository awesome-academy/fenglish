<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="home">
	<div class="home_background_container prlx_parent">
		<div class="home_background prlx"></div>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title user-title">Lịch sử làm bài</h3>
				<a href="${pageContext.request.contextPath}/users/show">Tài khoản</a> <span> | </span> <a
					href="${pageContext.request.contextPath}/exercises/histories/show">Lịch sử
					làm bài</a>
			</div>

			<c:choose>
				<c:when test="${exercises == null || fn:length(exercises) == 0}">
					<div>Bạn chưa làm bài thi nào</div>
					<a href="${pageContext.request.contextPath}/subjects">Làm bài
						thi tại đây</a>
				</c:when>
				<c:otherwise>
					<div class="panel-body" align="center">
						<div class="row">
							<table class="table table-hover table-dark">
								<thead class="bg-primary">
									<tr align="center">
										<th scope="col">#</th>
										<th scope="col">Chủ để</th>
										<th scope="col">Tổng câu hỏi</th>
										<th scope="col">Ngày tạo</th>
										<th scope="col">Điểm số</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${exercises}" var="exercise"
										varStatus="count">
										<tr align="center">
											<th scope="row">${count.index + 1}</th>
											<td>${exercise.subject.subjectName}</td>
											<td>${fn:length(exercise.exerciseDetails)}</td>
											<td><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${exercise.createTime}" /></td>
											<td>${scores[count.index]}/${fn:length(exercise.exerciseDetails)}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>