package com.todak.todakf.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todak.todakf.user.dto.UserDto;
import com.todak.todakf.user.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper mapper;
	@Autowired
	private PasswordEncoder passwordEncoder;


    public Map<String,String> register(UserDto param) {
    	Map<String,String> result= new HashMap<String, String>();
    	int cnt = mapper.checkNickname(param.getNickname());
    	System.out.println(">>>>cnt:"+cnt);
    	if(cnt > 0) {
    		result.put("message","이미사용중인 닉네임 입니다.");
    		result.put("result","fail");
    	}else {
    		String encodedPassword = passwordEncoder.encode(param.getPassword());
            param.setPassword(encodedPassword);
    		mapper.insertUser(param);
    		result.put("message","회원가입 완료");
    		result.put("result","success");
    	}
        return result;
    }
    public List<UserDto> userList(UserDto param) {
    	return mapper.userList(param);
    }
    public int userCount(UserDto param) {
    	return mapper.userCount(param);
    }
}


