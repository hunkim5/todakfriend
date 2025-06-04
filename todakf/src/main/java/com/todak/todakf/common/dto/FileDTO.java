package com.todak.todakf.common.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
	private long fileMngNid;
	private String fileGroupUuid;
	private String uuid;
	private String filePath;
	private String imgPath;
	private String orgFileName;
	private String mainYn;
	private LocalDateTime createDtm;
}
