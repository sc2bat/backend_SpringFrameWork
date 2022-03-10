<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/shopping.css" rel="stylesheet">  
<script src="resources/script/member.js"></script>
</head>
<body>
<div id="popup">
	<h1>우편번호검색</h1>
	<form method="post" name="formm" action="findZipNum">
		동 이름 : <input name="dong"  type="text"><input type="submit" value="찾기"  class="submit">
	</form>
	<!-- 검색된 우편번호와 동이 표시되는 곳 -->
	<table id="zipcode">
		<tr><th width="100">우편번호</th><th>주소</th></tr>
		<c:forEach items="${addressList}" var="addressVO">
			<tr>
				<td>${addressVO.zip_num}</td><!-- onClick="result( 우편번호, 시도, 구군, 동);" -->
				<td><a href="#" onClick="result( '${addressVO.zip_num}' ,  '${addressVO.sido}' , '${addressVO.gugun}' , '${addressVO.dong}'  );" >
				${addressVO.sido} ${addressVO.gugun} ${addressVO.dong} ${addressVO.bunji}</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>