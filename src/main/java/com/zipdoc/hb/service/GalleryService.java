package com.zipdoc.hb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public int getStartIndex(int pager,int viewItems) {
		int startIndex=(pager-1)*viewItems;
		return startIndex;
	}
	
	public List<Map <String, Object>> getGalleryLists(int pager, int viewItems) {
		int startIndex= getStartIndex(pager,viewItems);
		Map listInfo = new HashMap();
		listInfo.put("startIndex", startIndex);
		listInfo.put("viewItems", viewItems);
		return galleryDAO.getGalleryLists(listInfo);
	}
	public List<Map<String, Object>> getGalleryMainImg (int pager, int viewItems) {
		int startIndex= getStartIndex(pager,viewItems);
		Map listInfo = new HashMap();
		listInfo.put("startIndex", startIndex);
		listInfo.put("viewItems", viewItems);
		return galleryDAO.getGalleryMainImg(listInfo);
	}

}
