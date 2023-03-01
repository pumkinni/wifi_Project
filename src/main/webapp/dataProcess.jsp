<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="service.Services"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	// 위치 history에 값 저장
	<%  
		String lat = request.getParameter("LAT");
		String lnt = request.getParameter("LNT");
		Services service = new Services();
		
		System.out.println(lat +"  " + lnt);
		
		service.insertHistory(lat, lnt); 
		
	%>

	// 이전 페이지로 돌아가
	<script>history.back();</script>


</body>
</html>