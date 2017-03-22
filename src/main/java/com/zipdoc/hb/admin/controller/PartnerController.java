package com.zipdoc.hb.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	@RequestMapping(value="/{partnerId}", method=RequestMethod.GET)
	public Map get (@PathVariable("partnerId") String partnerId) {
		return PartnerService.get(partnerId);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Map add(MultipartHttpServletRequest request) {
		String partnerName = request.getParameter("partnerName");
		String partnerDesc = request.getParameter("partnerDesc");
		String partnerMajor = request.getParameter("partnerMajor");
		String partnerSpecialty = request.getParameter("partnerSpecialty");
		String partnerRange = request.getParameter("partnerRange");
		
		Map partnerDetail = new HashMap();
		partnerDetail.put("partnerName", partnerName);
		partnerDetail.put("partnerDesc", partnerDesc);
		partnerDetail.put("partnerMajor", partnerMajor);
		partnerDetail.put("partnerSpecialty", partnerSpecialty);
		partnerDetail.put("partnerRange", partnerRange);
		
		MultipartFile partnerImgFile = request.getFile("partnerImg");
		PartnerService.add(partnerDetail, partnerImgFile);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}

}
