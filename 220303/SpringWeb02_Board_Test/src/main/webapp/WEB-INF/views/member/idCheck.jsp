<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idcheck</title>
<link rel="stylesheet" type="text/css" href="resources/css/board.css" />
<script src="resources/script/board.js"></script>
</head>
<body>
	<br><br>
	<form action="idCheck" name="frm">
		아이디<input type="text" name="id" value="${id }">
		<input type="submit" value="중복체크"><br><br><br>
		<c:if test="${result==1 }">
			<script type="text/Javascript">
				opener.document.frm.id.value="";
			</script>
			${id }는 이미 사용중인 아이디입니다.
		</c:if>
		<c:if test="${result==-1 }">
			${id }는 사용 가능한 아이디입니다. 
			<input type="button" value="사용" class="cancel" onClick="idok('${id}')">
 		</c:if>
	</form>
</body>
</html>