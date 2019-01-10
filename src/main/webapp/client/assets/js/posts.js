(function() {
	"use strict";
	
	var app = angular.module("PostsApp", ["ngRoute"]);
	
	app.filter('range', function() {
		return function(input, total) {
			total = parseInt(total);
			
			for (var i = 1; i <= total; i++) {
				input.push(i);
			}
			
			return input;
		};
	});
	
	app.factory("MyService", function($http, $q) {
		var service = {
			loadCategories: loadCategories,
			loadPostByCategory: loadPostByCategory,
			countPostByCategory: countPostByCategory
		};
		return service;
		
		function loadCategories() {
			var defered = $q.defer();
			var url = "categories";
			
			$http.get(url).then(function(res) {
				defered.resolve(res.data);
			}, function(res) {
				defered.resolve(res);
				console.log("Error in loadCategories: " + res.messgae);
			});
			
			return defered.promise;
		}
		
		function loadPostByCategory(idCategory, page, per_page) {
			var defered = $q.defer();
			var url = "posts/" + idCategory + "/" + page + "/" + per_page;
			
			$http.get(url).then(function(res) {
				defered.resolve(res.data);
			}, function(res) {
				defered.resolve(res);
				console.log("Error in loadPostByCategory: " + res.message);
			});
			
			return defered.promise;
		}
		
		function countPostByCategory() {
			var defered =  $q.defer();
			var url = "posts/count";
			
			$http.get(url).then(function(res) {
				defered.resolve(res.data);
			}, function(res) {
				defered.resolve(res);
				console.log("Error in countPostCategory: " + res.message);
			});
			
			return defered.promise;
		}
	});
	
	app.controller("PostsController", function($scope, $filter, MyService) {
		$scope.mapPosts = [];
		$scope.counts = [];
		$scope.numPerPage = 5;
		
		MyService.countPostByCategory().then(function(d) {
			$scope.counts = d;
		});
		
		MyService.loadCategories().then(function(d) {
			$scope.categories = d;
			
			$scope.categories.forEach(function(category) {
				MyService.loadPostByCategory(category.id, 1, $scope.numPerPage).then(function(d) {
					var totalPage = getTotalPage($scope.numPerPage, $scope.counts[category.id]);
					$scope.mapPosts.push({
						"id": category.id,
						"currentPage": 1,
						"totalPage": totalPage,
						"posts": d,
					});
					$scope.mapPosts = $filter("orderBy")($scope.mapPosts, "id");
					
					$(".tab-slider--body:first").show();
					$(".tab-slider--body").hide();
					
					$(".tab-slider--nav li").click(function() {
						$(".tab-slider--body").hide();
						var activeTab = $(this).attr("rel");
						$("#"+activeTab).show();
						$(".tab-slider--nav li").removeClass("active");
						$(this).addClass("active");
						$(this).fadeIn();
					});
				});
			});
		});
		
		$scope.selectPage = function(idCategory, page) {
			for(var i in $scope.mapPosts) {
				if ($scope.mapPosts[i].id === idCategory && $scope.mapPosts[i].currentPage === page) {
					return;
				}
			}
			
			MyService.loadPostByCategory(idCategory, page, $scope.numPerPage).then(function(d) {
				for(var i in $scope.mapPosts) {
					if ($scope.mapPosts[i].id === idCategory) {
						$scope.mapPosts[i].currentPage = page;
						$scope.mapPosts[i].posts = d;
						
						reloadPaging(i, page);
					}
				}
			});
		}
		
		$scope.previous = function(idCategory, currentPage) {
			MyService.loadPostByCategory(idCategory, currentPage - 1, $scope.numPerPage).then(function(d){
				for(var i in $scope.mapPosts) {
					if ($scope.mapPosts[i].id === idCategory) {
						$scope.mapPosts[i].currentPage = currentPage - 1;
						$scope.mapPosts[i].posts = d;
						
						reloadPaging(i, currentPage - 1);
					}
				}
			});
		}
		
		$scope.next = function(idCategory, currentPage) {
			MyService.loadPostByCategory(idCategory, currentPage + 1, $scope.numPerPage).then(function(d){
				for(var i in $scope.mapPosts) {
					if ($scope.mapPosts[i].id === idCategory) {
						$scope.mapPosts[i].currentPage = currentPage + 1;
						$scope.mapPosts[i].posts = d;
						
						reloadPaging(i, currentPage + 1);
					}
				}
			});
		}
		
		function getTotalPage(pageSize, total) {
			var totalPage = Math.floor(total / pageSize);
			if (total % pageSize != 0) {
				totalPage++;
			}
			return totalPage;
		}
		
		function reloadPaging(i, page) {
			switch (page) {
				case 1:
					$("#tab" + i + " .pagination li:first").addClass("disabled");
					$("#tab" + i + " .pagination li:last").removeClass("disabled");
					break;
				case $scope.mapPosts[i].totalPage:
					$("#tab" + i + " .pagination li:first").removeClass("disabled");
					$("#tab" + i + " .pagination li:last").addClass("disabled");
					break;
				default:
					$("#tab" + i + " .pagination li").removeClass("disabled");
					break;
			}
			
			$("#tab" + i + " .pagination li").removeClass("active");
			$("#tab" + i + " .pagination li:nth-child(" + (page + 1)  + ")").addClass("active");
		}
	});
})();