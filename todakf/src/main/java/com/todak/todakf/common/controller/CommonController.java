package com.todak.todakf.common.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.todak.todakf.common.dto.FileDTO;
import com.todak.todakf.common.mapper.CommonMapper;

@Controller
@RequestMapping("/common")
public class CommonController {
	@Autowired
	private CommonMapper mapper;

	@PostMapping("/sido")
    public @ResponseBody List<Map<String, String>> sido() {
		List<Map<String, String>> sido = mapper.selectSido();
        return sido;
    }
	@PostMapping("/partner")
	public @ResponseBody List<Map<String, Object>> partner() {
		List<Map<String, Object>> list = mapper.selectPartner();
		return list;
	}
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(@RequestBody FileDTO param) {
		List<FileDTO> list = mapper.selectFileList(param);
		mapper.deleteFileMng(param.getFileGroupUuid());
		FileDTO data = list.get(0);
		File f = new File(data.getFilePath());
		if(f.exists()) {
			f.delete();
		}
		return ResponseEntity.ok("success");
	}
}
