package com.zipdoc.hb.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zipdoc.hb.admin.service.PartnerService;
@RequestMapping("/admin/api/partner")
@RestController
public class PartnerController {
	
	@Autowired
	private PartnerService PartnerService;
	
	@RequestMapping("/list")
	public List getList() {
		return PartnerService.getList();	
	}

}
