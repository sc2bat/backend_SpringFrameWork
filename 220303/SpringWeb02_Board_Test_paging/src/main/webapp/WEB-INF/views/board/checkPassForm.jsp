<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardCheckPassForm</title>
<script type="text/javascript">
	function passCheck(){
		if(document.frm.pass.value.length == 0){
			alert("비밀번호를 입력하세요");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div align="center">
		<h1>비밀번호 확인</h1>
		<form action="boardEdit" name="frm" method="get">
			<input type="hidden" name="boardnum" value="${boardnum }">
			<table style="width:80%">
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pass" size="20"></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="확인" onClick="return passCheck();">
			<br><br>${message }
		</form>
	</div>
</body>
</html>

