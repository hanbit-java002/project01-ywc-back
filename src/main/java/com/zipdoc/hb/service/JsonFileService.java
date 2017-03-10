package com.zipdoc.hb.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonFileService {
	private static ObjectMapper mapper = new ObjectMapper();
	private static Logger logger = LoggerFactory.getLogger(JsonFileService.class);
	public List RecentViewJasonParser(String galleryCookie)throws Exception {
		List jsonParse =mapper.readValue(galleryCookie, List.class);		
		return jsonParse;
		
	}

}
