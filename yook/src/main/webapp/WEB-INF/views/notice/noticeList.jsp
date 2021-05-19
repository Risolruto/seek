<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function(){
		$.each(boardlist, function(index, item){
			var newobject = 
			 "<tr><td><a href='board_ajax.jsp'>" + item.title +"</a></td><td>"
			 + item.writer + "</td><td>" 
			 + item.viewcount + "</td></tr>";
			 $('table').append(newobject);
		});//each end
	
		$('a').on('click',function(e){
			e.preventDefault();
			requestAjax($(this).text());
		}) //click end
	});  //ready end

</script>
<script>
function requestAjax(title){
		$.ajax({
			url : 'board_ajax.jsp',
			type : 'POST',
			data : {'title': title, 'contents': title + " 수업시간입니다.", 'password' : "1111"},
			dataType : 'text',
			success : function(server_result){	//json-텍스트->텍스트-json  stringify
				var jsonobj = JSON.parse(server_result);
				$("#result").text("title : " + jsonobj.title + " contents : " + jsonobj.contents 
						+ " password : " + jsonobj.password);								
			},
			error : function(err){alert(err);},
			complete : function(){alert("실행완료!")}
		});
	}//requestAjax
</script>
<title>공지사항</title>
</head>

<body>

	<table>
		<tr>
			<td>제목</td>
			<td>작성자</td>
			<td>조회수</td>
		</tr>
	</table>
	<div id="result"></div>

	<script src="/thirdproject/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		var boardlist = [
		  {title:'게시물시작' , writer : 'java', viewcount : 100},
		  {title:'게시물2' , writer : 'sql', viewcount : 70},
		  {title:'게시물3' , writer : 'admin', viewcount : 1},
		  {title:'게시물4' , writer : 'bit', viewcount : 10},
		  {title:'게시물5' , writer : 'java', viewcount : 55},
		  {title:'게시물6' , writer : 'html', viewcount : 29},
		  {title:'게시물7' , writer : 'java', viewcount : 1},
		  {title:'게시물8' , writer : 'java', viewcount : 1},
		  {title:'게시물9' , writer : 'java', viewcount : 6},
		  {title:'게시물10' , writer : 'java', viewcount : 0}
		  ];
[출처] [AJAX] 1-1. ajax/json값 전달 실습.|작성자 mongniiii


</body>
</html>