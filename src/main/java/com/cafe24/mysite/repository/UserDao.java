package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;
	
	public UserDao() {
		System.out.println("UserDao Constructor");
	}
	
	public void update(UserVo vo) {
		sqlSession.update("user.update", vo);
	}
	
	public UserVo get(Long no) {
		UserVo userVo = sqlSession.selectOne("user.getByNo", no);
		return userVo;
	}
	
	public UserVo get(String email, String password) throws UserDaoException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", map);
		return userVo;
	}
	
	public Boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		System.out.println(vo);
		return 1 == count;
	}

//	private Connection getConnection() throws SQLException {
//		Connection conn = null;
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			String url = "jdbc:mariadb://192.168.1.37:3307/webdb";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패:" + e);
//		}
//		return conn;
//	}
}
