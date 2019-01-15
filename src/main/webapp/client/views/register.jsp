<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="home">
	<div class="home_background_container prlx_parent">
		<div class="home_background prlx"></div>
	</div>
</div>

<div class="register">
	<div class="container-fluid">
		<div class="row row-eq-height">
			<div class="col-lg-3 nopadding"></div>

			<div class="col-lg-6 nopadding">

				<!-- Search -->

				<div
					class="search_section d-flex flex-column align-items-center justify-content-center">
					<div class="search_background"></div>
					<div class="search_content text-center">
						<h1 class="search_title">Ghi Danh</h1>
						<spring:url var="register" value="/register" />
						<form name="registerForm" id="register_form" class="search_form"
							action="${register}" modelAttribute="userInfo" method="post">
							<input class="input_field search_form_name" type="text"
								pattern="[^ @]*@[^ @]*" required="required"
								data-error="Vui lòng điền tên đăng nhập" type="email"
								name="email" placeholder="Enter your email" /> <input
								class="input_field search_form_category" type="password"
								placeholder="password" name="password" id="password" /> <input
								class="input_field search_form_name" type="text"
								required="required" data-error="Vui lòng điền tên đăng nhập"
								type="email" name="fullname" placeholder="FullName" /> <input
								id="search_form_name" class="input_field search_form_name"
								type="text" required="required"
								data-error="Vui lòng điền tên đăng nhập" type="email"
								name="phone" placeholder="Phone" /> <br /> <br /> <select
								class="input_field search_form_name" name="gender">
								<option value="null">--Select gender--</option>
								<option value="0">Female</option>
								<option value="1">Male</option>
							</select>

							<button id="search_submit_button" type="submit"
								class="search_submit_button trans_200" value="Submit">Ghi
								danh</button>

						</form>
						<script>
							var password = document.getElementById("password"), confirm_password = document
									.getElementById("confirm_password");

							function validatePassword() {
								if (password.value != confirm_password.value) {
									confirm_password
											.setCustomValidity("Passwords Don't Match");
								} else {
									confirm_password.setCustomValidity('');
								}
							}

							password.onchange = validatePassword;
							confirm_password.onkeyup = validatePassword;
						</script>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>