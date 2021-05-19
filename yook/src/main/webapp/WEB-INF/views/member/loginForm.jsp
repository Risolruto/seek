<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>


<link href="<c:url value="/resources/css/btn.css"/>" rel="stylesheet">


<script type="text/javascript">

function submitCheck() {
    var userId  = $('#MEM_ID' ).val() ;
    var userPwd = $('#MEM_PW').val() ;
    $.ajax({
        type : "POST",
        url: '/yook/login2.do',
        data: {userId:userId, userPwd:userPwd},
        success: function(data){},
		error: function (request, status, error){}
    }) ;
}


function fsubmit() {
		var id = $("#MEM_ID")[0].value;
		var pw = $("#MEM_PW")[0].value;
		if (id == null || id == '') {
			alert("아이디를 입력하세요.");
			return false;
		}
		if (pw == null || pw == '') {
			alert("비밀번호를 입력하세요.");
			return false;
		}

		var data = {
			id: id,
			pw : pw
		};

		$.ajax(
				{
				type:"POST",
				url:"/yook/login2.do",
				data: JSON.stringify(data),
				dataType:"json",
				contentType :"application/json",
				success: function(data){},
				error: function (request, status, error){}
				}
		)
	};


	 function fsubmit() {
		var id = $("#MEM_ID")[0].value;
		var pw = $("#MEM_PW")[0].value;
		if (id == null || id == '') {
			alert("아이디를 입력하세요.");
			return false;
		}
		if (pw == null || pw == '') {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		myform.submit();
	}
 
	$(document).ready(function() {
		// 저장된 쿠키를 로그인화면에 불러오기위함
		var userInputId = getCookie("userInputId");
		$("#MEM_ID").val(userInputId);

		if ($("#MEM_ID").val() != "") { // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
			$("#idSaveCheck").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
		}

		$("#idSaveCheck").change(function() { // 체크박스에 변화가 있다면,
			if ($("#idSaveCheck").is(":checked")) { // ID 저장하기 체크했을 때,
				var userInputId = $("#MEM_ID").val();
				setCookie("userInputId", userInputId, 7); // 7일보관
			} else { // ID 저장하기 체크 해제 시,
				deleteCookie("userInputId");
			}
		});

		// ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
		$("#MEM_ID").keyup(function() { // ID 입력 칸에 ID를 입력할 때,
			if ($("#idSaveCheck").is(":checked")) { // ID 저장하기를 체크한 상태라면,
				var userInputId = $("#MEM_ID").val();
				setCookie("userInputId", userInputId, 7); // 7일 동안 쿠키 보관
			}
		});
	});

	function setCookie(cookieName, value, exdays) { //SET을 사용하여 쿠키저장
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays); //설정 일수만큼 현재시간에 만료값으로 지정
		var cookieValue = escape(value)
				+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}

	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1);
		document.cookie = cookieName + "= " + "; expires="
				+ expireDate.toGMTString();
	}

	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1)
				end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
</script>

<title>로그인</title>
</head>
<body>
	<form name="myform" action="/yook/login.do" method="post"
		class="form-signin">


		<div style="height: 30px;"></div>
		<div align="center">
			<h3>Login</h3>
		</div>
		<div style="height: 30px; margin-bottom: 10px;"></div>

		<div
			style="width: 500px; margin-left: auto; margin-right: auto; margin-bottom: 150px;">
			<div>
				<p style="font-size: 13; color: gray;">
					가입하신 아이디와 비밀번호를 입력해주세요.<br> 비밀번호는 대소문자를 구분합니다.
				</p>
			</div>



			<div class="form-group">
				<label for="inputId">아이디</label>
				<input type="text" class="form-control" id="MEM_ID"
				 placeholder="MEMBER ID" name="MEM_ID">
			</div>
			<div class="form-group">
				<label for="inputPw">비밀번호</label>
				<input type="password" class="form-control" id="MEM_PW" 
				placeholder="PASSWORD" name="MEM_PW">
			</div>
			<div class="form-group form-check">
				<input type="checkbox" class="form-check-input" id="idSaveCheck">
				<label class="form-check-label" for="idSaveCheck">아이디 저장</label>
			</div>



			<input type="button" class="btn btn-lg btn-primary btn-block"
				style="margin-left: auto; width: 100%;" onclick="fsubmit()" value="로그인">
				
			<div class="form-group form-check" style="margin-top: 10px;">
				<div style="float: left;">
					<span style="margin-left: 30;"><a
						href="${contextPath}/yook/findId.do">아이디/비밀번호 찾기</a></span>
				</div>
				<div style="float: right;">
					<span style="margin-right: 90;"><a
						href="${contextPath}/yook/join.do">회원가입</a></span>
				</div>
			</div>
		</div>
	</form>

</body>
</html>