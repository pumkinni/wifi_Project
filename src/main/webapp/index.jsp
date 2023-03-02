<%@page import="wifiInforms.PublicWifiItem"%>
<%@page import="wifiInforms.PublicWifiList"%>
<%@page import="wifiInforms.WifiKilometer"%>
<%@page import="java.util.PriorityQueue"%>
<%@page import="service.Services"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보</title>
<h1>와이파이 정보 구하기</h1>
</head>
 <script>
        function clickBtn(){
            window.navigator.geolocation.getCurrentPosition( function(position){ //OK
                var lat= position.coords.latitude;
                var lnt= position.coords.longitude;
                
                LAT.value = lat;
               	LNT.value = lnt;
            }); 
        }
</script>

    
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
<p>
	<div>
		<form method="get" action="dataProcess.jsp">
		 LAT : 
		<input type="text" id="LAT" name="LAT" required minlength="0" maxlength="15" size="15", value = "0.0">
		, LNT :  
		<input type="text" id="LNT" name="LNT" required minlength="0" maxlength="15" size="15", value = "0.0">
		
		<input type="button" onclick="clickBtn()" value= "내 위치 가져오기">
		
		<input type="submit" value="근처 wifi 정보 보기" onclick = "btn()">
		
 		</form>
	
	</div>
</p>
<p>
<table>
    <thead>
        <tr>
            <th >거리(Km)</th>  
            <th >관리번호</th>
            <th >자치구</th>
            <th >와이파이명</th>
            <th >도로명주소</th>
         	<th >상세주소</th>
         	<th >설치위치(층)</th>
         	<th >설치유형</th>
         	<th >설치기</th>
         	<th >서비스구분</th>
         	<th >망종류</th>
         	<th >설치년도</th>
         	<th >실내외구분</th>
         	<th >WIFI접속환경</th>
         	<th >X좌표</th>
         	<th >Y좌표</th>
         	<th >작업일자</th>
         	
        </tr>
    </thead>
    <%
    String lat = request.getParameter("LAT");
	String lnt = request.getParameter("LNT");
	
	System.out.println(lat);
	System.out.println(lnt);
	

   	if (lat == null &  lnt == null){
   	
   	%>
   	<tbody>
        <tr>
            <td colspan="17">위치 정보를 입력한 후에 조회해 주세요</td>
        </tr>
    </tbody>
   	<% 	} else {
   		
   		%>
   		<tbody>
		<%	Services service = new Services(); 
   		
			for(WifiKilometer nearWifi : service.wifiList(lat, lnt)){
				PublicWifiItem wifi = nearWifi.getItem();  %>
				<tr>
	 		 	<td><%=nearWifi.getKilo()%> </td>
				<td> <%=wifi.getX_SWIFI_MGR_NO()%> </td>
				<td> <%=wifi.getX_SWIFI_WRDOFC() %> </td>
				<td> <%=wifi.getX_SWIFI_MAIN_NM() %> </td>
				<td><%=wifi.getX_SWIFI_ADRES1() %></td>
				<td><%=wifi.getX_SWIFI_ADRES2() %></td>
				<td><%=wifi.getX_SWIFI_INSTL_FLOOR() %></td>
				<td><%=wifi.getX_SWIFI_INSTL_TY() %></td>
				<td><%=wifi.getX_SWIFI_INSTL_MBY() %></td>
				<td><%=wifi.getX_SWIFI_SVC_SE() %></td>
				<td><%=wifi.getX_SWIFI_CMCWR() %></td>
				<td><%=wifi.getX_SWIFI_CNSTC_YEAR() %></td>
				<td><%=wifi.getX_SWIFI_INOUT_DOOR() %></td>
				<td><%=wifi.getX_SWIFI_REMARS3() %></td>
				<td><%=wifi.getLAT() %></td>
				<td><%=wifi.getLNT() %></td>
				<td><%=wifi.getWORK_DTTM() %></td>
				</tr>	
			
				<% 
			}
   	}
				%>

			</tr>
    <tbody>
</table>
</p>
</body>
</html>