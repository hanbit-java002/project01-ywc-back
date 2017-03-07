package com.zipdoc.hb.controler;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zipdoc.hb.service.CookieService;
import com.zipdoc.hb.service.JsonFileService;

import scala.util.control.Exception;

@Controller
public class CookieController {
	@Autowired
	private CookieService cookieService;
	@Autowired JsonFileService jsonFileService;
	
	@RequestMapping("/api/cookie/gallery")
	@ResponseBody
	public List CookieGallery(
			@RequestParam("galleryCookie") String galleryCookie) throws Exception{
		List galleryCookieList=jsonFileService.RecentViewJasonParser(galleryCookie);
		return cookieService.GetCookieGallery(galleryCookieList);
		
	}

}
