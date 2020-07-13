<%@ include file="/jsp/part/head.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/highlight.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/styles/default.min.css">

<title>CodePen</title>
</head>
<body translate="no" style="height:800px;">


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/css.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/javascript.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/java.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/xml.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/php-template.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.1.1/languages/sql.min.js"></script>

	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css" />

	<script
		src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

	<script
		src="https://uicdn.toast.com/editor-plugin-code-syntax-highlight/latest/toastui-editor-plugin-code-syntax-highlight-all.min.js"></script>

	<link rel="stylesheet"
		href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />

	
	<div class="editor">
		<h1>TOAST UI Editor</h1>
		<div class="emoji">
			<a href="https://www.emojiengine.com/ko/" target="_blank">
				😵 emoji 이동
			</a>
		</div>
		<div class="naver">
			<a href="https://search.naver.com/search.naver?sm=top_hty&fbm=0&ie=utf8&query=%EB%A7%9E%EC%B6%A4%EB%B2%95+%EA%B2%80%EC%82%AC%EA%B8%B0" target="_blank">
				✅ naver 맞춤법 검사기 이동
			</a>
		</div>
		<div class="google">
			<a href="https://google.com" target="_blank">
				🌐 google
			</a>
		</div>
		<div id="editor1"></div>
		<script id="rendered-js">
			console.clear();
			var editor1 = new toastui.Editor({
				el : document.querySelector('#editor1'),
// 				height : '600px',
				width : '100%',
				initialEditType : 'markdown',
				previewStyle : 'vertical',
				initialValue : "# 안녕하세요",
				plugins : [ toastui.Editor.plugin.codeSyntaxHighlight ]
			});
		</script>
	</div>




</body>
</html>







<%@ include file="/jsp/part/foot.jspf"%>