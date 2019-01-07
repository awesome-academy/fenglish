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

<div class="popular page_section" ng-app="PostsApp"
	ng-controller="PostsController">
	<div class="container">
		<div class="row">
			<div class="col">
				<div class="section_title text-center">
					<h1>Danh sách bài đăng</h1>
				</div>
			</div>
		</div>

		<div class="row course_boxes">
			<div class="tab-slider--nav">
				<ul class="tab-slider--tabs">
					<li class="tab-slider--trigger" ng-repeat="row in categories"
						rel='{{"tab" + $index}}' ng-class={active:$first}>{{row.categoryName}}</li>
				</ul>
			</div>
			<div class="tab-slider--container">
				<div id='{{"tab" + $index}}' class="tab-slider--body"
					ng-repeat="posts in mapPosts">
					<div class="col-lg-4 course_box" ng-repeat="post in posts.posts">
						<div class="card">
							<div class="card-body">
								<div class="card-title">
									<a href="${pageContext.request.contextPath}/posts/{{post.id}}">{{post.title}}</a>
								</div>
							</div>
						</div>
					</div>
					<ul class="pagination">
						<li class="disabled" ng-click="previous(posts.id, posts.currentPage)"><a>&laquo;</a></li>
						<li ng-repeat="page in [] | range:posts.totalPage" ng-class="{active:$first}" ng-click="selectPage(posts.id, page)">
							<a>{{page}}</a>
						</li>
						<li ng-class="{disabled: posts.totalPage == 1}" ng-click="next(posts.id, posts.currentPage)"><a>&raquo;</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>