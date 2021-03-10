package com.bit.guestbook.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bit.guestbook.vo.GuestBookVo;

public class GuestBookDao {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public boolean insert(GuestBookVo vo) {
		
		try {
			
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "insert into guestbook(name, password, contents) values(?, ?, ?);";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());
			
			
			// 5. SQL 실행
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				//conn.commit();
				return true;
			}
		} catch(SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("[error] " + e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close(); 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean delete(Long no, String password) {
		try {
			
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "delete from guestbook where no = ? and password = ?;";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 바인딩
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			
			
			// 5. SQL 실행
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				//conn.commit();
				return true;
			}
		} catch(SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("[error] " + e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close(); 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
		
	}

	
	
	public List<GuestBookVo> findAll() {
		List<GuestBookVo> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			
			// 3. SQL 준비
			String sql = "select no, name, date_format(reg_date, '%Y-%m-%d') as date, contents, name from guestbook order by reg_date desc;";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 바인딩
			
			// 5. SQL 실행
			rs = pstmt.executeQuery();
			
			// 6. 데이터 가져오기
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				Date date = rs.getDate(3);
				String contents = rs.getString(4);
				
				
				GuestBookVo vo = new GuestBookVo();
				
				vo.setNo(no);
				vo.setName(name);
				vo.setReg_date(date);
				vo.setContents(contents);
				
				list.add(vo);
			}
			
		} catch(SQLException e) {
			// 1. 사과
			// 2. log
			System.out.println("[error] " + e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close(); 
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	private Connection getConnection() throws SQLException {
		
		try {
			// 1. JDBC 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2. 연결
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch(ClassNotFoundException e) {
			// 1. 사과
			// 2. log
			System.out.println("[error] " + e.getMessage());
		} 
		
		return conn;
	}

}
