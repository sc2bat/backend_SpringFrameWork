function loginCheck(){
	if( document.frm.id.value==''){
		alert("아이디를 입력하세요");
		document.frm.id.focus();
		return false;
	}else if( document.frm.pw.value==''){
		alert("패쓰워드를 입력하세요");
		document.frm.pw.focus();
		return false;
	}else{
		return true;
	}
}

function idCheck(){
	if(document.frm.id.value==''){
		alert('아이디를 입력하여 주십시오.');
		document.frm.id.focus();
		return;
	}
	
	var id = document.frm.id.value;
	var opt = "toolbar = no, menubar=no, resizable=no, width=450, height=200";
	window.open("idcheck?id=" + id, "중복체크", opt);
	// 리퀘스트에도 ? 와 함께 기존처럼 파라미터를 붙여서 링크를 만들 수 있습니다

}

function idok(userid){
	opener.frm.id.value = userid;
	opener.frm.re_id.value = userid;
	self.close();
}

function joinCheck(){
	var f = document.frm;
	if(f.id.value==""){
		alert("아이디를 써주세요");
		f.id.focus();
		return false;
	}else if(f.name.value.length==0){
		alert("이름을 써주세요");
		f.name.focus();
		return false;
	}else if(f.pw.value==""){
		alert("암호는 반드시 입력하여야합니다");
		f.pw.focus();
		return false;
	}else if(f.pw.value != f.pw_check.value){
		alert("암호가 일치하지 않습니다");
		f.pw_check.focus();
		return false;
	}else if(f.id.value != f.re_id.value){
		alert("중복 체크를 하지 않았습니다");
		f.id.focus();
		return false;
	}else if(f.phone.value==""){
		alert("전화번호를 입력하세요");
		f.phone.focus();
		return false;
	}else{
		return ture;
	}
}

function editCheck(){
	var f = document.frm;
	if(f.name.value.length==0){
		alert("이름을 써주세요");
		f.name.focus();
		return false;
	}else if(f.pw.value==""){
		alert("암호는 반드시 입력하여야합니다");
		f.pw.focus();
		return false;
	}else if(f.pw.value != f.pw_check.value){
		alert("암호가 일치하지 않습니다");
		f.pw_check.focus();
		return false;
	}else if(f.phone.value==""){
		alert("전화번호를 입력하세요");
		f.phone.focus();
		return false;
	}else{
		return ture;
	}
}

function boardCheck(){
	var f = document.frm;
	if(f.pass.value==''){
		alert("비밀번호를 써주세요");
		f.pass.focus();
		return false;
	}else if(f.title.value==""){
		alert("제목을 입력해주세요");
		f.title.focus();
		return false;
	}else if(f.content.value==""){
		alert("내용을 입력하세요");
		f.content.focus();
		return false;
	}else{
		return ture;
	}
}

function reply_check(){
	var f = document.frm2;
	if(f.reply.value==''){
		alert('댓글을 작성하세요');
		f.reply.focus();
		return false;
	}else {
		return true;
	}
}


