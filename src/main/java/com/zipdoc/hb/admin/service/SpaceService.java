package com.zipdoc.hb.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zipdoc.hb.admin.dao.SpaceDAO;

@Service
public class SpaceService {
	@Autowired
	private SpaceDAO spaceDAO;
	
	public List getList() {
		return spaceDAO.selectList();
	}

}
