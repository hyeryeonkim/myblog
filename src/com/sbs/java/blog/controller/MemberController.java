package com.sbs.java.blog.controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.java.blog.dto.Member;
import com.sbs.java.blog.service.MailService;
import com.sbs.java.blog.util.Util;

public class MemberController extends Controller {
	//private String gmailId;
	//private String gmailPw;

	public MemberController(Connection dbConn, String actionMethodName, HttpServletRequest req,
			HttpServletResponse resp) {
		super(dbConn, actionMethodName, req, resp);
		//this.gmailId = gmailId;
		//this.gmailPw = gmailPw;

	}

	public void beforeAction() {
		// [ 이 메서드는 게시물 컨트롤러의 모든 액션이 실행되기 전에 실행된다. ]
		// [ 필요없다면 지워도 된다. ]
		super.beforeAction();
	}

	@Override
	public String doAction()  {
		switch (actionMethodName) {
		case "join":
			return doActionJoin(); // controller req, resp 나중에 뺀다고 하셨음.
		case "doJoin":
			return doActionDoJoin();
		case "login":
			return doActionLogin();
		case "doLogin":
			return doActionDoLogin();
		case "doLogout": // 작업을 하는 jsp, 페이지가 아닌 잠깐 들렀다 이동하는 곳은 do를 붙인다!
			return doActionDoLogout();
		case "lookForLoginId":
			return doActionLookForLoginId();
		case "doLookForLoginId":
			return doActionDoLookForLoginId();
		case "lookForLoginPw":
			return doActionLookForLoginPw();
		case "doLookForLoginPw":
			return doActionDoLookForLoginPw();
		case "myPage":
			return doActionMyPage();
		case "memberDataModifyConfirm":
			return doActionMemberDataModifyConfirm();
		case "memberDataModify":
			return doActionMemberDataModify();
		case "doMemberDataModify":
			return doActionDoMemberDataModify();
		case "doAuthMail":
			return doActionDoAuthMail();
		}
		return "";
	}

	private String doActionDoAuthMail() {
		String authCode = "";

		if (!Util.empty(req, "code")) { // cateItemId가 없지 않고 숫자가 맞으면

			authCode = Util.getString(req, "code");

		}

		memberService.setAuthCodeForJoin(authCode);

		return "html:<script> alert('이메일 인증이 완료되었습니다. 로그인 후 이용해주세요.'); location.replace('../member/login'); </script>";
	}

	private String doActionDoMemberDataModify() {

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		String name = req.getParameter("name");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String loginPw = req.getParameter("loginPwReal");

		memberService.memberDataUpdate(name, nickname, email, loginPw, loginedMemberId);

		return "html:<script> alert('회원정보가 변경되었습니다.'); location.replace('../home/main'); </script>";
	}

	private String doActionMemberDataModifyConfirm() {

		return "member/memberDataModifyConfirm.jsp";
	}

	private String doActionMemberDataModify() {

		return "member/memberDataModify.jsp";
	}

	private String doActionMyPage() {
		return "member/myPage.jsp";
	}

