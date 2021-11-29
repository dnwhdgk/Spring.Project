// document (HTML 문서)가 로드가 완료되었을 때 실행
$(document).ready(function () {
	
	var id_check_flag = false;
	var pw_check_flag = false;
	var em_check_flag = false;
	
	// user_pw2 id를 가지는 HTML 태그가 변경되었을 때 실행
	$('#user_pw2').change(function() {
		// 각각의 id를 가지는 HTML 태그에서 value 값을 가져옴
		var user_pw  = $('#user_pw').val();
		var user_pw2 = $('#user_pw2').val();
		if (user_pw != user_pw2) {
			// 해당 id를 가지는 HTML 태그를 보여줌
			$('#diff_pw').show();
			// 해당 id를 가지는 HTML 태그를 숨김
			$('#equal_pw').hide();
			pw_check_flag = false;
		}
		else {
			$('#equal_pw').show();
			$('#diff_pw').hide();
			pw_check_flag = true;
		}
	});
	
	// id_check id를 가지는 HTML 태그가 클릭되었을 때 실행
	$('#id_check').click(function() {
		var user_id = $('#user_id').val();
		
		if(user_id == '') {
			alert('아이디를 입력하세요.');
			return;
		}
		
		// 비동기통신 AJAX 진행
		$.ajax({
			// 전송부
			type : 'GET',		// 전송방식
			url  : './idCheck',	// 전송할 url
			data : {			// 전송하는 데이터
				// parameter name : parameter value
				user_id : user_id
			},
			// 송신부
			dataType : 'text',	// 송신받는 데이터의 타입
			success : function(data) {	// 통신에 성공했을 때
				// 사용가능 가능 아이디
				if(data == 0) {
					alert('사용가능한 아이디입니다.');
					$('#user_id').attr('readonly', true);
					id_check_flag = true;
				}
				// 사용중인 아이디
				else if(data == 1) {
					alert('사용중인 아이디입니다.');
					$('#user_id').attr('value', '');
				}
				// 데이터베이스 오류
				else {
					alert('데이터베이스 오류가 발생했습니다.');
				}
			},
			error : function() {	// 통신에 실패했을 때
				alert('중복체크 실패');
			}
		});
	});
	
	$('#getAddrBtn').click(function(){
		var postCode  = $('#entry_postcode5').val();
		var doroAddr  = $('#entry_address').val();
		var jibunAddr = $('#entry_extra_info').val();
		
		$('#addr1').val(postCode);
		$('#addr2').val(doroAddr + ' ' + jibunAddr);
	});
	
	// sendAuthBtn를 가지는 HTML 태그가 클릭되었을 때 실행
	$('#sendAuthBtn').click(function() {
		var user_mail = $('#user_mail_modal').val();
		
		if(user_mail == '') {
			alert('이메일을 입력하세요.');
			return;
		}
		
		// 비동기통신 AJAX 진행
		$.ajax({
			// 전송부
			type : 'GET',				// 전송방식
			url  : './sendAuthMail',	// 전송할 url
			data : {					// 전송하는 데이터
				// parameter name : parameter value
				user_mail : user_mail
			},
			// 송신부
			dataType : 'text',	// 송신받는 데이터의 타입
			success : function(data) {	// 통신에 성공했을 때
				// 메일 전송 성공
				if(data == 0) {
					alert('인증 메일을 전송했습니다.');
					$('#authDiv').show();
					$('#user_mail_modal').attr('readonly', 'true');
					$('#sendAuthBtn').attr('class', 'btn btn-warning btn-block');
					$('#sendAuthBtn').text('인증번호 재전송');
				}
				// 메일 전송 실패
				else if(data == -1) {
					alert('다시 메일주소를 입력하세요.');
					$('#user_mail_modal').val('');
				}
			}
		});
	});
	
	// authMailBtn를 가지는 HTML 태그가 클릭되었을 때 실행
	$('#authMailBtn').click(function(){
		var user_mail = $('#user_mail_modal').val();
		var auth_num  = $('#auth_num').val();
		
		$.ajax({
			type : 'POST',
			url  : './mailAuth',
			data : {
				user_mail : user_mail,
				auth_num  : auth_num
			},
			dataType : 'text',
			success : function(data) {
				if(data == 0) {
					alert('인증되었습니다.');
					$('#user_email').val(user_mail);
					$('#emailAuthModal').modal('hide');
					em_check_flag = true;
				}
				else if(data == 1) {
					alert('인증번호가 다릅니다.');
					$('#auth_num').val('');
				}
				else if(data == -1) {
					alert('데이터베이스 오류가 발생했습니다.');
					$('#auth_num').val('');
				}
			}
		});
		
	});
	
	// form 태그가 sumbit 이벤트를 발생시켰을 때
	$('form').submit(function(event){
		var user_id     = $('#user_id').val();
		var user_pw     = $('#user_pw').val();
		var user_pw2    = $('#user_pw2').val();
		var user_name   = $('#user_name').val();
		var user_gender = $('#user_gender').val();
		var user_email  = $('#user_email').val();
		var addr1  = $('#addr1').val();
		var addr2  = $('#addr2').val();
		var addr3  = $('#addr3').val();
		
		if(user_id == "" || user_pw == "" || user_pw2 == "" ||
				user_name == "" || user_gender == "" || user_email == "" ||
				addr1 == "" || addr2 == "" || addr3 == "") {
			alert('모든 값을 입력하세요.');
			event.preventDefault();
			return;
		}
		
		if(!id_check_flag) { 
			alert('아이디 중복체크를 해주세요.');
			event.preventDefault();
			return;
		}
		if(!pw_check_flag) {
			alert('비밀번호를 확인해주세요.');
			event.preventDefault();
			return;
		}
		if(!em_check_flag) {
			alert('이메일 인증을 해주세요.');
			event.preventDefault();
			return;
		}
		
	});
	
});