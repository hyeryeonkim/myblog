package com.sbs.java.blog.util;

import java.io.IOException;
import java.sql.SQLException;

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
}
