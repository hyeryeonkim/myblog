package com.sbs.java.blog.controller;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;

import com.sbs.java.blog.dto.Article;
import com.sbs.java.blog.dto.CateItem;
import com.sbs.java.blog.util.Util;

public class ArticleController extends Controller {

	public ArticleController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);

	}

	// 오버라이딩을 통해서 부모의 메서드를 불러왔고, super를 통해서 부모가 먼저 할 일이 있으면 하세요. 그리고 제 일을 하겠습니다. 라는
	// 의미.
	public void beforeAction() {
		// [ 이 메서드는 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다. ]
		// [ 필요없다면 지워도 된다. ]
		super.beforeAction();
	}

	// 상속받은 아이는 부모의 추상메서드를 무조건(싫어도) 상속받아서 구현해야 한다.
	public String doAction() {
		switch (actionMethodName) {
		case "list":
			return doActionList(req, resp);
		case "detail":
			return doActionDetail(req, resp);
		case "doWrite":
			return doActionDoWrite(req, resp);
		case "aboutMe":
			return doActionAboutMe(req, resp);
		case "listWrite":
			return doActionListWrite(req, resp);
		case "modify":
			return doActionModify(req, resp);
		case "delete":
			return doActionDelete(req, resp);
		case "listWriteOk":
			return doActionListWriteOk(req,resp);
		case "modifyOk":
			return doActionModifyOk(req, resp);
		}

		return "";
	}

	private String doActionModifyOk(HttpServletRequest req, HttpServletResponse resp) {
		
		int id = 0;

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) { // cateItemId가 없지 않고 숫자가 맞으면

			id = Util.getInt(req, "id");

		}

		int displayStatus = 0;

		if (!Util.empty(req, "displayStatus") && Util.isNum(req, "displayStatus")) { // cateItemId가 없지 않고 숫자가 맞으면

			displayStatus = Util.getInt(req, "displayStatus");

		}

		int cateItemId = 0;

		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) { // cateItemId가 없지 않고 숫자가 맞으면

			cateItemId = Util.getInt(req, "cateItemId");

		}

		String title = ""; // keywordType이 더 중요하니까 searchkeyword보다 위에 써주는게 낫다.

		if (!Util.empty(req, "title")) { // cateItemId가 없지 않고 숫자가 맞으면

			title = Util.getString(req, "title");

		}

		String body = "";

		if (!Util.empty(req, "body")) { // cateItemId가 없지 않고 숫자가 맞으면

			body = Util.getString(req, "body");

		}
		
		if ( title == null || body == null ) {
			Article article = articleService.articleDetailForModify(id);
			articleService.ArticleModify(article.getId(), article.getCateItemId(), article.getTitle(), article.getBody());
			return "article/modifyOk.jsp";
		}
		// method="post"로 바꾸고 테스트 해보지 않음.  수정하지 않고 페이지 이동했을 때, 게시물 title, body 영향 받는지 테스트하기.
		
		
		articleService.ArticleModify(id, cateItemId, title, body);
		
		
		
		return "article/modifyOk.jsp";
	}

	private String doActionListWriteOk(HttpServletRequest req, HttpServletResponse resp) {
		// 샘이 만들어주신 검색 기능

				int displayStatus = 0;

				if (!Util.empty(req, "displayStatus") && Util.isNum(req, "displayStatus")) { // cateItemId가 없지 않고 숫자가 맞으면

					displayStatus = Util.getInt(req, "displayStatus");

				}

				int cateItemId = 0;

				if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) { // cateItemId가 없지 않고 숫자가 맞으면

					cateItemId = Util.getInt(req, "cateItemId");

				}

				String title = ""; // keywordType이 더 중요하니까 searchkeyword보다 위에 써주는게 낫다.

				if (!Util.empty(req, "title")) { // cateItemId가 없지 않고 숫자가 맞으면

					title = Util.getString(req, "title");
					

				}
				
				

				String body = "";

				if (!Util.empty(req, "body")) { // cateItemId가 없지 않고 숫자가 맞으면

					body = Util.getString(req, "body");
					
				}
				
				
				
				articleService.doArticleWrite(displayStatus, cateItemId, title, body);
				
		return "article/listWriteOk.jsp";
	}

	private String doActionDelete(HttpServletRequest req, HttpServletResponse resp) {
		
		int id = 0;

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) { // cateItemId가 없지 않고 숫자가 맞으면

			id = Util.getInt(req, "id");

		}

		articleService.articleDelete(id);
				
		
		
		
		
		return "article/delete.jsp";
	}

	private String doActionModify(HttpServletRequest req, HttpServletResponse resp) {
		
		int id = 0;

		if (!Util.empty(req, "id") && Util.isNum(req, "id")) { // cateItemId가 없지 않고 숫자가 맞으면

			id = Util.getInt(req, "id");

		}
	
		
		
		
		int cateItemId = 0;

		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) { // cateItemId가 없지 않고 숫자가 맞으면

			cateItemId = Util.getInt(req, "cateItemId");

		}
		
		
		String title = ""; // keywordType이 더 중요하니까 searchkeyword보다 위에 써주는게 낫다.

		if (!Util.empty(req, "title")) { // cateItemId가 없지 않고 숫자가 맞으면

			title = Util.getString(req, "title");

		}

		String body = "";

		if (!Util.empty(req, "body")) { 

			body = Util.getString(req, "body");

		}
		
		
		String cateItemName = "전체";

		if (cateItemId != 0) {
			CateItem cateItem = articleService.getCateItem(cateItemId);
			cateItemName = cateItem.getName();
		}

		req.setAttribute("cateItemName", cateItemName);
		
		Article article = articleService.articleDetailForModify(id);
		req.setAttribute("article", article);
		
		//title, body가 null 도 아니고 길이가 0인 문자였음........ 
