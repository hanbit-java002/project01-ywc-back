package com.zipdoc.hb.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zipdoc.hb.admin.dao.SpaceDAO;
import com.zipdoc.hb.util.KeyUtils;

@Service
public class SpaceService {
	@Autowired
	private SpaceDAO spaceDAO;
	
	public List getList() {
		return spaceDAO.selectList();
	}
	public List getDescList() {
		return spaceDAO.selectDescList();
	}
	
	@Transactional
	public int updateSpace(String spaceId, String spaceName) {
		return spaceDAO.updateSpace(spaceId, spaceName);
	}
	
	@Transactional
	public int updateSpaceDesc(String spaceDescId, String spaceId, String spaceDescName) {
		return spaceDAO.updateSpaceDesc(spaceDescId, spaceId, spaceDescName);
	}
	
	@Transactional
	public int spaceAdd(String spaceName) {
		String spaceId=KeyUtils.generateKey("SPA");
		
		return spaceDAO.insertSpace(spaceId,spaceName);
	}
	
	@Transactional
	public int spaceDescAdd(String spaceId, String spaceDescName) {
		String spaceDescId=KeyUtils.generateKey("SDC");
		return spaceDAO.insertDescSpace(spaceDescId,spaceId,spaceDescName);
	}

}
