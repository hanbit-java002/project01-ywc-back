package com.zipdoc.hb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zipdoc.hb.dao.MainDAO;

@Service
public class MainService {
	@Autowired private MainDAO mainDAO;
	
	private String headerDetails;
	
	public void headerSet(String headerDetails) {
		this.headerDetails = headerDetails;
	}
	
	public String headerGet() {
		return headerDetails;
	}
}
