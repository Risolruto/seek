<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<%@ include file="/WEB-INF/include/include-header.jspf" %>
    
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  
    <script src="<c:url value='/js/common.js'/>" charset="utf-8" type="text/javascript"></script>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 

  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 

  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>  

  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script> 

  </head>
  
<body>
<div id="layoutSidenav_content">
	
		<div class="container-fluid">
      <h3>관리자 회원정보</h3>
   <div class="card mb-4">
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable"  width="100%" cellspacing="0">
					         <colgroup>
					         <col width="10%" />
					         <col width="10%" />
					         <col width="20%" />
					         <col width="20%" />
					         <col width="15%" />
					         <col width="10%" />
					         <col width="15%" />
					         </colgroup>
					         <thead>
					            <tr style="background-color:#EAEAEA;">
					               <th scope="col">아이디</th>
					               <th scope="col">이름</th>
					               <th scope="col">주소</th>
					               <th scope="col">이메일</th>
					               <th scope="col">핸드폰</th>
					               <th scope="col">가입날짜</th>
					               <th scope="col">상태</th>
					            </tr>   
					         </thead>
					         <tbody>
<%-- 								<c:choose> --%>
<%-- 								//empty인지 확인하기 위함
									<c:when test="${fn:length(list) > 0 }">
 --%>
 
 										<c:forEach items="${list}" var="row">
											<tr>
												  
												<td align="center">${row.GOODS_NUM}</td>
												<td>${row.MEM_ID}</td>
												<td>${row.MEM_NAME}</td>
												<td>${row.MEM_ADDRESS1}</td>    
												<td>${row.MEM_PHONE}</td>
												<td>${row.MEM_DEL_GB}</td>
											</tr>
										</c:forEach>
<%-- 									
</c:when>
1.others를 다른 경우에 또 쓸수있다 -> 확장성
2. else를 일단 지움
 --%>
<!--  										0인경우 
when 을 if로 바꿀수도 있다
-> choice 안써도됨
when을 쓰기위해서는 choice를 선언해야만 한다-->
									
								<%-- 	<c:if test="${fn:length(list) =0 }">
										<tr>
											<td colspan="9">조회된 결과가 없습니다.</td>
										</tr>
									</c:if> --%>
<!-- when은 if문으로 여러번 타면 문제가 발생할수 있음으로
when은 맞는애를 찾아서 맞는것만 도는데, if문은 조건문을 모두 검색한다. -->

									<c:if test="${empty list}">
										<tr>
											<td colspan="9">조회된 결과가 없습니다.</td>
										</tr>
									</c:if>									
<%-- 
								</c:choose> --%>
					         
               			</table>
	               <div id="PAGE_NAVI">
	                  <input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX" />
	               </div>
              
               </div>
               <br> 

   </div>
   </div>
  </div></div>
	
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>
</html>