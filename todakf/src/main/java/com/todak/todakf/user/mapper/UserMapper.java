package com.todak.todakf.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.todak.todakf.user.dto.UserDto;

@Mapper
public interface UserMapper {


    UserDto findByUsername(String nickname);
    int checkNickname(String nickname);
    int insertUser(UserDto param);
    List<UserDto> userList(UserDto param);
    int userCount(UserDto param);
}

