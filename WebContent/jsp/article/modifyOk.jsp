<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<title>modify-ok</title>

<div class="WriteOk">게시물 수정 성공!</div>
<button type="button" onclick="location.href='detail?id=${param.id}&cateItemId=${param.cateItemId}'" class="modify-button" >뒤로가기</button>
<style>
.WriteOk {
	font-size:4rem;
	font-weight:bold;
	position:absolute;
	top:40%;
	left:50%;
	transform:translateX(-50%) translateY(-40%);
	color:orange;
}
.modify-button {
	position:absolute;
	top:50%;
	left:50%;
	transform:translateX(-50%) translateY(-40%);
}
</style>



<%@ include file="/jsp/part/foot.jspf"%>