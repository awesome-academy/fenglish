<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="admin admin-add-problem"></div>

<spring:url value="/ckeditor/ckeditor.js" var="ckeditor" />
<script type="text/javascript" src="${ckeditor}"></script>

<form action="" method="post">
	<textarea id="editor1" name="editor1" cols="80" rows="10">
        </textarea>
	<script>
		CKEDITOR.replace('editor1');
		CKEDITOR.editorConfig = function(config) {
			// Define changes to default configuration here.
			config.startupFocus = false;
			if (isIE()) {
				if (!window.location.origin) {
					window.location.origin = window.location.protocol
							+ "//"
							+ window.location.hostname
							+ (window.location.port ? ':'
									+ window.location.port : '');
				}
				config.filebrowserBrowseUrl = '/admin/home/uploadClassic';
			} else {
				config.filebrowserBrowseUrl = '/admin/home/upload';
			}
			config.filebrowserWindowWidth = '10';
			config.filebrowserWindowHeight = '10';
			config.selectMultiple = true;
		};
		function isIE() {
			var ua = window.navigator.userAgent;
			var msie = ua.indexOf("MSIE ");
			return !!(msie > 0 || !!navigator.userAgent
					.match(/Trident.*rv\:11\./));
		}
	</script>
</form>