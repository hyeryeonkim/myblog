package com.sbs.java.blog.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbs.java.blog.exception.SQLErrorException;

public class DBUtil {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
	public DBUtil(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
	}

	public Map<String, Object> selectRow (Connection connection, String sql) throws SQLErrorException {
		
		List<Map<String, Object>> rows = selectRows(connection, sql);
		if ( rows.size() == 0 ) {
			return new HashMap<>(); //만약 비어있다면 빈 깡통이라도 리턴하겠다.
		}
		
		return rows.get(0);
	}
	
	public List<Map<String, Object>> selectRows(Connection connection, String sql) throws SQLErrorException {
		List<Map<String, Object>> rows = new ArrayList<>();
		
		
		Statement stmt = null;
		ResultSet rs = null;
		
		
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			int columnSize = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> row = new HashMap<>();

				for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
					String columnName = metaData.getColumnName(columnIndex + 1);
					Object value = rs.getObject(columnName);

					if (value instanceof Long) {
						int numValue = (int) (long) value;
						row.put(columnName, numValue);
					} else if (value instanceof Timestamp) {
						String dateValue = value.toString();
						dateValue = dateValue.substring(0, dateValue.length() - 2);
						row.put(columnName, dateValue);
					} else {
						row.put(columnName, value);
					}
				}

				rows.add(row);
			}
		} catch (SQLException e) {
			//Util.printEx("SQL 예외, SQL  : " + sql, resp, e);   아래 코드로 대체함
			throw new SQLErrorException("SQL 예외, SQL  : " + sql);
		}
		finally {
			if ( rs != null ) {  // stmt, rs순으로 열어줬다면 닫을때는 반대로 닫아주어야 한다! (200707 15:36)
				try {
					rs.close();
				} catch (SQLException e) {
					//Util.printEx("SQL 예외, rs 닫기 " , resp, e); 아래 코드로 대체
					throw new SQLErrorException("SQL 예외, rs 닫기 : " + sql);
				}
			}
			if ( stmt != null ) {
				try {
					stmt.close();
				} catch (SQLException e) {
					//Util.printEx("SQL 예외, stmt 닫기 " , resp, e); 아래 코드로 대체함
					throw new SQLErrorException("SQL 예외, stmt 닫기 : " + sql);
				}
			}
			
		}

		return rows;
	}

	public int insert(Connection dbConn, String sql) throws SQLErrorException {
		/*Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = dbConn.createStatement();
			int no = stmt.executeUpdate(sql); // 추가, 수정된 게시물 개수를 리턴한다.
			
		} catch (SQLException e) {
			System.err.printf("[SQL 예외, SQL : %s] : %s\n", sql, e.getMessage());
		} */
		int id = -1;

		// SQL을 적는 문서파일
		Statement statement = null;
		// SQL의 실행결과 보고서
		ResultSet rs = null;
		try {
			statement = dbConn.createStatement();
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
				System.out.println(id);
			}
		} catch (SQLException e) {
			throw new SQLErrorException("INSERT 쿼리 오류 : " + sql);
			//System.err.printf("[INSERT 쿼리 오류, %s]\n" + e.getStackTrace() + "\n", sql);
		}

		try {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
			
		} catch (SQLException e) {
			System.err.println("[INSERT 종료 오류]\n" + e.getStackTrace());
		}

		return id;
	}

	public int selectRowIntValue(Connection dbConn, String sql) throws SQLErrorException {
		Map<String, Object> row = selectRow(dbConn, sql);
		
		for ( String key : row.keySet() ) {
			return (int)row.get(key);
			
		}
		
		return -1;
	}
	// String은 return 값을 null로 하면 안된다. 
	public String selectRowStringValue(Connection dbConn, String sql) throws SQLErrorException  {
		Map<String, Object> row = selectRow(dbConn, sql);
		
		for ( String key : row.keySet() ) {
			return (String)row.get(key);
			
		}
		
		return "";
	}
	
	public boolean selectRowBooleanValue(Connection dbConn, String sql) throws SQLErrorException {
		Map<String, Object> row = selectRow(dbConn, sql);
		
		for ( String key : row.keySet() ) {
			return ((int)row.get(key)) == 1;
			
		}
		
		return false ;
	}
}
