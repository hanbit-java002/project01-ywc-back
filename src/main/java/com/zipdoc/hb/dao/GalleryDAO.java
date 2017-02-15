package com.zipdoc.hb.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GalleryDAO {
	@Autowired
	SqlSession sqlSession;
	
	public String getAllCount() {
		return sqlSession.selectOne("gallery.count");
	}

}
