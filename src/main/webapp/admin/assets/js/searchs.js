(function () {
	"use strict";
	
	var contextUrl = $("#contextUrl").val();
	var app = angular.module("searchApp", ["ngRoute", "ngTable"]);
	
	app.factory("MyService", function($http, $q) {
		var service = {
			searchQuestions: searchQuestions,
			removeQuestion: removeQuestion,
			loadAllSubject: loadAllSubject,
			loadAllLevel: loadAllLevel
		};
		return service;
		
		function searchQuestions(name, subject, level) {
			var defered = $q.defer();
			var text = "", idSubject = 0, idLevel = 0;
			
			if (name !== undefined) {
				text = name;
			}
			
			if (subject !== undefined && subject !== "") {
				idSubject = subject;
			}
			
			if (level != undefined && level !== "") {
				idLevel = level;
			}
			
			var url = contextUrl + "admin/questions/search";
			var search = {
				name: text,
				subject: idSubject,
				level: idLevel
			};
			var headers = {
				'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
			};
			
			$http({
				method: "POST",
				url: url,
				headers: headers,
				data: search,
				transformRequest: function(obj) {
			        var str = [];
			        for(var p in obj)
			        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			        return str.join("&");
			    }
			}).then(function(res) {
				defered.resolve(res.data);
			}, function(res) {
				defered.resolve(res);
				console.log("Error in searchQuestions, code: " + res.message);
			});
			
			return defered.promise;
		}
		
		function removeQuestion(id) {
			var defered = $q.defer();
			var url = contextUrl + "admin/questions/" + id;
			$http({
				method: "DELETE",
				url: url
			}).then(function(res) {
				defered.resolve(res);
			}, function(res) {
				defered.resolve(res);
				console.log("Error in removeQuestion id=" + id + ", code: " + res.message);
			});
			
			return defered.promise;
		}
		
		function loadAllSubject() {
			var defered = $q.defer();
			var url = contextUrl + "admin/subjects";
			
			$http.get(url).then(function(res) {
				defered.resolve(res.data);
			}, function(res) {
				defered.resolve(res);
				console.error("Error in loadAllSubject: " + res.message);
			});
			
			return defered.promise;
		}
		
		function loadAllLevel() {
			var defered = $q.defer();
			var url = contextUrl + "admin/levels";
			
			$http.get(url).then(function(res) {
				defered.resolve(res.data);
			}, function(res) {
				defered.resolve(res);
				console.log("Error in loadAllLevel: " + e.message);
			});
			
			return defered.promise;
		}
	});
	
	app.controller("searchController", function($scope, $window, NgTableParams, MyService) {
		var self = this;
		var per_page = 5;
		$scope.currentPage = 1;
		$scope.questions = [];
		$scope.hide = true;
		
		MyService.loadAllSubject().then(function(d) {
			$scope.subjects = d;
		});
		
		MyService.loadAllLevel().then(function(d) {
			$scope.levels = d;
		});
		
		MyService.searchQuestions($scope.name, $scope.subject, $scope.level).then(function(d) {
			$scope.questions = d;
			self.tableParams = new NgTableParams({
				page: 1,
				count: per_page
			}, {
				counts: [],
				getData: function($defer, params) {
					params.total($scope.questions.length);
					$defer.resolve($scope.questions.slice((params.page() - 1) * params.count(), params.page() * params.count()));
				}
			});
		});
		
		$scope.editQuestion = function(id) {
			window.location.href = contextUrl + "admin/questions/" + id + "/edit";
		};
		
		$scope.removeQuestion = function(id) {
			$.confirm({
				title: $("#titleConfirm").val(),
				content: $("#contentConfirm").val(),
				buttons: {
					confirm: {
						text: $("#btnConfirm").val(),
						action: function() {
							MyService.removeQuestion(id).then(function(r) {
								$.confirm({
									title : $("#titleMessage").val(),
									content : $("#deleteSuccess").val(),
									buttons : {
										confirm : function() {
											for(var i in $scope.questions) {
												if ($scope.questions[i].id == id) {
													$scope.questions.splice(i, 1);
													break;
												}
											}
											reloadTableParams();
										}
									}
								});
							});
						}
					},
					cancel: {
						text: $("#btnCancel").val(),
						action: canceled
					}
				}
			});
		}
		
		$scope.complete = function(event) {
			if (event.keyCode === 27) {
				$scope.hide = true;
				return;
			}
			MyService.searchQuestions($scope.name, $scope.subject, $scope.level).then(function(d) {
				$scope.questions = d;
				$scope.hide = false;
				reloadTableParams();
			});
		}
		
		$scope.selectQuestion = function(row) {
			$scope.name = row.question;
			$scope.hide = true;
			MyService.searchQuestions($scope.name, $scope.subject, $scope.level).then(function(d) {
				$scope.questions = d;
				reloadTableParams();
			});
		}
		
		$scope.changeSubject = function() {
			MyService.searchQuestions($scope.name, $scope.subject, $scope.level).then(function(d) {
				$scope.questions = d;
				reloadTableParams();
			});
		}
		
		$scope.changeLevel = function() {
			MyService.searchQuestions($scope.name, $scope.subject, $scope.level).then(function(d) {
				$scope.questions = d;
				reloadTableParams();
			});
		}
		
		function reloadTableParams() {
			self.tableParams.reload().then(function(data) {
				if (data.length === 0 && self.tableParams.total() > 0) {
					self.tableParams.page(self.tableParams.page() - 1);
					self.tableParams.reload();
				}
			});
		}
		
		function canceled() {
			$.confirm({
				title : "",
				content : $("#contentCanceled").val(),
				buttons : {
					confirm : {
						text : $("#btnOk").val()
					}
				}
			});
		}
	});
})();