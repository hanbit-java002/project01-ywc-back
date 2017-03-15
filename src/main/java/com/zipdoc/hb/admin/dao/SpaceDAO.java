package com.zipdoc.hb.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List selectDescList(){
		return sqlSession.selectList("admin.space.selectDescList");
	}
	public int insertSpace(String spaceId,String spaceName) {
		Map param = new HashMap();
		param.put("spaceId", spaceId);
		param.put("spaceName", spaceName);
		
		return sqlSession.insert("admin.space.insertSpace", param);
	}
	public int insertDescSpace(String spaceDescId,String spaceId,String spaceDescName) {
		Map param = new HashMap();
		param.put("spaceDescId", spaceDescId);
		param.put("spaceId", spaceId);
		param.put("spaceDescName", spaceDescName);
		
		return sqlSession.insert("admin.space.insertDescSpace", param);
		
	}

}
