package com.zipdoc.hb.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zipdoc.hb.dao.FileDAO;
import com.zipdoc.hb.util.KeyUtils;

@Service
public class FileService {
	
	public static final String PATH_PREFIX = "/zipdoc/upload/";
	
	@Autowired
	private FileDAO fileDAO;
	
	public Map get(String fileId) {
		return fileDAO.selectOne(fileId);
	}
	
	@Transactional
	public String addAndSave(String fileId, MultipartFile multipartFile) {
		fileId = add(fileId, multipartFile.getContentType(),multipartFile.getSize(),
				multipartFile.getOriginalFilename());
		
		String filePath = PATH_PREFIX +fileId;
		File file = new File(filePath);
		
		try {
			FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		return fileId;
	}
	
	@Transactional
	public void updateAndSave(String fileId, MultipartFile multipartFile) {
		delete(fileId);
		addAndSave(fileId, multipartFile);
	}
	
	@Transactional
	public void delete(String fileId) {
		
		fileDAO.delete(fileId);
		
		String filePath = PATH_PREFIX+fileId;
		File file = new File(filePath);
		
		try {
			FileUtils.forceDelete(file);
		} catch (FileNotFoundException e) {
			// Ignore
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public String add(String fileId, String fileType ,long fileSize,
			String fileName) {
		if (fileId == null) {
			fileId = KeyUtils.generateKey("FILE");
		}
		fileDAO.insert(fileId,fileType,fileSize,fileName);
		return fileId;
		
	}

}
