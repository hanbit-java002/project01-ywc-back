package com.zipdoc.hb.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SpaceDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public List selectList(){
		return sqlSession.selectList("admin.space.selectList");
	}

}
