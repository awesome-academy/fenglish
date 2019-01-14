<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="uri" value="${pageContext.request.requestURI}" />
<c:set var="domain" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}" />
<c:set var="req" value="${requestScope['javax.servlet.forward.request_uri']}" />

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
					<h1>${post.title}</h1>
				</div>
			</div>
		</div>

		<div class="row">
			<div id="news_detail_content" class="post_content">
				${post.content}</div>
		</div>

		<div class="row">
			<div class="block-comment-face">
				<div id="fb-root"></div>
				<div
					class="fb-comments fb_iframe_widget fb_iframe_widget_fluid_desktop"
					data-href="${domain}${req}" data-width="580" data-numposts="5">
				</div>
				<br>
				<div class="fb-share-button fb_iframe_widget"
					data-href="${domain}${req}" data-layout="button_count"
					data-size="small" data-mobile-iframe="true"
					fb-xfbml-state="rendered">
					<span class="button-share-fb"><iframe
							src="https://www.facebook.com/plugins/share_button.php?href=${$domain}${req}&layout=button_count&size=small&mobile_iframe=true&appId=333204870537739&width=78&height=20"
							width="78" height="20" class="iframe-share-fb" scrolling="no"
							frameborder="0" allowTransparency="true" allow="encrypted-media"></iframe>
					</span>
				</div>
				<div class="fb-like fb_iframe_widget" data-href="${domain}${req}"
					data-layout="button_count" data-action="like" data-size="small"
					data-show-faces="true" data-share="false" fb-xfbml-state="rendered">
					<span class="button-like-fb"><iframe
							width="78px" height="20px" frameborder="0"
							allowtransparency="true" allowfullscreen="true" scrolling="no"
							allow="encrypted-media"
							src="https://www.facebook.com/plugins/like.php?href=${domain}${req}&width=450&layout=standard&action=like&size=small&show_faces=true&share=true&height=80&appId=333204870537739"
							class="iframe-like-fb"></iframe></span>
				</div>
				<div class="button-share-g">
					<div class="g-plus" data-action="share" data-href="${domain}${req}"></div>
				</div>
			</div>
		</div>
	</div>
</div>