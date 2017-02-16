package com.zipdoc.hb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GalleryDAO {
	@Autowired
	SqlSession sqlSession;
	
	public int getAllCount() {
		return sqlSession.selectOne("gallery.count");
	}
	public List<Map<String,Object>> getGalleryLists(Map listInfo) {
		
		return sqlSession.selectList("gallery.lists", listInfo);
	}

}
