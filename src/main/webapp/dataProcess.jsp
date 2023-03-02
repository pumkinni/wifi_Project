<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="service.Services"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function getParameter(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
</script>
<body>
	
 	<!-- 위치 history에 값 저장 -->
	<%  
		String lat = request.getParameter("LAT");
		String lnt = request.getParameter("LNT");
		Services service = new Services();
		
		System.out.println(lat +"  " + lnt);
		
		service.insertHistory(lat, lnt); 
		
	%>

	<!-- 이전 페이지로 돌아가기 -->
	<script>
	var lat = getParameter("LAT");
	var lnt = getParameter("LNT");
	var url = 'index.jsp?LAT=' + lat + '&LNT= ' + lnt;
	location.replace(url);
	</script>


</body>
</html>