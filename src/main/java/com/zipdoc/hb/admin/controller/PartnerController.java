package com.zipdoc.hb.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zipdoc.hb.admin.service.PartnerService;

@RequestMapping("/admin/api/partner")
@RestController
public class PartnerController {
	
	@Autowired
	private PartnerService partnerService;
	
	@RequestMapping("/list")
	public Map getList(@RequestParam("currentPage") int currentPage,
			@RequestParam("rowsPerPage") int rowsPerPage) {
		 Map result= new HashMap();
		List list = partnerService.getList(currentPage, rowsPerPage);
		int count = partnerService.count();
		result.put("list",list);
		result.put("count", count);
		
		return result;	
	}
	
	@RequestMapping(value="/{partnerId}", method=RequestMethod.GET)
	public Map get (@PathVariable("partnerId") String partnerId) {
		return partnerService.get(partnerId);
	}
	
	@RequestMapping(value="/{partnerId}", method=RequestMethod.POST)
	public Map modify (@PathVariable("partnerId") String partnerId, MultipartHttpServletRequest request ) {
		String partnerName = request.getParameter("partnerName");
		String partnerDesc = request.getParameter("partnerDesc");
		String partnerMajor = request.getParameter("partnerMajor");
		String partnerSpecialty = request.getParameter("partnerSpecialty");
		String partnerRange = request.getParameter("partnerRange");
		
		Map partnerDetail = new HashMap();
		partnerDetail.put("partnerId", partnerId);
		partnerDetail.put("partnerName", partnerName);
		partnerDetail.put("partnerDesc", partnerDesc);
		partnerDetail.put("partnerMajor", partnerMajor);
		partnerDetail.put("partnerSpecialty", partnerSpecialty);
		partnerDetail.put("partnerRange", partnerRange);
		
		MultipartFile partnerImgFile = request.getFile("partnerImg");
		partnerService.update(partnerDetail, partnerImgFile);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}
	
	@RequestMapping(value="/{partnerId}", method=RequestMethod.DELETE)
	public Map delete (@PathVariable("partnerId") String partnerId) {
		
		partnerService.delete(partnerId);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
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
		partnerService.add(partnerDetail, partnerImgFile);
		
		Map result = new HashMap();
		result.put("result", "ok");
		
		return result;
	}

}
