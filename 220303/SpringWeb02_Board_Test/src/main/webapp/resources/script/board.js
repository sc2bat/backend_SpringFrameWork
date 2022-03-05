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

function boardCheck(){
	var f = document.frm;
	if(f.pass.value == ''){
		alert('password none');
		f.pass.focus();
		return false;
	}else if(f.title.value == ''){
		alert('title none');
		f.title.focus();
		return false;
	}else if(f.content.value == ''){
		alert('content none');
		f.content.focus();
		return false;
	}else{
		return true;
	}
}

function idCheck(){
	var f = document.frm;
	if(f.id.value == ''){
		alert('id none');
		f.id.focus();
		return;
	}
	
	var id = f.id.value;
	var opt = "toolbar=no, menubar=no, resizable=no, width=400, height=300";
	window.open("idCheck?id=" + id, "idCheck", opt);
}

function idok(userid){
	opener.frm.id.value = userid;
	opener.frm.re_id.value = userid;
	self.close();
}

function joinCheck(){
	var f = document.frm;
	if(f.id.value == ''){
		alert('id none');
		f.id.focus();
		return false;
	}else if(f.id.value != f.re_id.value){
		alert('id duplicate check none');
		f.id.focus();
		return false;
	}else if(f.pw.value == ''){
		alert('pw none');
		f.pw.focus();
		return false;
	}else if(f.pw.value != f.pw_check.value){
		alert('pw != pw_check');
		f.pw_check.focus();
		return false;
	}else if(f.name.value == ''){
		alert('name none');
		f.name.focus();
		return false;
	}else if(f.phone.value == ''){
		alert('phone none');
		f.phone.focus();
		return false;
	}else if(f.email.value == ''){
		alert('email none');
		f.email.focus();
		return false;
	}else{
		return true;
	}
}

function editCheck(){
	var f = document.frm;
	if(f.pw.value == ''){
		alert('pw none');
		f.pw.focus();
		return false;
	}else if(f.pw.value != f.pw_check.value){
		alert('pw != pw_check');
		f.pw_check.focus();
		return false;
	}else if(f.name.value == ''){
		alert('name none');
		f.name.focus();
		return false;
	}else if(f.phone.value == ''){
		alert('phone none');
		f.phone.focus();
		return false;
	}else if(f.email.value == ''){
		alert('email none');
		f.email.focus();
		return false;
	}else{
		return true;
	}
}

function reply_check(){
	var f = document.frm2;
	if(f.content.value==''){
		alert('reply content none');
		f.content.focus();
		return false;
	}
	return true;
}


function open_win(url, name){
	window.open(url, name, "toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=230");
}

function boardCheck(){
	var f = document.frm;
	if(f.pass.value ==''){
		alert('pass none');
		f.pass.focus();
		return false;
	}else if(f.email.value ==''){
		alert('email none');
		f.email.focus();
		return false;
	}else if(f.title.value ==''){
		alert('title none');
		f.title.focus();
		return false;
	}else if(f.content.value ==''){
		alert('content none');
		f.content.focus();
		return false;
	}
	return true;
}









