<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
/* lib   (나중에 다른 곳으로 옮길 예정이라셨음) */
.form1 {
	position:absolute;
	display: block;
	width: 400px;
	top:30%;
	left:50%;
	transform:translateX(-50%) translateY(-50%);
	
}

.form1 .form-row {
	padding:5px;
	align-items: center;
	display:flex;
	text-align:center;
	
	
	
}



.form1 .form-row>.label {
	width:30%;
	
	
}

.form1 .form-row>.input {
	flex-grow:1;
	
}

.form1 .form-row .input > input {
	border-radius:5px;
}

.form1 .form-row:nth-child(4) .input {
	width:80px;
	flex-grow:0;
	margin-left:0;
	
}



.form1 .form-row:nth-child(4) .input:nth-child(2) {
	width:160px;
	flex-grow:0;
	margin-left:auto;
	margin-right:0;
	
}


.form1 .form-row>.input>input, .form1 .form-row>.input>textarea {
	display: block;
	width: 100%;
	box-sizing: border-box;
	padding: 10px;
}

.form1 .form-row>.input>select {
	padding: 10px;
}

.form1 .form-row>.input>textarea {
	height: 500px;
	
}



/* cus */
.write-form-box {
	position:absolute;
	top:50%;
	left:50%;
	transform:translateX(-50%) translateY(-50%);
	height:600px;
	width:500px;
	border:2px solid gold;
}

.blog-name {
	position:absolute;
	font-size:2.4rem;
	top:70%;
	left:50%;
	transform:translateX(-50%);
	letter-spacing:10px;
	
}


.write-form-box .blank-box {
	position:absolute;
	top:200px;
	right:5%;
	
}
.emoji, .pixabay, .github, .write-editor {
	width: 200px;
}

.emoji, .pixabay, .github, .write-editor a {
	display: block;
}
@media ( max-width :799px) {
	.form1 {
		margin-top:200px;
		
	}
	.blog-name {
		font-size:1.7rem;
		top:100%;
		left:96%;
		transform:translateX(-50%);
		letter-spacing:10px;
	}
	.write-form-box {
		border:none;
		width:20%;
		top:250px;
		left:42%;
		
		
	}
	.write-form-box .blank-box {
	top:240px;
	right:-8%;
	
	
	}
	.form1 .form-row {
		display: block;
		width:80%;
		margin-left:auto;
		margin-top:auto;
		text-align:left;
		
	}
	.form1 .form-row>.input {
		flex-grow:1;
	}
	.form1 .form-row .label {
		width:45%;
	}
	.form1 .form-row:nth-child(4) { 
		display:flex;
		
	}
	
	
}
</style>

<div class="write-form-box">
	<form name="form" action="doLogin" method="POST" class="write-form form1">	
		<div class="form-row">
			<div class="label">로그인 아이디</div>
			<div class="input">
				<input name="loginId" type="text" placeholder="로그인 아이디를 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="label">로그인 비밀번호</div>
			<div class="input">
				<input name="loginPw" type="text" placeholder="로그인 비밀번호를 입력해주세요." />
			</div>
		</div>
		<div class="form-row">
			<div class="input">
				<input type="submit" value="로그인" />
			</div>
		</div>
		<div class="form-row">
			<div class="input">
				<input type="button" value="취소" onclick="history.back();" />
			</div>
			<div class="input">
				<input type="button" value="회원가입" onclick="location.href='join'" />
			</div>
		</div>
	</form>
	<div class="blog-name">harry.my.iu.gy</div>
</div>


<%@ include file="/jsp/part/foot.jspf"%>