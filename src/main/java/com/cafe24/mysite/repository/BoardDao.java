package com.cafe24.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	public BoardVo get(Long no) {
		return sqlSession.selectOne("board.getByNo", no);
	}
	
	public List<BoardVo> getList(Map<String, Integer> map){
		return sqlSession.selectList("board.getBoardList", map);
	}
	
	public int getCount() {
		return sqlSession.selectOne("board.getCount");
	}
	
	public int getMaxGroupNo() {
		return sqlSession.selectOne("board.getMaxGroupNo");
	}
	
	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
	}
	
	public void update(BoardVo vo) {
		sqlSession.update("board.update", vo);
	}
	
	public void delete(Long no) {
		sqlSession.delete("board.delete", no);
	}
	
	public List<BoardVo> getReplyList(BoardVo vo){
		return sqlSession.selectList("board.replySelect", vo);
	}
	
	public void replyInsert(BoardVo vo) {
		sqlSession.insert("board.replyInsert", vo);
	}
	
	public void replyUpdate(BoardVo vo) {
		sqlSession.update("board.replyUpdate", vo);
		System.out.println("dao50-----------");
	}
}
