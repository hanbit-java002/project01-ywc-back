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
	
	public String getAllCount() {
		return sqlSession.selectOne("gallery.count");
	}
	public List<Map<String,Object>> getGalleryLists(Map listInfo) {
		
		return sqlSession.selectList("gallery.lists", listInfo);
	}
	public List<Map<String, Object>> getGalleryMainImg(Map listInfo) {
		return sqlSession.selectList("gallery.mainimg", listInfo);
	}
	public List<Map<String, Object>> getGalleryImgs(int galleryId) {
		return sqlSession.selectList("gallery.imgs", galleryId);
	}
	public List<Map<String, Object>> GalleryDetailsInfo(int galleryId) {
		return sqlSession.selectList("gallery.detailsinfo", galleryId);
	}
	public List<Map<String, Object>> GalleryPartner(int galleryId) {
		return sqlSession.selectList("gallery.partner", galleryId);
	}


}
