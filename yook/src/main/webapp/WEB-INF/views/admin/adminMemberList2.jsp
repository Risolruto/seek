<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>MEMBER LIST</title>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<!-- 부트스트랩 -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Custom styles for this template -->
<link href="css/dashboard.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/justified-nav.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-3.5.1.js"></script>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
<style>
.list_hidden{display:none; cursor: pointer;}

thead>tr>th, .list_title {
   text-align: center;
}
#PAGE_NAVI{text-align: center;}
</style>
 
</head>
<body>

<div style="height: 160px;"></div>
<div align="center">
    
</div>
<div style="height: 30px;"></div>

<div align="center" style="width:1200px; margin-left:auto; margin-right:auto;">

   <br/><br/><br/><br/>
   
      <div class="container" style="margin-top:20px;">
      <h3>관리자 회원정보</h3>
   
   <div style="margin-top:20px;">
  <table align="center" class="table table-hover" style="cellpadding:8px;" name="memList">
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
               </tbody>
               </table>
               </div>
               <div id="PAGE_NAVI">
                  <input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX" />
               </div>
               <br> 
               
               
               <%-- <select id="searchOption" size="1">
                  <option id="all" value="all" selected="selected">전체</option>
                  <option id="MEM_ID" value="MEM_ID">회원 아이디</option>
                  <option id="MEM_NAME" value="MEM_NAME">회원 이름</option>
                  <option id="MEM_NICK" value="MEM_NICK">회원 닉네임</option>
               </select> 
               <input type="text" size="16" name="keyword" value="${keyword}"
                  placeholder="검색어 입력" onkeyup="fn_memList(1)">
               <input type="text" style="display: none;" />
               <!-- type="text"가 하나일때는 밑의 설명처럼 서브밋처럼 액션 주소를 따라감, 그래서 꼼수로 보이지않는 텍스트를 하나 더 넣어줌 -->
               <input type="button" value="검 색" onClick="fn_memList(1)">
 --%>
 
      
   </div>
   </div>
  
  



  <script type="text/javascript">
   $(document).ready(function() {  
      fn_memList(1);   
   
     

      $("a[name='click']").on("click", function(e){
          e.preventDefault();
          fn_click($(this));
           });
   });

   function fn_click(obj){
     var comSubmit = new ComSubmit();
         comSubmit.setUrl("<c:url value='/admin/openAdminMemberDetail.do'/>");
         comSubmit.addParam("MEM_NUM", obj.parent().find("#MEM_NUM").val());
         comSubmit.submit();
   }
  
  
   
   function fn_memList(pageNo) { //페이징 함수
      var comAjax = new ComAjax();
      comAjax.setUrl("<c:url value='/admin/selectMemberList.do' />");
      comAjax.setCallback("fn_memListCallback"); //ajax요청 후 호출될 함수의 이름 지정
      comAjax.addParam("PAGE_INDEX", $("#PAGE_INDEX").val());
      comAjax.addParam("PAGE_ROW", 10);
      comAjax.ajax(); //실질적인 ajax기능 수행
   }

   function fn_memListCallback(data) { //콜백함수
      var total = data.TOTAL;    
      var body = $("table[name='memList']>tbody");
      //alert(body);
      body.empty();
      if (total == 0) {
         var str = "<tr>" + "<td colspan='8'>조회된 결과가 없습니다.</td>" + "</tr>";
         body.append(str);
      } else {
         var params = {
            divId : "PAGE_NAVI",
            pageIndex : "PAGE_INDEX",
            totalCount : total,
            eventName : "fn_memList",
          
         };
         gfn_renderPaging(params);

         var str = "";
         $.each(data.list,function(key, value) {
            
            str += "<tr>"
                  + "<td align='center'>"
                  + value.MEM_ID
                  + "</td>"
                  + "<td align='center'>"
                  + value.MEM_NAME
                  + "</td>" 
                  + "<td align='center'>"
                  + value.MEM_ADDRESS1 + value.MEM_ADDRESS2 
                  + "</td>"
                  + "<td align='center'>"
                  + value.MEM_EMAIL 
                  + "</td>"
                  + "<td align='center'>" 
                  + value.MEM_PHONE
                  + "</td>" 
                  + "<td align='center'>" 
                  + value.MEM_JOINDATE
                  + "</td>" 
                  + "<td align='center'>" 
                  + "<a href='#this' name='click'>"
                  + value.MEM_DEL_GB
                  + "<input type='hidden' id='MEM_NUM' name='MEM_NUM' value=" + value.MEM_NUM + ">"
                  + "</a>"
                  + "</td>" 
                  + "</tr>";
         }); 
         //이거 넣어야 데이터 들어감
         body.append(str);
      }
   }
  
   </script>
   <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  
</body>
</html>