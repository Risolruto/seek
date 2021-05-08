<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>


<!-- icon -->
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
	
<script src="<c:url value='/js/common1.js'/>" charset="utf-8"></script>
<title>상품</title>

</head>

<body>

	<div class="album py-5 ">
		<div class="container">

			<div class="modal-body">
				<div class="row">
					
					<div class="col-md-4 product_img" style="margin-left:220px;">
						<img src="/yook/img/goods_upload/${map.GOODS_IMAGE}" width="330" height="330">
					</div>
					
					<div class="col-md-4" style="border: none; size:3px;">
						<div class="card mb-6 bg-white mt-5" style="border: none;">
							<h2 class="display-3">${list.GOODS_NAME} </h2>
							<span class="display-4">${Weight}</span>
							<span>${map.GOODS_CATE }</span>
							<p>
							<c:if test="${Weight == '[300g(14mm)]' }">
							100g당 ${map.GOODS_PRICE / 3}원
							</c:if>
							<c:if test="${Weight == '[300g(16mm)]' }">
							100g당 ${map.GOODS_PRICE / 3}원
							</c:if>
							<c:if test="${Weight == '[300g(18mm)]' }">
							100g당 ${map.GOODS_PRICE / 3}원
							</c:if>
							<c:if test="${Weight == '[300g]' }">
							100g당 ${map.GOODS_PRICE / 3}원
							</c:if>
							<c:if test="${Weight == '[200g]' }">
							100g당 ${map.GOODS_PRICE / 2}원
							</c:if>
							<c:if test="${Weight == '[150g]' }">
							100g당 ${map.GOODS_PRICE / 1.5}원
							</c:if>
							</p>
							<h3 class="cost display-3">${map.GOODS_PRICE } 원</h3>
								<div class="card-body">
									<div class="col-sm-8"></div>
								</div>
								<form id="frm" name="frm" method="post">
									<div class="btn-ground">
										<div style="height: 10px;"></div>
										<button style="width: 300px; height: 38px;" class="btn btn-danger" id="insertBasket" 
		            						onclick="fn_InsertBasket()"><span class="glyphicon glyphicon-shopping-cart"></span> Add To Cart</button>
										<input type="hidden" name="IDX" id="IDX" value="${map.GOODS_NUM }"/>
										<input type="hidden" name="GOODS_WEIGHT" id="GOODS_WEIGHT" value="${map.GOODS_WEIGHT }	"/>	
									</div>
								</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- buttons -->
	<div class="container" align="center">
	<button type="submit" class="btn btn-primary center">상품정보</button>
	<button type="submit" class="btn btn-primary center" onclick="location.href='${contextPath}/yook/gd.do'">상품상세</button>
	<button type="submit" class="btn btn-primary center" onclick="location.href='${contextPath}/yook/review.do'">상품리뷰</button>
	<br><br>
	</div>
	

</body>
</html>



<script type="text/javascript">

	function fn_InsertBasket() { // 장바구니
	 //if(doubleSubmitCheck()) return; // 중복클릭 방지
		   
	   if(${session_MEM_ID != null}){
	      
	         var url = "/yook/shop/basketPopUp.do";
	         var name = "popup";
	         var option = "width=382, height=227, top=300, left=500, location=no";
	         
	          var comSubmit = new ComSubmit("frm");
	         comSubmit.setUrl("<c:url value='/shop/insertBasket.do'/>");
	         window.open(url,name,option);
	         comSubmit.submit();
	      
	   }else {
		  var comSubmit = new ComSubmit("frm"); 
	      alert("로그인 후 이용해주세요.");
	      comSubmit.setUrl("<c:url value='/LoginForm.do'/>");
	      comSubmit.submit();
	   }
		   
	}

</script>