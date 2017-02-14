package com.zipdoc.hb.controler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zipdoc.hb.service.MemberService;

@Controller
public class MemberControler {
	
	@Autowired
	private MemberService memberService;
	@RequestMapping(value="/api/member/register", method=RequestMethod.POST)
	@ResponseBody
	public Map register(@RequestParam("userId") String userId, 
			@RequestParam("userPw") String userPw,
			@RequestParam("userName") String userName,
			@RequestParam("userPhone") String userPhone,
			@RequestParam("userNickname") String userNickname) {
		if (StringUtils.isBlank(userId)) {
			throw new RuntimeException("아이디가 잘못 입력되었습니다.");
		}
		else if (StringUtils.isBlank(userPw)) {
			throw new RuntimeException("비밀번호가 잘못 입력되었습니다.");
		}
		String uid = memberService.addMember(userId, userPw, userName, userPhone, userNickname);
		
		Map result = new HashMap();
		result.put("result", "ok");
		result.put("uid", uid);
		
		return result;
	}
	@RequestMapping(value="/api/member/login", method= RequestMethod.POST)
	@ResponseBody
	public Map login (@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpSession session ){
		
		try {
			if (! memberService.isValidMember(userId, userPw)) {
				throw new RuntimeException("패스워드가 다릅니다.");
			}
		}
		catch (NullPointerException e) {
			throw new RuntimeException("가입되지 않은 사용자입니다.");
		}
		String uid= memberService.getUid(userId);
		session.setAttribute("uid", uid);
		session.setAttribute("logedIn", true);
		session.setAttribute("userId", userId );
	
		Map result = new HashMap();
		result.put("result", "ok");
		return result;
	}
	@RequestMapping("/api/member/logedin")
	@ResponseBody
	public Map logedIn (HttpSession session) {
		Map result =new HashMap();
		String logedIn ="no";
		
		if (session.getAttribute("logedIn") !=null 
				&& (Boolean) session.getAttribute("logedIn") ){
			logedIn="yes";
			result.put("userId", session.getAttribute("userId"));
		}
		result.put("result", logedIn);
		return result;
	}
	@RequestMapping("/api/member/logout")
	@ResponseBody
	public Map logedOut (HttpSession session) {
		session.invalidate();
		Map result =new HashMap();
		result.put("result", "ok");
		return result;
	}


}
