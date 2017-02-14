package com.zipdoc.hb.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zipdoc.hb.dao.MemberDAO;


@Service
public class MemberService {
	
	private static final String SECRET_KEY = "zipdoc";
	private PasswordEncoder passwordEncoder = new StandardPasswordEncoder(SECRET_KEY);
	
	@Autowired
	private MemberDAO memberDAO;
	
	private String generateKey(String prefix) {
		String key = prefix + StringUtils.leftPad(
				String.valueOf(System.nanoTime()), 30, "0");
		
		key += StringUtils.leftPad(
				String.valueOf((int) (Math.random() * 1000) % 100), 2, "0");
		
		return key;
	}
	
	public String addMember(String userId, String userPw,
			String userName, String userPhone, String userNickname) {
		String uid = generateKey("UID");
		String encryptedUserPw = passwordEncoder.encode(userPw);
		
		memberDAO.insertMember(uid, userId, encryptedUserPw, userName, userPhone, userNickname);
		
		return uid;
	}
	
	public boolean isValidMember(String userId, String userPw) {
		String encryptedUserPw =memberDAO.selectUserPw(userId);
		
		return passwordEncoder.matches(userPw, encryptedUserPw );
	}
	public String getUid(String userId){
		return memberDAO.selectUID(userId);
	}
	
	
}
