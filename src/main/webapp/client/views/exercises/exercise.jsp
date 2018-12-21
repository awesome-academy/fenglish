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
		<div class="col-md-8 col-sm-8 col-xs-12">
			<div class="col_box_baihoc_view col_box_test" id="test_random">
				<form id="testing_form" action="${pageContext.request.contextPath}/exercises/result" method="POST">
					<div class="head-list-read">
						<h1>Chủ đề: ${subject.subjectName}</h1>
					</div>
					<div class="question alias_answer">
						<div class="myquestionarea">
							<c:forEach items="${questions}" var="question" varStatus="count">
								<div id="testing_answer[${id_exercise}][${count.index + 1}]">
									<div class="question_number">
										<b>Question ${count.index + 1}: </b><span>${question.question}</span>
									</div>
									<c:if test="${question.option1 != null}">
										<label class="fulltest_answer_label"> <input
											type="radio" name="answer[${id_exercise}][${question.id}]"
											value="1"> <span>${question.option1}</span>
										</label>
									</c:if>
									<c:if test="${question.option2 != null}">
										<label class="fulltest_answer_label"> <input
											type="radio" name="answer[${id_exercise}][${question.id}]"
											value="2">
											<span>${question.option2}</span>
										</label>
									</c:if>
									<c:if test="${question.option3 != null}">
										<label class="fulltest_answer_label"> <input
											type="radio" name="answer[${id_exercise}][${question.id}]"
											value="3">
											<span>${question.option3}</span>
										</label>
									</c:if>
									<c:if test="${question.option4 != null}">
										<label class="fulltest_answer_label"> <input
											type="radio" name="answer[${id_exercise}][${question.id}]"
											value="4">
											<span>${question.option4}</span>
										</label>
									</c:if>
								</div>
							</c:forEach>
						</div>
					</div>
					<div>
						<input name="id_exercise" value="${id_exercise}" type="hidden">
						<div class="btn-bh testing_result_button"
							id="testing_answer_button">
							<button type="submit" class="btn btn-success" value="Score">Score</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>