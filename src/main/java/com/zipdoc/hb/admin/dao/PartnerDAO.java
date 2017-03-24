package com.zipdoc.hb.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PartnerDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public int selectOneCount() {
		return sqlSession.selectOne("admin.partner.selectOneCount");
	}
	
	public List selectList(int startIndex, int rowsPerPage) {
		Map param = new HashMap();
		param.put("startIndex", startIndex);
		param.put("rowsPerPage", rowsPerPage);
		return sqlSession.selectList("admin.partner.selectList", param);
	}
	
	public Map selectOne(String partnerId) {
		return sqlSession.selectOne("admin.partner.selectOne", partnerId);
	}
	public int update(Map partnerDetail) {
		return sqlSession.update("admin.partner.update",partnerDetail);
	}
	
	public int delete(String partnerId) {
		return sqlSession.delete("admin.partner.delete",partnerId);
	}
	
	public int insert(Map partnerDetail) {
		Map param = new HashMap();
		param=partnerDetail;
		return sqlSession.insert("admin.partner.insert",param );
	}
	

}
