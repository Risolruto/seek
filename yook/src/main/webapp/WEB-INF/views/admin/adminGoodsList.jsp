<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

  <head>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    
    <script src="<c:url value='/js/common.js'/>" charset="utf-8" type="text/javascript"></script>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 

  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 

  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 

  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script> 
    <style type="text/css">
			li {list-style: none; float: left; padding: 6px;}
		</style>
  </head>
  <body>
    <div id="layoutSidenav_content">
	<main>
		<div class="container-fluid">
			<h1 class="mt-4">상품 리스트</h1>
			
			<div class="card mb-4">
				<div class="card-header" align="right">
					<button type="button" class="btn btn-primary btn-sm" onclick="javascript:window.location='/yook/adminGoodsWrite.do'">상품 등록</button>
     
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>상품번호</th>
									<th>상품명</th>
									<th>카테고리</th>
									<th>가격</th>
									<th>중량</th>
									<th>재고</th>
									<th></th>
									    
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(list) > 0 }">
										<c:forEach items="${list}" var="row">
											<tr>
												  
												<td align="center">${row.GOODS_NUM}</td>
												<td>${row.GOODS_NAME}</td>
												<td>${row.GOODS_CATE}</td>
												<td>${row.GOODS_PRICE}</td>    
												<td>${row.GOODS_WEIGHT }</td>
												<td>${row.GOODS_ATT_AMOUNT }</td>
												
												<td>
												<form id="frm2" name="frm2">
												<a href="#this" name="mdf"><input type="button"
														class="btn btn-outline-primary btn-sm" value="수정">
														<input type="hidden" name="GOODS_NUM" id="GOODS_NUM"
														value="${row.GOODS_NUM}"></a>
												</form>
												</td>
												
												
											</tr>
										</c:forEach>
									</c:when>

									<c:otherwise>
										<tr>
											<td colspan="9">조회된 결과가 없습니다.</td>
										</tr>
									</c:otherwise>

								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div>
			  <ul>
			    <c:if test="${pageMaker.prev}">
			    	<li><a href="${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
			    </c:if> 
			
			    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
			    	<li><a href="${pageMaker.makeQuery(idx)}">${idx}</a></li>
			    </c:forEach>
			
			    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			    	<li><a href="${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
			    </c:if> 
			  </ul>
			</div>
		</div>
		
		<script type="text/javascript">
   $(document).ready(function(){
         $("a[name='mdf']").on("click",function(e){
               e.preventDefault();
               fn_update($(this));
               });
   });

     

     function fn_update(obj) {
 		var comSubmit = new ComSubmit("frm2");
 		comSubmit.setUrl("<c:url value='/goodsModifyForm.do'/>");
 		comSubmit.addParam("IDX",  obj.parent().find("#GOODS_NUM").val());
 		comSubmit.submit();
 	}
     
</script>   

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