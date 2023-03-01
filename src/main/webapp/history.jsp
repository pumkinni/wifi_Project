<%@page import="wifiInforms.HistoryItem"%>
<%@page import="java.util.List"%>
<%@page import="service.Services"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위치 히스토리 정보</title>
<h1>위치 히스토리 정보</h1>
</head>
   
<style>
	td, th {
	  text-align: center;
	  border:solid 0.1px ;
	  padding : 10px;
	}
	table{
		width : 100%;
		border:solid 0.1px #000;
		border-collapse : collapse;
	}
	thead{
	    background-color: #009E60;
	    color: #fff;
	}
</style>

<body>

<p>
	<div>
		<a href = "http://localhost:8080/zerobase-mini-project1/index.jsp"> 홈 </a> |
		<a href = "http://localhost:8080/zerobase-mini-project1/history.jsp"> 위치 히스토리 목록 </a>|
		<a href = "http://localhost:8080/zerobase-mini-project1/load-wifi.jsp"> 오픈 API 와이파이 정보 가져오기 </a>
	</div>
</P>

<%
	Services service = new Services();
	List<HistoryItem> itemList = service.historyList();
%>
<p>
<table>
    <thead>
        <tr>
            <th >ID</th>  
            <th >X좌표</th>
            <th >Y좌표</th>
            <th >조회일자</th>
            <th >비고</th>
        </tr>
    </thead>
    <tbody>
			<%
			for(HistoryItem item : itemList){
				%>
				<tr>
					<td> <%=item.getId()%> </td>	
					<td> <%=item.getLat()%> </td>
					<td> <%=item.getLnt()%> </td>
					<td> <%=item.getDate() %> </td>
					<td> 비고
					 </td>
				</tr>
			<%
			}
			%>
	</tbody>
</table>
</p>
</body>
</html>