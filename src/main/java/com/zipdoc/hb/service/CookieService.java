package com.zipdoc.hb.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanbit.hp.controller.WelcomeController;
import com.zipdoc.hb.dao.CookieDAO;

@Service
public class CookieService {
	 private static final Logger LOGGER = LoggerFactory.getLogger(CookieService.class);
	@Autowired
	private CookieDAO cookieDAO;
	
	public List GetCookieGallery(List galleryCookieList) throws Exception{
		
		return cookieDAO.GetCookieGallery(galleryCookieList);
	}

}
