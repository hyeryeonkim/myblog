package com.sbs.java.blog.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Util {
	//똑같은 메서드는 여러개가 있어도 상관없다. 
	public static boolean empty(HttpServletRequest req, String paramName) {
		 String paramValue = req.getParameter(paramName);
		return empty(paramValue);
	}
	public static  boolean empty(Object obj) {
		 if ( obj == null ) {
			 return true;
		 }
		 if ( obj instanceof String) {
			 return ((String)obj).trim().length() == 0 ;
		 }
		return true;
	}
	public static boolean isNum(HttpServletRequest req, String paramName) {
		String paramValue = req.getParameter(paramName);
		return isNum(paramValue);
	}
	public static boolean isNum(Object obj) {
		if ( obj == null ) {
			return false;
		}
		if ( obj instanceof Long ) {
			return true;
		}
		else if ( obj instanceof Integer ) {
			return true;
		}
		else if ( obj instanceof String ) {
			try {
				Integer.parseInt((String)obj);
				return true;
			}
			catch ( NumberFormatException e ) {
				return false;
			}
		}
		return false;
	}
	public static int getInt(HttpServletRequest req, String paramName) {
		return Integer.parseInt(req.getParameter(paramName));
	}
	public static void printEx(String errName, HttpServletResponse resp, Exception e) { // DispatcherServlet의 SQLException과 Exception의 메서드가 똑같음.
		try {																	// 둘다 수용하기 위해서는 예외 타입을  Exception이라고 해주어야 한다.  
			resp.getWriter().append("<h1 style='color:red; font-weight:bold; text-align:left; '>[에러 : " + errName + "]</h1>");   // -> 얘는 try/catch 문으로 감싸주어야 한다.
			resp.getWriter().append("<pre style='text-align:left; font-weight:bold; font-size:1.3rem; '>");   
			e.printStackTrace(resp.getWriter());
			resp.getWriter().append("</pre>");
		} catch (IOException e1) { 
			e1.printStackTrace();  // 얘는 에러가 날일이 없다. 신경쓰지 않아도 된다. 
		}
	}
	public static String getString(HttpServletRequest req, String paramName) {
		
		return req.getParameter(paramName);
	}
	public static String getUriEncoded(String str) { // Uri 인코딩을 해야할 때가 있기에 미리 해놓는 작업.(2020-07-22)
		try { // 문제가 없으면 인코딩된 Uri을 return 하고, 문제가 있으면 원문을 리턴한다.
			return  URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return str;
		}
	}
	public static String getString(HttpServletRequest req, String paramName, String elseValue) {
		if ( req.getParameter(paramName) == null ) { // 아예 없거나
			return elseValue;
		}
		
		if ( req.getParameter(paramName).trim().length() == 0 ) { // 공백이 입력되었거나
			return elseValue;
		}
		
		return getString(req, paramName);
	}
	public static boolean isSuccess(Map<String, Object> rs) {
		
		return ((String)rs.get("resultCode")).startsWith("S-");
	}
	public static String getNewUriRemoved(String Uri, String paramName) {
		String deleteStrStarts = paramName + "=";
		int delStartPos = Uri.indexOf(deleteStrStarts);

		if (delStartPos != -1) {
			int delEndPos = Uri.indexOf("&", delStartPos);

			if (delEndPos != -1) {
				delEndPos++;
				Uri = Uri.substring(0, delStartPos) + Uri.substring(delEndPos, Uri.length());
			} else {
				Uri = Uri.substring(0, delStartPos);
			}
		}

		if (Uri.charAt(Uri.length() - 1) == '?') {
			Uri = Uri.substring(0, Uri.length() - 1);
		}

		if (Uri.charAt(Uri.length() - 1) == '&') {
			Uri = Uri.substring(0, Uri.length() - 1);
		}

		return Uri;
		
	}
	

	public static String getNewUri(String Uri, String paramName, String paramValue) {
		Uri = getNewUriRemoved(Uri, paramName);

		if (Uri.contains("?")) {
			Uri += "&" + paramName + "=" + paramValue;
		} else {
			Uri += "?" + paramName + "=" + paramValue;
		}
		

		Uri = Uri.replace("?&", "?");

		return Uri;
	}
	public static String getNewUriAndEncoded(String Uri, String paramName, String paramValue) {
		if ( Uri.contains("?") == false ) {
			Uri += "?dummy=dummy";  //아무거나 붙여주어도 상관없다. ?가 없다면 붙여주기만을 위한 용도이므로.
		}
		
		if ( Uri.indexOf(paramName + "=") != -1 ) {
			int startPos =  Uri.indexOf(paramName + "=");
		}
		
		Uri += "&" + paramName + "=" + paramValue;
		
		
		return getUriEncoded(getNewUri(Uri, paramName, paramValue));
	}
	
}
