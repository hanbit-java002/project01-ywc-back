package com.zipdoc.hb.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
