package com.zipdoc.hb.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class CookieDAO {
	private SqlSession sqlSession;
	
	public List GetCookieGallery(List galleryCookieList) throws Exception {
		return sqlSession.selectList("cookie.gallery.banner", galleryCookieList);
	}

}
