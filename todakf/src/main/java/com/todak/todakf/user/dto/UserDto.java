package com.todak.todakf.user.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDto {
    private Integer userSeq;
    private String userId;
    private String nickname;
    private String searchNickname;
    private String searchUserId;
    private String password;
    private String role;
    private String roleName;
    private String param;
    private LocalDate createDtm;

    private Integer page;
    private Integer offset;
    private Integer pageSize;

    public Integer getPage() {
		if(this.page == null) {
			return 0;
		}
		return page;
	}
}
