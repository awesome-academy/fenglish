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
				<form id="testing_form" action="/test/result" method="POST">
					<div class="head-list-read">
						<h1>Chủ đề: ${subject.subjectName}</h1>
					</div>
					<div class="question alias_answer" data-question="120397"
						style="padding: 0">
						<div class="myquestionarea">
							<div id="testing_answer_120397_2">
								<c:forEach items="${questions}" var="question" varStatus="count">
									<div class="question_number">
										<b>Question ${count.index + 1}: </b><span>${question.question}</span>
									</div>
									<c:if test="${question.option1 != null}">
										<label class="fulltest_answer_label"> <input
											type="radio" data-iquestion="${count.index + 1}" name="anser"> <span>${question.option1}</span>
										</label>
									</c:if>
									<c:if test="${question.option2 != null}">
										<label class="fulltest_answer_label"> <input
											type="radio" data-iquestion="2"> <span>${question.option2}</span>
										</label>
									</c:if>
									<c:if test="${question.option3 != null}">
										<label class="fulltest_answer_label"> <input
											type="radio" data-iquestion="2"> <span>${question.option3}</span>
										</label>
									</c:if>
									<c:if test="${question.option4 != null}">
										<label class="fulltest_answer_label"> <input
											type="radio" data-iquestion="2"> <span>${question.option4}</span>
										</label>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
					<div>
						<div class="btn-bh testing_result_button"
							id="testing_answer_button">
							<a href="javascript:void(0)"
								onclick="return mshoatoeic.send_answer_training()">Score</a>
						</div>
						<input name="test_id" value="13947" type="hidden"> <input
							type="hidden" name="token"
							value="c899ebb2032bbc2f65f0a26ce69b39bf">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>