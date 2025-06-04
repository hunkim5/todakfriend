package com.todak.todakf.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.todak.todakf.common.dto.FileDTO;

@Mapper
public interface CommonMapper {
    List<Map<String,String>> selectSido();
    List<Map<String,Object>> selectPartner();
    List<FileDTO> selectFileList(FileDTO param);

    int insertFileMng(FileDTO param);
    int deleteFileMng(String uuid);
    int insertVisitReport(Map<String,String> param);
}

