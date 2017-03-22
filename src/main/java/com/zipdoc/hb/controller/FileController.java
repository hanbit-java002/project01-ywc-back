package com.zipdoc.hb.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zipdoc.hb.service.FileService;

@RequestMapping()
@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/api2/file/{fileId}")
	public void getFile(@PathVariable("fileId") String fileId,
			HttpServletResponse response) throws Exception {
		String filePath = FileService.PATH_PREFIX+fileId;
		Map fileInfo=fileService.get(fileId);
		
		response.setContentType((String) fileInfo.get("file_type"));
		response.setContentLengthLong((Long)fileInfo.get("file_size"));
		File file = new File(filePath);
		
		FileInputStream fis = new FileInputStream(file);
		
		BufferedInputStream bis = IOUtils.buffer(fis);
		
		while(bis.available()>0) {
			byte[] buffer = new byte[4096];
			int length=bis.read(buffer);
			response.getOutputStream().write(buffer,0,length);
		}
		fis.close();
		bis.close();
		response.flushBuffer();
		
	}

}
