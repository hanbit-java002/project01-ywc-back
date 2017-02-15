package com.zipdoc.hb.controler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zipdoc.hb.service.GalleryService;

@Controller
public class GalleryController {
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("/api/gallery/count")
	public Map GalleryListCount() {
		Map result =new HashMap();
		result.put("totalPages",galleryService.getAllCount());
		return result;
	}

}
