<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardCheckPass</title>
</head>
<body>

<script type="text/javascript">
	if(window.name == "update"){
		window.opener.location.href="boardUpdateForm?num=${boardnum}";
	}else if(window.name == "delete"){
		var ans = confirm("정말로 삭제?");
		if(ans){
			window.opener.location.href="boardDelete?num=${boardnum}";
		}
	}
	self.close();
</script>
</body>
</html>