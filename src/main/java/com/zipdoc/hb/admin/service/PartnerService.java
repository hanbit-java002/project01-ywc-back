package com.zipdoc.hb.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zipdoc.hb.admin.dao.PartnerDAO;

@Service
public class PartnerService {
	
	@Autowired
	private PartnerDAO partnerDAO;
	
	public List getList() {
		return partnerDAO.selectList();
	}

}
