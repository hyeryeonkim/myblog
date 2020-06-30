package com.sbs.java.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.security.provider.certpath.ResponderId;

@WebServlet("/s/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String url = "jdbc:mysql://site24.iu.gy:3306/site24?serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true";
		String user = "site24";
		String password = "sbs123414";
		String driverName = "com.mysql.cj.jdbc.Driver";
		
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		Connection connection = null;
		Statement stmt = null;
		
		String sql = "";
		sql += String.format("INSERT INTO article ");
		sql += String.format("SET regDate = NOW() ");
		sql += String.format(", updateDate = NOW() ");
		sql += String.format(", title = '%s' ", title);
		sql += String.format(", `body` = '%s' ", body);
		
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			stmt = connection.createStatement();
			int no = stmt.executeUpdate(sql);
			response.getWriter().append(no + "개의 데이터가 추가되었습니다.");
			
		} catch (SQLException e) {
			System.err.printf("[SQL 예외] : %s\n", e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.printf("[드라이버 클래스 로딩 예외] : %s\n", e.getMessage());
		}
		finally {
			if ( connection != null ) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.printf("[SQL 예외, connection 닫기] : %s\n", e.getMessage());
					e.printStackTrace();
				}
			}
			if ( stmt != null ) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.printf("[SQL 예외, stmt 닫기] : %s\n", e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
		
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
