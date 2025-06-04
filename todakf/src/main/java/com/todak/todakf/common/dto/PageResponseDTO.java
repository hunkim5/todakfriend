package com.todak.todakf.common.dto;

import java.util.List;

import lombok.Data;

@Data
public class PageResponseDTO<T> {
	private List<T> list;
    private int totalCount;
    private int totalPages;
    private int page;
    private int pageSize;
    private int offset;

    // 블록 관련
    private int blockSize = 5;
    private int startBlock;
    private int endBlock;
    private boolean hasPrevBlock;
    private boolean hasNextBlock;

    public PageResponseDTO(List<T> list, int totalCount, PageRequestDTO request) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = request.getSize();
        this.page = request.getPage();
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);

        // 블록 계산
        this.startBlock = ((page - 1) / blockSize) * blockSize + 1;
        this.endBlock = Math.min(startBlock + blockSize - 1, totalPages);
        this.hasPrevBlock = startBlock > 1;
        this.hasNextBlock = endBlock < totalPages;
    }
}