	// 임시 비밀번호를 받기 위한 SecureRandom 객체를 생성하는 메서드
	public static String generate(String DATA, int length) {
		SecureRandom random = new SecureRandom();

		if (length < 1)
			throw new IllegalArgumentException("length must be a positive number.");
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(DATA.charAt(random.nextInt(DATA.length())));
		}
		return sb.toString();
	}

	private String doActionDoLookForLoginPw()  {
		String name = req.getParameter("name");
		String loginId = req.getParameter("loginId");
		String email = req.getParameter("email");

		int memberId = memberService.getLookForLoginPw(name, loginId, email);

		if (memberId == -1) {
			return "html:<script> alert('일치하는 회원정보가 존재하지 않습니다.'); history.back(); </script>";
		}

		// 임시 비밀번호를 랜덤 생성하는 코드들
		String ENGLISH_LOWER = "abcdefghijklmnopqrstuvwxyz";
		String ENGLISH_UPPER = ENGLISH_LOWER.toUpperCase();
		String NUMBER = "0123456789";

		// 랜덤을 생성할 대상 문자열
		String DATA_FOR_RANDOM_STRING = ENGLISH_LOWER + ENGLISH_UPPER + NUMBER;

		// 랜덤 문자열 길이
		int random_string_length = 10;

		String randomPw = generate(DATA_FOR_RANDOM_STRING, random_string_length);


		String emailTitle = "harry's life 임시 비밀번호를 확인바랍니다.";
		String emailBody = "<h3>발송드린 암호는 임시 비밀번호 입니다.</h3><br>";
		emailBody +=  "<h3>로그인 후 비밀번호 변경하여 사용바랍니다.</h3><br><br>";
		emailBody += "<h1>임시 비밀번호 : " + randomPw + "</h1>";
		emailBody += "<html><body><h4><a href=" + "http://localhost:8081/blog/s/member/login>📣로그인 바로 가기 </a></h4></body></html>";
		memberService.updateRandomPw(email, memberId, randomPw, emailTitle, emailBody);		
		/*
		 * MailService mailService = new MailService(gmailId, gmailPw, gmailId, "관리자");
		 * boolean sendMailDone = mailService.send(email, "임시 비밀번호를 확인바랍니다.",
		 * "발송드린 암호는 임시 비밀번호 입니다. 로그인 후 변경하여 사용바랍니다. \n" + "임시 비밀번호 : " + randomPw +
		 * "\n로그인 바로 가기 https://harry.my.iu.gy/blog/s/member/login") == 1;
		 * 
		 * resp.getWriter().append(String.format("발송성공 : %b", sendMailDone));
		 */
		return "html:<script> alert('가입하신 이메일로 임시 비밀번호를 발송드렸습니다.'); location.replace('../home/main'); </script>";
	}

	private String doActionLookForLoginPw() {
		return "member/lookForLoginPw.jsp";
	}

	private String doActionDoLookForLoginId() {

		String name = req.getParameter("name");
		String email = req.getParameter("email");

		String loginId = memberService.getLookForLoginId(name, email);
		if (loginId.length() == 0) {
			return "html:<script> alert('일치하는 회원정보가 존재하지 않습니다.'); history.back(); </script>";
		}
		String emailTitle = "요청하신 harry's life 회원가입 아이디를 발송해드립니다.";
		String emailBody = "";
		emailBody += "<h3>harry's life에 가입하신 로그인 아이디는</h3><br>";
		emailBody += "<h1>" + loginId +  "</h1><br>입니다. <br><br>"; 
		emailBody += "<html><body><h4><a href=" + "http://localhost:8081/blog/s/member/login\">📣로그인 바로 가기 </a></h4></body></html>";
		memberService.getLookForLoginId(name, email, emailTitle, emailBody);
		
		
		/* boolean sendMailDone = mailService.send(email, "가입하신 로그인 아이디를 확인바랍니다.", "harry's life에 가입하신 로그인 아이디는 " + loginId
				+ " 입니다. \n\n" + "로그인 바로 가기 https://harry.my.iu.gy/blog/s/member/login") == 1; */

		//resp.getWriter().append(String.format("발송성공 : %b", sendMailDone));

		return "html:<script> alert('가입하신 이메일로 로그인 아이디를 발송드렸습니다.'); location.replace('../home/main'); </script>";
	}

	private String doActionLookForLoginId() {
		return "member/lookForLoginId.jsp";
	}

	private String doActionDoLogout() {

		HttpSession session = req.getSession();
		int loginedMemberId = 0;
		if (session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}

		session.removeAttribute("loginedMemberId");

		String redirectUri = Util.getString(req, "redirectUri", "../home/main");

		return String.format("html:<script> alert('로그아웃 되었습니다.'); location.replace('" + redirectUri + "'); </script>");
	}

	private String doActionDoLogin() {

		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");

		int loginedMemberId = memberService.getMemberIdByLoginIdAndLoginPw(loginId, loginPw);
		
		if (loginedMemberId == -1) {
			return "html:<script> alert('일치하는 정보가 없습니다.'); history.back(); </script>";
		}

		

		Member member = memberService.getMemberById(loginedMemberId);
		
		if (member.getMailAuthStatus() == 0) {
			return "html:<script> alert('이메일 미인증 회원으로 인증 후 이용해주세요.'); history.back(); </script>";
		}

		
		session.setAttribute("loginedMemberId", loginedMemberId); // 최초 키값을 설정하는 코드(개별 저장소 생성)

		String redirectUri = Util.getString(req, "redirectUri", "../home/main");

		return String.format("html:<script> alert('로그인 되었습니다.'); location.replace('" + redirectUri + "'); </script>");
	}

	private String doActionDoJoin()  {
		String loginId = req.getParameter("loginId");
		String name = req.getParameter("name");
		String nickName = req.getParameter("nickname");
		String loginPw = req.getParameter("loginPwReal");
		String email = req.getParameter("email");

		boolean isJoinableLoginId = memberService.isJoinableLoginId(loginId);

		if (isJoinableLoginId == false) {
			return String.format("html:<script> alert('%s은(는) 이미 사용중인 아이디 입니다.'); history.back(); </script>", loginId);
		}

		boolean isJoinableNickName = memberService.isJoinableNickName(nickName);

		if (isJoinableNickName == false) {
			return String.format("html:<script> alert('%s은(는) 이미 사용중인 닉네임 입니다.'); history.back(); </script>", nickName);
		}

		boolean isJoinableEmail = memberService.isJoinableEmail(email);

		if (isJoinableEmail == false) {
			return String.format("html:<script> alert('%s은(는) 이미 사용중인 이메일 입니다.'); history.back(); </script>", email);
		}

		String ENGLISH_LOWER = "abcdefghijklmnopqrstuvwxyz";
		String ENGLISH_UPPER = ENGLISH_LOWER.toUpperCase();
		String NUMBER = "0123456789";

		// 랜덤을 생성할 대상 문자열
		String DATA_FOR_RANDOM_STRING = ENGLISH_LOWER + ENGLISH_UPPER + NUMBER;

		// 랜덤 문자열 길이
		int random_string_length = 10;

		String authCode = generate(DATA_FOR_RANDOM_STRING, random_string_length);

		

		// MailService mailService = new MailService(gmailId, gmailPw, gmailId, "관리자");
		String emailTitle = "harry's life 회원가입을 축하드립니다. 이메일 인증 후 활동해주세요.";
		String emailBody = "";
		emailBody += "<h1>🙌환영합니다. 회원님 ^^</h1><br>";
		emailBody += "<h2>테스트 중입니다. 회원님????</h2><br>";
		emailBody += "<h3>아래 '인증하기' 버튼을 클릭한 후 회원활동을 하실 수 있습니다.</h3><br>";
		emailBody += "<html><body><h4><a href=" + "http://localhost:8081/blog/s/member/doAuthMail?code=" + authCode
				+ ">📣인증하기</a></h4></body></html>";

		//boolean sendMailDone = mailService.send("kim5638yw@gmail.com", emailTitle, emailBody) == 1;

		//resp.getWriter().append(String.format("발송성공 : %b", sendMailDone));
		
		int id = memberService.join(loginId, name, nickName, loginPw, email, authCode, emailTitle, emailBody);
		
		return String.format("html:<script> alert('%s님, 환영합니다.'); location.replace('../home/main'); </script>", name);
	}

	private String doActionLogin() {

		return "member/login.jsp";
	}

	private String doActionJoin() {
		return "member/join.jsp";
	}

	@Override
	public String getControllerName() {
		return "member";
	}
}
