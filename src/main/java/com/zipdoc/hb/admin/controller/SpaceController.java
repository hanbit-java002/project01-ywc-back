package com.zipdoc.hb.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zipdoc.hb.admin.service.SpaceService;


@RestController
@RequestMapping("/admin/api/space")
public class SpaceController {
	@Autowired
	private SpaceService spaceService;
	
	@RequestMapping("/list")
	public List list() {
		return spaceService.getList();	
	}
	@RequestMapping("/descList")
	public List descList() {
		return spaceService.getDescList();	
	}
	
	@RequestMapping(value="/add", method=RequestMethod.PUT)
	public Map spaceAdd(@RequestParam("spaceName") String spaceName) {
		spaceService.spaceAdd(spaceName);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}
	@RequestMapping(value="/desc/add", method=RequestMethod.PUT)
	public Map spaceDescAdd(@RequestParam("spaceId") String spaceId, 
			@RequestParam("spaceDescName") String spaceDescName) {
		spaceService.spaceDescAdd(spaceId, spaceDescName);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}

}
