package com.todak.todakf.common.dto;

import lombok.Data;

@Data
public class PageRequestDTO {
	private int page = 1;
    private int size = 5;

    public int getOffset() {
        return (page - 1) * size;
    }
}
