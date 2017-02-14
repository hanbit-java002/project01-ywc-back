package com.zipdoc.hb.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertMember(String uid, String userId, String userPw,
			String userName, String userPhone, String userNickname ) {
		Map param = new HashMap();
		param.put("uid", uid);
		param.put("userId", userId);
		param.put("userPw", userPw);
		param.put("userName", userName);
		param.put("userPhone", userPhone);
		param.put("userNickname", userNickname);
		
		return sqlSession.insert("member.insertMember", param);
	}
	public String selectUserPw(String userId) {
		Map param = new HashMap();
		param.put("userId", userId);
		
		return sqlSession.selectOne("member.selectUserPw", param);
	}
	public String selectUID(String userId) {
		Map param = new HashMap();
		param.put("userId", userId);
		
		return sqlSession.selectOne("member.selectUID", param);
	}
	
}
