<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>로그인</title>

<script type="text/javascript">
	$(document).ready(function() {
		var message = "${message}";
		var url = "${url}";
		if (message != null && message != '') {
			alert(message);
			if (url != null && url != '') {
				location.href = "<c:url value='${url}'/>";
			} else {
				history.go(-1);
			} 
		} else {
			location.href = "<c:url value='/main.do'/>";//로그인 이후 나타나는 화면
		}
	});
</script>