/*		if ( title.length() == 0 || body.length() == 0 ) {    이 코드들 없어도 될 것 같음( 초반에 method="get" 방식으로 할 때, 내가 작성했었던 것 같음)
			articleService.ArticleModify(article.getId(), article.getCateItemId(), article.getTitle(), article.getBody());
			return "article/modify.jsp";
		}
		else if ( title.length() == 0 && body.length() == 0 ) {
			articleService.ArticleModify(article.getId(), article.getCateItemId(), article.getTitle(), article.getBody());
			return "article/modify.jsp";
		} */
		
		
		
		articleService.ArticleModify(id, cateItemId, title, body);
		req.setAttribute("id", id);
		req.setAttribute("cateItemId", cateItemId);
		req.setAttribute("title", title);
		req.setAttribute("body", body);
			
	
		
		
		
		

		return "article/modify.jsp";
	}
	
	private String doActionListWrite(HttpServletRequest req, HttpServletResponse resp) {

		

		return "article/listWrite.jsp";
	}

	private String doActionAboutMe(HttpServletRequest req, HttpServletResponse resp) {
		return "article/aboutMe.jsp";
	}

	private String doActionDoWrite(HttpServletRequest req, HttpServletResponse resp) {
		
		return "article/doWrite.jsp";
	}

	private String doActionDetail(HttpServletRequest req, HttpServletResponse resp) {
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}
		if (Util.isNum(req, "id") == false) {
			return "html:id를 정수로 입력해주세요.";
		}
		
		int id = Util.getInt(req, "id");
		
		if (Util.empty(req, "id")) {
			return "html:id를 입력해주세요.";
		}
		
		int cateItemId = 0;

		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) { // cateItemId가 없지 않고 숫자가 맞으면

			cateItemId = Util.getInt(req, "cateItemId");

		}
		
		 
		CateItem cateItem = articleService.getCateItem(cateItemId);

		// 이 article은 그냥(평범한) article이 아니다.
		Article article = articleService.getForPrintArticle(id); // sql 쿼리에 작성해놓은 정보들만이 아닌 부가적으로 추가한 자잘한 (항목 추가한)작성자 등
																	// 항목 모두 불러오는 메서드 네임
		System.out.println(article);
		req.setAttribute("article", article);
		req.setAttribute("cateItem", cateItem);
		
		// return "article/detail.jsp";

		// 작성자명이 게시물 상세보기에서 잠깐 필요한데 필드에까지 추가할 필요가 없다. 그래서 extra__를 이용해서 사용한다. sql에는 영향을
		// 주지 않는 듯. 확인해보기.
		return "article/detail.jsp";
		// 다시 한번 설명! 자질구래한 것들을 모아놓는 것이 dto의 extra 변수이다.
	}

	private String doActionList(HttpServletRequest req, HttpServletResponse resp) {

		// 샘이 만들어주신 검색 기능

		int page = 1;

		if (!Util.empty(req, "page") && Util.isNum(req, "page")) {
			page = Util.getInt(req, "page");
		}

		int cateItemId = 0;

		if (!Util.empty(req, "cateItemId") && Util.isNum(req, "cateItemId")) { // cateItemId가 없지 않고 숫자가 맞으면

			cateItemId = Util.getInt(req, "cateItemId");

		}

		String cateItemName = "전체";

		if (cateItemId != 0) {
			CateItem cateItem = articleService.getCateItem(cateItemId);
			cateItemName = cateItem.getName();
		}

		req.setAttribute("cateItemName", cateItemName);

		String searchKeywordType = ""; // keywordType이 더 중요하니까 searchkeyword보다 위에 써주는게 낫다.

		if (!Util.empty(req, "searchKeywordType")) { // cateItemId가 없지 않고 숫자가 맞으면

			searchKeywordType = Util.getString(req, "searchKeywordType");

		}
		
		String searchKeywordTypeBody = "";
		
		if (!Util.empty(req, "searchKeywordTypeBody")) { // cateItemId가 없지 않고 숫자가 맞으면

			searchKeywordTypeBody = Util.getString(req, "searchKeywordTypeBody");

		}
		
		
		
		

		String searchKeyword = "";

		if (!Util.empty(req, "searchKeyword")) { // cateItemId가 없지 않고 숫자가 맞으면

			searchKeyword = Util.getString(req, "searchKeyword");

		}

		

		int itemsInAPage = 10; // 게시물 리스트에 보여줄 게시물 개수
		int totalCount = articleService.getForPrintListArticlesCount(cateItemId, searchKeywordType, searchKeywordTypeBody, searchKeyword);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);
		

		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);

		List<Article> articles = articleService.getForPrintListArticles(page, cateItemId, itemsInAPage,
				searchKeywordType, searchKeywordTypeBody, searchKeyword);
		req.setAttribute("articles", articles);

	

		return "article/list.jsp";
	}
}
