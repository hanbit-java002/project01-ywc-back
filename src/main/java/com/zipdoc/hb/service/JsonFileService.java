package com.zipdoc.hb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonFileService {
	private static ObjectMapper mapper = new ObjectMapper();
	
	public List RecentViewJasonParser(String galleryCookie)throws Exception {
		List jsonParse =mapper.readValue(galleryCookie, List.class);
		return jsonParse;
		
	}

}
