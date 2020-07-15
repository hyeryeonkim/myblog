package com.sbs.java.blog.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;
import com.sbs.java.blog.dto.Member;
	
	public class MemberController extends Controller {
		
		
		
	public MemberController(Connection dbConn, String actionMethodName, HttpServletRequest req, HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
	}
	
	public void beforeAction() {
		// [ 이 메서드는 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다. ]
		// [ 필요없다면 지워도 된다. ]
		super.beforeAction();
	}
	
	
	@Override
	public String doAction() {
		switch ( actionMethodName ) {
		case "join":
			return doActionJoin(req, resp); //controller req, resp 나중에 뺀다고 하셨음. 
		case "doJoin":
			return doActionDoJoin(req, resp);
		case "login":
			return doActionLogin(req, resp);
		case "doLogin":
			return doActionDoLogin(req, resp);
		case "logout":
			return doActionLogout(req, resp);
		}
		return "";
	}


	private String doActionLogout(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession session = req.getSession();
		int loginedMemberId = 0;
		if ( session.getAttribute("loginedMemberId") != null ) {
		  loginedMemberId = (int)session.getAttribute("loginedMemberId");
		}
		
		System.out.println("logout_id : " + loginedMemberId);
		
		session.removeAttribute("loginedMemberId");
		
		return "html:<script> alert('로그아웃 되셨습니다.'); location.replace('../home/main'); </script>";
	}

	private String doActionDoLogin(HttpServletRequest req, HttpServletResponse resp) {
		
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");  
		
		boolean isJoinableLoginId = memberService.isJoinableLoginId(loginId);
		
		if ( isJoinableLoginId ) {
			return String.format("html:<script> alert('%s은(는) 존재하지 않는 아이디 입니다.'); history.back(); </script>", loginId);
		}
		
		
		int loginedMemberId = memberService.getMemberIdByLoginIdAndLoginPw(loginId, loginPw);
		
		if ( loginedMemberId == -1 ) {
			return "html:<script> alert('일치하는 정보가 없습니다.'); history.back(); </script>";
		}
		
		
		HttpSession session = req.getSession();
		session.setAttribute("loginedMemberId", loginedMemberId);
		
		
		
		return String.format("html:<script> alert('로그인되셨습니다.'); location.replace('../home/main'); </script>");
	}

	private String doActionDoJoin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String name = req.getParameter("name");
		String nickName = req.getParameter("nickname");
		String loginPw = req.getParameter("loginPwReal");
		String email = req.getParameter("email");
		
		boolean isJoinableLoginId = memberService.isJoinableLoginId(loginId);
		
		if ( isJoinableLoginId == false ) {
			return String.format("html:<script> alert('%s은(는) 이미 사용중인 아이디 입니다.'); history.back(); </script>", loginId);
		}
		
		boolean isJoinableNickName = memberService.isJoinableNickName(nickName);
		
		if ( isJoinableNickName == false ) {
			return String.format("html:<script> alert('%s은(는) 이미 사용중인 닉네임 입니다.'); history.back(); </script>", nickName);
		}

		
		boolean isJoinableEmail= memberService.isJoinableEmail(email);
		
		if ( isJoinableEmail == false ) {
			return String.format("html:<script> alert('%s은(는) 이미 사용중인 이메일 입니다.'); history.back(); </script>", email);
		}
		
		
		int id = memberService.join(loginId, name, nickName, loginPw, email);
		
	
		
		return String.format("html:<script> alert('%s님, 환영합니다.'); location.replace('../home/main'); </script>", name);
	}

	private String doActionLogin(HttpServletRequest req, HttpServletResponse resp) {
		
		return "member/login.jsp";
	}

	private String doActionJoin(HttpServletRequest req, HttpServletResponse resp) {
		return "member/join.jsp";
	}
}
