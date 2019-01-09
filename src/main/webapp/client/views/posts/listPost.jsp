<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="home">
	<div class="home_background_container prlx_parent">
		<div class="home_background prlx"></div>
	</div>
</div>

<div class="popular page_section">
	<div class="container">
		<div class="row">
			<div class="col">
				<div class="section_title text-center">
					<h1>Danh sách bài đăng</h1>
				</div>
			</div>
		</div>

		<div class="row course_boxes">
			<c:choose>
				<c:when test="${not empty mapPosts}">
					<div class="tab-slider--nav">
						<ul class="tab-slider--tabs">
							<c:forEach items="${mapPosts}" var="entry" varStatus="count">
								<c:if test="${not empty entry.value}">
									<li class="tab-slider--trigger" rel="tab${count.index}">${entry.key}</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
					<div class="tab-slider--container">
						<c:forEach items="${mapPosts}" var="entry" varStatus="count">
							<c:if test="${not empty entry.value}">
								<div id="tab${count.index}" class="tab-slider--body">
									<c:forEach items="${entry.value}" var="post">
										<c:if test="${not empty post}">
											<div class="col-lg-4 course_box">
												<div class="card">
													<div class="card-body">
														<div class="card-title">
															<a
																href="${pageContext.request.contextPath}/posts/${post.id}">${post.title}</a>
														</div>
													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</c:when>
				<c:otherwise>
					<h3 class="text-center non-data">Chưa có bài đăng nào</h3>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>