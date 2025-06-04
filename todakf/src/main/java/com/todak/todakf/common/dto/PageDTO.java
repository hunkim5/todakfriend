package com.todak.todakf.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageDTO {
    private int totalCount;
    private int totalPages;
    private int page;
    private int pageSize=15;
    private int offset;

    // 블록 관련
    private int blockSize = 5;
    private int startBlock;
    private int endBlock;
    private boolean hasPrevBlock;
    private boolean hasNextBlock;

    public int getOffset() {
        return (page - 1) * pageSize;
    }

    public PageDTO(int totalCount,int page) {
    	if(page < 1) {
    		page = 1;
    	}
    	this.page = page;
    	this.totalCount = totalCount;
    	this.totalPages = (int) Math.ceil((double) totalCount / pageSize);
    	// 블록 계산
    	this.startBlock = ((this.page - 1) / blockSize) * blockSize + 1;
    	this.endBlock = Math.min(startBlock + blockSize - 1, totalPages);
    	this.hasPrevBlock = startBlock > 1;
    	this.hasNextBlock = endBlock < totalPages;
    }
}
