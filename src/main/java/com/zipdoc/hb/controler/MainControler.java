package com.zipdoc.hb.controler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zipdoc.hb.service.MainService;

@Controller
public class MainControler {
	@Autowired private MainService mainService;
	
	@RequestMapping("/api/header/setdetails")
	@ResponseBody
	public void headerSet(@RequestParam("headerDetails") String headerDetails) {	
		mainService.headerSet(headerDetails);
	}
	@RequestMapping("/api/header/getdetails")
	@ResponseBody
	public Map headerGet() {
		String headerDetails = mainService.headerGet();
		Map result =new HashMap();
		result.put("result", headerDetails);
		return result;
	}
	
	

}
