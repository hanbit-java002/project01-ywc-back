package com.zipdoc.hb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zipdoc.hb.dao.GalleryDAO;

@Service
public class GalleryService {
	@Autowired
	private GalleryDAO galleryDAO;
	
	public String getAllCount() {
		return galleryDAO.getAllCount();
	}

}
