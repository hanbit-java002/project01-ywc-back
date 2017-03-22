package com.zipdoc.hb.admin.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zipdoc.hb.admin.dao.PartnerDAO;
import com.zipdoc.hb.service.FileService;
import com.zipdoc.hb.util.KeyUtils;

@Service
public class PartnerService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(PartnerService.class);
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private PartnerDAO partnerDAO;
	
	public List getList() {
		return partnerDAO.selectList();
	}
	
	public Map get(String partnerId) {
		return partnerDAO.selectOne(partnerId);
	}
	
	@Transactional
	public int add(Map partnerDetail, MultipartFile partnerImgFile) {
		
		String partnerId = KeyUtils.generateKey("PAR");
		String partnerImg = "/api2/file/" + partnerId;
		partnerDetail.put("partnerId", partnerId);
		partnerDetail.put("partnerImg", partnerImg);
		
		int result= partnerDAO.insert(partnerDetail);
		
		fileService.addAndSave(partnerId, partnerImgFile);
		
		return result;
	}

}
