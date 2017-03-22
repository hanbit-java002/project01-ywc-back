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
	
	public List selectList() {
		return sqlSession.selectList("admin.partner.selectList");
	}
	public Map selectOne(String partnerId) {
		return sqlSession.selectOne("admin.partner.selectOne", partnerId);
	}
	public int insert(Map partnerDetail) {
		Map param = new HashMap();
		param=partnerDetail;
		return sqlSession.insert("admin.partner.insert",param );
	}
	

}
