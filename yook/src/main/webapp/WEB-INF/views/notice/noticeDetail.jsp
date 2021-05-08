<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body>
 
   <div style="height: 160px;"></div>

   <!-- title -->
   <div align="center">
      <h3>공지사항</h3>
   </div>
   <div style="height: 30px;"></div>
   
   <div class="container" style="width:65%">
      <div style="display:inline-block; float:right;"></div>
      
      <hr>
      
      <div>
      	 <div class="row">
         <div class="col"><h4>${map.NOTICE_TITLE}</h4></div>
         <div class="col" align="right"; style="margin-top:5px;">
         <h8>작성일&nbsp;&nbsp;&nbsp; ${map.NOTICE_DATE}</h8></div>
      	 </div>
      
      <hr>
      
      <div style="padding:20px; margin-bottom:100px; white-space:pre-wrap;">
         <span>${map.NOTICE_CONTENT}</span>
      </div>
      
      <hr>

	  <form id="frm" name="frm">
      <div style="float:right;">
         
         <input type="hidden" name="NOTICE_NUM" id="NOTICE_NUM" value="${map.NOTICE_NUM}"/>
        <button type="button" class="btn btn-outline-primary" onclick="javascript:history.go(-1);" style="margin-right:6px;">목록</button>
         
        
      </div>
      </form>
   </div>
   </div>
  
</body>
