package com.zipdoc.hb.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public int spaceAdd(String spaceName) {
		String spaceId=KeyUtils.generateKey("SPA");
		
		return spaceDAO.insertSpace(spaceId,spaceName);
	}
	public int spaceDescAdd(String spaceId, String spaceDescName) {
		String spaceDescId=KeyUtils.generateKey("SDC");
		return spaceDAO.insertDescSpace(spaceDescId,spaceId,spaceDescName);
	}

}
