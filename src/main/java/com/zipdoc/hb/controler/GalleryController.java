package com.zipdoc.hb.controler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zipdoc.hb.service.GalleryService;

@Controller
public class GalleryController {
	@Autowired
	private GalleryService galleryService;
	 
	@RequestMapping("/api/gallery/totalcount")
	@ResponseBody
	public String GalleryTotalCount() {
		return galleryService.getAllCount();
	}
	
	@RequestMapping("/api/gallery/lists")
	@ResponseBody
	public List<Map<String, Object>> GalleryLists(@RequestParam("pager") int pager,
			@RequestParam("viewItems") int viewItems) {
				
		return galleryService.getGalleryLists(pager,viewItems);
	}
	
	@RequestMapping("/api/gallery/main/img")
	@ResponseBody
	public List<Map<String, Object>> GalleryMainImg(@RequestParam("pager") int pager, @RequestParam("viewItems") int viewItems) {
		return galleryService.getGalleryMainImg(pager, viewItems);
	}
	@RequestMapping("/api/gallery/imgs")
	@ResponseBody
	public List<Map<String, Object>> GalleryImgs(@RequestParam("galleryId") String galleryId) {
		return galleryService.getGalleryImgs(galleryId);
	}
	@RequestMapping("/api/gallery/detail")
	@ResponseBody
	public List<Map<String, Object>> GalleryDetailsInfo(@RequestParam("galleryId") String galleryId) {
		return galleryService.GalleryDetailsInfo(galleryId);
	}
	@RequestMapping("/api/gallery/partner")
	@ResponseBody
	public List<Map<String, Object>> GalleryPartner(@RequestParam("galleryId") String galleryId) {
		return galleryService.GalleryPartner(galleryId);
	}
	

}
